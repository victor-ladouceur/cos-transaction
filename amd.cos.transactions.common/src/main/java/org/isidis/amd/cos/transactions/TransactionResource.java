package org.isidis.amd.cos.transactions;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface implemented by the concrete transaction resources.
 */
public interface TransactionResource extends Remote {
	/**
	 * Active a transaction that will be used by the transaction operations.
	 * @param pTransaction The transaction that will be used by the transaction operations (prepare, commit, rollback).
	 * @throws RemoteException
	 */
	void activeTransaction(Transaction pTransaction) throws RemoteException;
	/**
	 * Prepare the transaction resource with the current transaction (activated by the activeTransaction method).
	 * @throws RemoteException
	 */
	void prepare() throws RemoteException;
	/**
	 * Commit the transaction resource with the current transaction (activated by the activeTransaction method).
	 * @throws RemoteException
	 */
	void commit() throws TransactionException, RemoteException;
	/**
	 * Rollback the transaction resource with the current transaction (activated by the activeTransaction method).
	 * @throws RemoteException
	 */
	void rollback() throws TransactionException, RemoteException;
}
