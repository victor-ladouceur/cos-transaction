package org.isidis.amd.cos.transactions;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Transaction manager. It's an implementation of the interface TransactionManager.
 * @see TransactionManager
 */
public class TransactionManagerImpl extends UnicastRemoteObject implements TransactionManager 
{
	private static final long serialVersionUID = 1186759994894825889L;
	
	/**
	 * Constructor of a transaction manager.
	 * @throws RemoteException
	 */
	public TransactionManagerImpl() throws RemoteException 
	{
		super();
	}
	/**
	 * Returns a new transaction factory.
	 * @return A new transaction factory.
	 */
	public TransactionFactory getTransactionFactory() throws RemoteException 
	{
		System.out.println("New transaction factory");
		return new TransactionFactoryImpl();
	}
}