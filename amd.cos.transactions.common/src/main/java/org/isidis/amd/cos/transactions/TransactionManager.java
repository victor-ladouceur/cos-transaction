package org.isidis.amd.cos.transactions;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface implemented by the concrete transaction managers.
 */
public interface TransactionManager extends Remote {
	/**
	 * Returns a new transaction factory.
	 * @return A new transaction factory.
	 */
	public TransactionFactory getTransactionFactory() throws RemoteException;
}
