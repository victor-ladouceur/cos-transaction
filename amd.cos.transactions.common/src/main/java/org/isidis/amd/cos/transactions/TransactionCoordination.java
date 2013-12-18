package org.isidis.amd.cos.transactions;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface TransactionCoordination extends Remote {
	public void registerResource(TransactionResource pResource) throws RemoteException;
	public boolean hasResource(TransactionResource pResource) throws RemoteException;
	public List<TransactionResource> getResources () throws RemoteException;
}
