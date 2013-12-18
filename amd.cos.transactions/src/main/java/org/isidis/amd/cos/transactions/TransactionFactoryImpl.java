package org.isidis.amd.cos.transactions;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionFactoryImpl extends UnicastRemoteObject implements TransactionFactory {
	private static final long serialVersionUID = 7130876765774814970L;
	private Map<TransactionResource, List<Transaction>> registry;
	protected List<Transaction> transactions;
	public TransactionFactoryImpl() throws RemoteException {
		super();
		registry = new HashMap<TransactionResource, List<Transaction>>();
		transactions = new ArrayList<Transaction>();
	}
	public Transaction createTransaction() throws RemoteException {
		Transaction transaction = new TransactionImpl(registry);
		transactions.add(transaction);
		System.out.println(String.format(String.format("New transaction (num %d)", TransactionImpl.autoid)));
		return transaction;
	}
	public List<Transaction> getTransactions() throws RemoteException {
		return transactions;
	}
	public List<Transaction> getTransactions(Object pResource) throws RemoteException {
		return getTransactions((TransactionResource) pResource);
	}
	public List<Transaction> getTransactions(TransactionResource pResource) {
		return registry.get(pResource);
	}
}
