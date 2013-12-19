package org.isidis.amd.cos.transactions;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Interface implemented by the concrete coordinators.
 */
public interface TransactionCoordination extends Remote {
	/**
	 * Register a resource on the managed transaction.
	 * @param pResource The resource has registered on the managed transaction.
	 * @throws RemoteException
	 */
	public void registerResource(TransactionResource pResource) throws RemoteException;
	/** 
	 * Returns the list of all resources that were registered on the managed transaction.
	 * @throws RemoteException
	 * @return The list of all registered resources on the managed transaction.
	 */
	public List<TransactionResource> getResources () throws RemoteException;
	/** 
	 * Know if a resource is registered in the managed transaction.
	 * @param pResource The tested resource.
	 * @return Boolean that indicates if a resource is registered in the managed transaction.
	 */
	public boolean hasResource(TransactionResource pResource) throws RemoteException;
}
