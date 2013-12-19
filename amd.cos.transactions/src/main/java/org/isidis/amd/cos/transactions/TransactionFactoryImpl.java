package org.isidis.amd.cos.transactions;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Factory of transactions. It's an implementation of the interface TransactionFactory.
 * @see TransactionFactory
 */
public class TransactionFactoryImpl extends UnicastRemoteObject implements TransactionFactory 
{
	private static final long serialVersionUID = 7130876765774814970L;
	/**
	 * The global registry of the attached resources on all the created transactions.
	 */
	private Map<TransactionResource, List<Transaction>> registry;
	/**
	 * The list of all transactions which were created by the factory.
	 */
	protected List<Transaction> transactions;
	
	/**
	 * Constructor of a transaction factory.
	 * @throws RemoteException
	 */
	public TransactionFactoryImpl() throws RemoteException 
	{
		super();
		registry = new HashMap<TransactionResource, List<Transaction>>();
		transactions = new ArrayList<Transaction>();
	}
	/**
	 * Create a new transaction.
	 * @throws RemoteException
	 * @return A new transaction (without registered resources).
	 */
	public Transaction createTransaction() throws RemoteException 
	{
		Transaction transaction = new TransactionImpl(registry);
		transactions.add(transaction);
		System.out.println(String.format(String.format("New transaction (num %d)", TransactionImpl.autoid)));
		return transaction;
	}
	/** 
	 * Returns the list of all transactions that were created by the transaction factory.
	 * @throws RemoteException
	 * @return The list of all created transactions.
	 */
	public List<Transaction> getTransactions() throws RemoteException 
	{
		return transactions;
	}
	/** 
	 * Returns the list of all transactions that are attached with the resource given in the call.
	 * @param pResource The resource attached to the transactions listed. 
	 * @throws RemoteException
	 * @return The list of all transactions that are attached with the resource given in the call.
	 */
	public List<Transaction> getTransactions(TransactionResource pResource) 
	{
		return registry.get(pResource);
	}
}