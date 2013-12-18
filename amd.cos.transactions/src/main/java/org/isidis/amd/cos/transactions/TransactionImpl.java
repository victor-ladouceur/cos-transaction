package org.isidis.amd.cos.transactions;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TransactionImpl extends UnicastRemoteObject implements Transaction 
{
	private static final long serialVersionUID = -220661024716748818L;
	public static int autoid;
	public final int id;
	private TransactionCoordination coordinator;
	private boolean started;
	private List<Invocation> bufferedInvocations;
	
	public TransactionImpl(Map<TransactionResource, List<Transaction>> registry) throws RemoteException 
	{
		super();
		id = ++autoid;
		coordinator = new TransactionCoordinationImpl(this, registry);
		started = false;
		bufferedInvocations = new ArrayList<Invocation>();
	}
	
	public TransactionCoordination getCoordinator() throws RemoteException 
	{
		return coordinator;
	}
	
	public int getId() throws RemoteException 
	{
		return id;
	}
	
	public void begin() throws TransactionException, RemoteException 
	{
		if (started)
			throw new TransactionException(String.format("The transaction %d has already begun", id));
		
		Iterator<?> resourcesIterator = coordinator.getResources().iterator();
		while (resourcesIterator.hasNext()) 
		{
			TransactionResource resource = (TransactionResource) resourcesIterator.next();
			resource.prepare();
		}
		
		bufferedInvocations.clear();
		started = true;
		System.out.println(String.format("The transaction %d begin!", id));
	}
	
	public void commit() throws TransactionException, RemoteException 
	{
		if (!started)
			throw new TransactionException(String.format("The transaction %d hasen't started yet", id));

		System.out.println(String.format("%d buffered invocations will be committed:", bufferedInvocations.size()));
		System.out.println(getInvocationsResume());	
		Iterator<?> resourcesIterator = coordinator.getResources().iterator();
		while (resourcesIterator.hasNext()) 
		{
			TransactionResource resource = (TransactionResource) resourcesIterator.next();
			resource.commit();
		}
		started = false;
		System.out.println(String.format("Transaction %d commit!", id));
	}
	
	public void rollback() throws TransactionException, RemoteException 
	{
		if (!started)
			throw new TransactionException(String.format("The transaction %d hasen't started yet", id));
		
		System.out.println(String.format("%d buffered invocations will be undone:", bufferedInvocations.size()));
		System.out.println(getInvocationsResume());	
		Iterator<?> resourcesIterator = coordinator.getResources().iterator();
		while (resourcesIterator.hasNext()) 
		{
			TransactionResource resource = (TransactionResource) resourcesIterator.next();
			resource.rollback();
		}
		started = false;
		System.out.println(String.format("Transaction %d rollback!", id));
	}
	
	public boolean hasStarted() throws RemoteException 
	{
		return started;
	}
	
	public void addInvocation(Invocation pInvocation) throws RemoteException 
	{
		bufferedInvocations.add(pInvocation);
	}
	
	public List<Invocation> getInvocations() throws RemoteException 
	{
		return bufferedInvocations;
	}
	
	public String getInvocationsResume() throws RemoteException 
	{
		if (!hasStarted())
			return String.format("The transaction %d hasen't started yet", id);
		
		if (bufferedInvocations.size() == 0)
			return "No invocation since the beginning of the transaction "+id;
		
		StringBuffer resume = new StringBuffer();
		Iterator<Invocation> invocationsIterator = bufferedInvocations.iterator();
		while (invocationsIterator.hasNext()) 
		{
			Invocation invocation = (Invocation) invocationsIterator.next();
			resume.append(String.format("- %s\n", invocation.getResume()));
		}
		return resume.substring(0,resume.length()-1);
	}
	
	public String toString() 
	{
		return "Transaction "+ id;
	}
}