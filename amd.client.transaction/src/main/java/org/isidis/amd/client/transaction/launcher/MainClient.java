package org.isidis.amd.client.transaction.launcher;

import java.rmi.RMISecurityManager;
import java.util.Scanner;

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
	
	public static void pause(String pMessage) 
	{
		System.out.print(pMessage);
		pause();
	}
	
	public static void pause() 
	{
		new Scanner(System.in).nextLine();
	}
	
	public static void main(String[] args) 
	{
		if (System.getSecurityManager() == null) 
		{
			System.setProperty("java.security.policy", "all.policy");
			System.setSecurityManager(new RMISecurityManager());
		}
		
		try 
		{	
			pause(">> Initialisation of the Transaction API");
			TransactionAPI api = TransactionAPI.init();
			pause(">> Registration of the Base resource");
			Base base = api.registerAsResource(Base.class, BASE_RESOURCE_ADDRESS);
			pause(">> Registration of the TraningCamp resource");
			TrainingCamp trainingcamp = api.registerAsResource(TrainingCamp.class, TRAININGCAMP_RESOURCE_ADDRESS);
			pause(">> Registration of the BattleField resource");
			BattleField battlefield = api.registerAsResource(BattleField.class, BATTLEFIELD_RESOURCE_ADDRESS);
			pause(">> Creation of the first transaction T1");
			Transaction T1 = api.createTransaction();
			pause(">> Attach the resource Base on T1");
			api.attachResource(T1, base);
			pause(">> Creation of the second transaction T2");
			Transaction T2 = api.createTransaction(base, trainingcamp);
			pause(">> Creation of the transaction T3");
			Transaction T3 = api.createTransaction(battlefield);
			pause(">> Attach the resource TrainingCamp on T1");
			api.attachResource(T3,  trainingcamp);
			pause(">> Begin T1");
			T1.begin();
			pause(">> Begin T2");
			T2.begin();
			pause(">> Calls of the resources");
			battlefield.deploy(trainingcamp.train(base.getNovice("JD")));
			pause(">> Begin T3");
			T3.begin();
			pause(">> Calls of the resources");
			battlefield.deploy(trainingcamp.train(base.getNovice("Bond")));
			pause(">> Rollback T2");
			T2.rollback();
			pause(">> Calls of the resources");
			battlefield.deploy(trainingcamp.train(base.getNovice("De Gaulle")));
			pause(">> Commit T1");
			T1.commit();
			pause(">> Begin T2");
			T2.begin();
			battlefield.deploy(trainingcamp.train(base.getNovice("Bond")));
			pause(">> Rollback T2");
			T2.rollback();
			pause(">> Resume of the transaction T3");
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