package org.isidis.amd.cos.transactions;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface TransactionFactory extends Remote {
	public Transaction createTransaction() throws RemoteException;
	public List<Transaction> getTransactions() throws RemoteException;
	public List<Transaction> getTransactions(Object pResource) throws RemoteException;
}
