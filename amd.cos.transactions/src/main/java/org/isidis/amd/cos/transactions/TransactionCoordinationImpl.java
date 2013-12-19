package org.isidis.amd.cos.transactions;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Transaction coordinator. It's an implementation of the interface TransactionCoordination.
 * @see TransactionCoordination
 */
public class TransactionCoordinationImpl extends UnicastRemoteObject implements TransactionCoordination 
{
	private static final long serialVersionUID = 2344853230834881069L;
	/**
	 * The transaction managed by the coordinator.
	 */
	private Transaction transaction;
	/**
	 * The global registry of the attached resources on all the created transactions.
	 */
	private Map<TransactionResource, List<Transaction>> registry;
	/**
	 * List of all attached resources on the managed transaction.
	 */
	private List<TransactionResource> resources;
	
	/**
	 * Constructor of a transaction coordinator.
	 * @param pTransaction The transaction will be managed by the coordinator.
	 * @param pRegistry The The global registry of the attached resources on all the created transactions.
	 * @throws RemoteException
	 */
	public TransactionCoordinationImpl(Transaction pTransaction, Map<TransactionResource, List<Transaction>> pRegistry) throws RemoteException 
	{
		super();
		transaction = pTransaction;
		registry = pRegistry;
		resources = new ArrayList<TransactionResource>();
	}
	/**
	 * Register a resource on the managed transaction.
	 * @param pResource The resource has registered on the managed transaction.
	 * @throws RemoteException
	 */
	public void registerResource(TransactionResource pResource) throws RemoteException 
	{
		if (hasResource(pResource))
			throw new RuntimeException("Resource has already registred");

		resources.add(pResource);
		
		if (!registry.containsKey(pResource))
			registry.put(pResource, new ArrayList<Transaction>());
		
		registry.get(pResource).add(transaction);
		System.out.println(String.format("A resource is registered in transaction %d : %s", transaction.getId(), pResource.getClass().getInterfaces()[1].getSimpleName()));
	}
	/** 
	 * Returns the list of all resources that were registered on the managed transaction.
	 * @throws RemoteException
	 * @return The list of all registered resources on the managed transaction.
	 */
	public List<TransactionResource> getResources() throws RemoteException 
	{
		return resources;
	}
	/** 
	 * Know if a resource is registered in the managed transaction.
	 * @param pResource The tested resource.
	 * @return Boolean that indicates if a resource is registered in the managed transaction.
	 */
	public boolean hasResource(TransactionResource pResource) 
	{
		return resources.contains(pResource);
	}

}