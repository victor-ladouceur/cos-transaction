package org.isidis.amd.cos.transactions;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Transaction extends Remote {
	public TransactionCoordination getCoordinator() throws RemoteException;
	public int getId() throws RemoteException;
	public void begin() throws TransactionException, RemoteException;
	public void commit() throws TransactionException, RemoteException;
	public void rollback() throws TransactionException, RemoteException;
	public boolean hasStarted() throws RemoteException;
	public void addInvocation(Invocation pInvocation) throws RemoteException;
	public List<Invocation> getInvocations() throws RemoteException;
	public String getInvocationsResume() throws RemoteException;
}
