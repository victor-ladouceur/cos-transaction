package org.isidis.amd.client.transaction.launcher;

import java.rmi.RMISecurityManager;

import org.isidis.amd.cos.transactions.Transaction;
import org.isidis.amd.cos.transactions.api.TransactionAPI;
import org.isidis.amd.resources.common.Base;
import org.isidis.amd.resources.common.BattleField;
import org.isidis.amd.resources.common.TrainingCamp;


public class MainClient
{
	public static final String SERVICES_HOST = "127.0.0.1";
	
	public static final int BASE_RESOURCE_PORT = 1098;
	public static final String BASE_RESOURCE_SERVICE = "base";
	
	public static final int TRAININGCAMP_RESOURCE_PORT = 1097;
	public static final String TRAININGCAMP_RESOURCE_SERVICE = "trainingcamp";
	
	public static final int BATTLEFIELD_RESOURCE_PORT = 1096;
	public static final String BATTLEFIELD_RESOURCE_SERVICE = "battlefield";
	
	public static final String BASE_RESOURCE_ADDRESS = String.format("rmi://%s:%s/%s/", SERVICES_HOST, BASE_RESOURCE_PORT, BASE_RESOURCE_SERVICE);
	public static final String TRAININGCAMP_RESOURCE_ADDRESS = String.format("rmi://%s:%s/%s/", SERVICES_HOST, TRAININGCAMP_RESOURCE_PORT, TRAININGCAMP_RESOURCE_SERVICE);
	public static final String BATTLEFIELD_RESOURCE_ADDRESS = String.format("rmi://%s:%s/%s/", SERVICES_HOST, BATTLEFIELD_RESOURCE_PORT, BATTLEFIELD_RESOURCE_SERVICE);
	
	public static void main(String[] args) 
	{
		if (System.getSecurityManager() == null) 
		{
			System.setProperty("java.security.policy", "all.policy");
			System.setSecurityManager(new RMISecurityManager());
		}
		
		try 
		{	
			TransactionAPI api = TransactionAPI.init();	
			Base base = api.registerAsResource(Base.class, BASE_RESOURCE_ADDRESS);
			TrainingCamp trainingcamp = api.registerAsResource(TrainingCamp.class, TRAININGCAMP_RESOURCE_ADDRESS);
			BattleField battlefield = api.registerAsResource(BattleField.class, BATTLEFIELD_RESOURCE_ADDRESS);
			Transaction T1 = api.createTransaction();
			api.attachResource(T1, base);
			Transaction T2 = api.createTransaction(base, trainingcamp);
			Transaction T3 = api.createTransaction(battlefield);
			api.attachResource(T3,  trainingcamp);
			T1.begin();
			T2.begin();
			battlefield.deploy(trainingcamp.train(base.getNovice("JD")));
			T3.begin();
			battlefield.deploy(trainingcamp.train(base.getNovice("Bond")));
			T2.rollback();
			battlefield.deploy(trainingcamp.train(base.getNovice("De Gaulle")));
			T1.commit();
			T2.begin();
			battlefield.deploy(trainingcamp.train(base.getNovice("Bond")));
			T2.rollback();
			System.out.println(T3.getInvocationsResume());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		System.out.println("End of the treatment");
		System.exit(0);
	}
}