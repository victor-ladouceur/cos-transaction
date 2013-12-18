package org.isidis.amd.cos.transactions;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TransactionManagerImpl extends UnicastRemoteObject implements TransactionManager 
{
	private static final long serialVersionUID = 1186759994894825889L;
	
	public TransactionManagerImpl() throws RemoteException 
	{
		super();
	}
	
	public TransactionFactory getTransactionFactory() throws RemoteException 
	{
		System.out.println("New transaction factory");
		return new TransactionFactoryImpl();
	}
}