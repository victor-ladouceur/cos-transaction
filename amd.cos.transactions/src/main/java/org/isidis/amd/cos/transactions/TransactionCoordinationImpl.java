package org.isidis.amd.cos.transactions;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TransactionCoordinationImpl extends UnicastRemoteObject implements TransactionCoordination 
{
	private static final long serialVersionUID = 2344853230834881069L;
	private Transaction transaction;
	private Map<TransactionResource, List<Transaction>> registry;
	private List<TransactionResource> resources;
	
	public TransactionCoordinationImpl(Transaction pTransaction, Map<TransactionResource, List<Transaction>> pRegistry) throws RemoteException 
	{
		super();
		transaction = pTransaction;
		registry = pRegistry;
		resources = new ArrayList<TransactionResource>();
	}
	
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
	
	public boolean hasResource(TransactionResource pResource) 
	{
		return resources.contains(pResource);
	}
	
	public List<TransactionResource> getResources() throws RemoteException 
	{
		return resources;
	}
}