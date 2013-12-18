package org.isidis.amd.client.transaction.launcher;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.RemoteException;

import org.isidis.amd.cos.transactions.Transaction;
import org.isidis.amd.cos.transactions.TransactionCoordination;
import org.isidis.amd.cos.transactions.TransactionException;
import org.isidis.amd.cos.transactions.TransactionFactory;
import org.isidis.amd.cos.transactions.TransactionManager;
import org.isidis.amd.resources.common.Base;
import org.isidis.amd.resources.common.Unit;


public class MainClient
{
	public static final String SERVICES_HOST = "192.168.1.27";
	
	public static final int TRANSACTIONS_MANAGER_PORT = 1099;
	public static final String TRANSACTIONS_MANAGER_SERVICE = "tmanager";
	
	public static final int BASE_RESOURCE_PORT = 1098;
	public static final String BASE_RESOURCE_SERVICE = "base";
	
	public static final int TRAININGCAMP_RESOURCE_PORT = 1097;
	public static final String TRAININGCAMP_RESOURCE_SERVICE = "trainingcamp";
	
	public static final int BATTLEFIELD_RESOURCE_PORT = 1096;
	public static final String BATTLEFIELD_RESOURCE_SERVICE = "battlefield";
	
	public static final String TRANSACTIONS_MANAGER_ADDRESS = String.format("rmi://%s:%s/%s/", SERVICES_HOST, TRANSACTIONS_MANAGER_PORT, TRANSACTIONS_MANAGER_SERVICE);
	public static final String BASE_RESOURCE_ADDRESS = String.format("rmi://%s:%s/%s/", SERVICES_HOST, BASE_RESOURCE_PORT, BASE_RESOURCE_SERVICE);
	public static final String TRAININGCAMP_RESOURCE_ADDRESS = String.format("rmi://%s:%s/%s/", SERVICES_HOST, TRAININGCAMP_RESOURCE_PORT, TRAININGCAMP_RESOURCE_SERVICE);
	public static final String BATTLEFIELD_RESOURCE_ADDRESS = String.format("rmi://%s:%s/%s/", SERVICES_HOST, BATTLEFIELD_RESOURCE_PORT, BATTLEFIELD_RESOURCE_SERVICE);
	
	public static void main(String[] args) 
	{
		try {
			if (System.getSecurityManager() == null) {
				System.setProperty("java.security.policy", "all.policy");
				System.setSecurityManager(new RMISecurityManager());
			}
			
			Remote r = Naming.lookup(TRANSACTIONS_MANAGER_ADDRESS);
			Remote r2 = Naming.lookup(BASE_RESOURCE_ADDRESS);
			//Remote r3 = Naming.lookup(TRAININGCAMP_RESOURCE_ADDRESS);
			//Remote r4 = Naming.lookup(BATTLEFIELD_RESOURCE_ADDRESS);
			
			if (r instanceof TransactionManager 
					&& r2 instanceof Base
					/*&& r3 instanceof TrainingCamp
					&& r4 instanceof BattleField*/) {
				TransactionManager tm = (TransactionManager)r;
				Base base = (Base)r2;
				//TrainingCamp trainingc = (TrainingCamp)r3;
				//BattleField battlef = (BattleField)r4;
				
				System.out.println(tm);
				TransactionFactory tf = tm.getTransactionFactory();
				System.out.println(tf);
				Transaction t = tf.createTransaction();
				System.out.println(t);
				TransactionCoordination c = t.getCoordinator();
				System.out.println(c);
				
				
				System.out.println(base);
				Unit novice = base.getNovice("Steven");
				System.out.println(novice.present());
				
				/*System.out.println(trainingc);
				Unit soldier = trainingc.train(novice);
				System.out.println(soldier.present());*/
				
				/*System.out.println(battlef);
				Unit warrior = battlef.deploy(soldier);
				System.out.println(warrior.present());*/

				System.out.println(c.getResources());
				c.registerResource(base);
				System.out.println(c.getResources());
				/*c.registerResource(trainingc);
				System.out.println(c.getResources());
				c.registerResource(battlef);
				System.out.println(c.getResources());*/
				
				System.out.println("-----");
				base.commit();
				base.prepare();
				base.getNovice("Steven");
				base.commit();
				base.rollback();
				//base.commit();
			}
			System.out.println("-- FIN");
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (TransactionException e) {
			e.printStackTrace();
		}
	}
}