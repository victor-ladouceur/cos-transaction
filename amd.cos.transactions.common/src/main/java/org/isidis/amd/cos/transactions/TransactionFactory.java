package org.isidis.amd.cos.transactions;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Interface implemented by the concrete transaction factories.
 */
public interface TransactionFactory extends Remote {
	/**
	 * Create a new transaction.
	 * @throws RemoteException
	 * @return A new transaction (without registered resources).
	 */
	public Transaction createTransaction() throws RemoteException;
	/** 
	 * Returns the list of all transactions that were created by the transaction factory.
	 * @throws RemoteException
	 * @return The list of all created transactions.
	 */
	public List<Transaction> getTransactions() throws RemoteException;
	/** 
	 * Returns the list of all transactions that are attached with the resource given in the call.
	 * @param pResource The resource attached to the transactions listed. 
	 * @throws RemoteException
	 * @return The list of all transactions that are attached with the resource given in the call.
	 */
	public List<Transaction> getTransactions(TransactionResource pResource) throws RemoteException;
}
