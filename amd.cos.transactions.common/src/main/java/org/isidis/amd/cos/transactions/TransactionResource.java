package org.isidis.amd.cos.transactions;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TransactionResource extends Remote {
	void activeTransaction(Transaction pTransaction) throws RemoteException;
	void prepare() throws RemoteException;
	void commit() throws TransactionException, RemoteException;
	void rollback() throws TransactionException, RemoteException;
}
