package org.isidis.amd.cos.transactions;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Transaction. It's an implementation of the interface TransactionFactory.
 * @see Transaction
 */
public class TransactionImpl extends UnicastRemoteObject implements Transaction 
{
	private static final long serialVersionUID = -220661024716748818L;
	/**
	 * The ID of the next 
	 */
	public static int autoid;
	/**
	 * ID of the transaction.
	 */
	public final int id;
	/**
	 * Coordinator of the transaction.
	 */
	private TransactionCoordination coordinator;
	/**
	 * Boolean that indicates if the transaction has started.
	 */
	private boolean started;
	/**
	 * The list of invocations which were caught by the transaction.
	 */
	private List<Invocation> bufferedInvocations;
	
	/**
	 * Constructor of a transaction.
	 * @param registry The global registry of the attached resources on all the created transactions.
	 * @throws RemoteException
	 */
	public TransactionImpl(Map<TransactionResource, List<Transaction>> registry) throws RemoteException 
	{
		super();
		id = ++autoid;
		coordinator = new TransactionCoordinationImpl(this, registry);
		started = false;
		bufferedInvocations = new ArrayList<Invocation>();
	}
	/** 
	 * Returns the coordinator of the transaction.
	 * @throws RemoteException
	 * @return The coordinator of the transaction.
	 */
	public TransactionCoordination getCoordinator() throws RemoteException 
	{
		return coordinator;
	}
	/** 
	 * Returns the ID of the transaction.
	 * @throws RemoteException
	 * @return ID of the transaction.
	 */
	public int getId() throws RemoteException 
	{
		return id;
	}
	/** 
	 * Starts the transaction (prepare all the resources that are registered on the transaction).
	 * @throws TransactionException When the transaction has already begun.
	 * @throws RemoteException
	 */
	public void begin() throws TransactionException, RemoteException 
	{
		if (started)
			throw new TransactionException(String.format("The transaction %d has already begun", id));
		
		for (TransactionResource resource : coordinator.getResources()) {
			resource.activeTransaction(this);
			resource.prepare();
		}
		
		bufferedInvocations.clear();
		started = true;
		
		System.out.println(String.format("The transaction %d begin!", id));
	}
	/** 
	 * Commit the transaction (commit all the resources that are registered on the transaction).
	 * @throws TransactionException When the transaction hasen't started.
	 * @throws RemoteException
	 */
	public void commit() throws TransactionException, RemoteException 
	{
		if (!started)
			throw new TransactionException(String.format("The transaction %d hasen't started yet", id));

		System.out.println(String.format("%d buffered invocations will be committed:", bufferedInvocations.size()));
		System.out.println(getInvocationsResume());	

		for (TransactionResource resource : coordinator.getResources()) {
			resource.activeTransaction(this);
			resource.commit();
		}
		
		bufferedInvocations.clear();
		started = false;
		
		System.out.println(String.format("Transaction %d commit!", id));
	}
	/** 
	 * Rollback the transaction (rollback all the resources that are registered on the transaction).
	 * @throws TransactionException When the transaction hasen't started.
	 * @throws RemoteException
	 */
	public void rollback() throws TransactionException, RemoteException 
	{
		if (!started)
			throw new TransactionException(String.format("The transaction %d hasen't started yet", id));
		
		System.out.println(String.format("%d buffered invocations will be undone:", bufferedInvocations.size()));
		System.out.println(getInvocationsResume());	

		for (TransactionResource resource : coordinator.getResources()) {
			resource.activeTransaction(this);
			resource.rollback();
		}
		
		bufferedInvocations.clear();
		started = false;
		
		System.out.println(String.format("Transaction %d rollback!", id));
	}
	/** 
	 * Know if the transaction is started.
	 * @throws RemoteException
	 * @return Boolean that indicates if the transaction is started.
	 */
	public boolean hasStarted() throws RemoteException 
	{
		return started;
	}
	/** 
	 * Add an invocation in the the transaction (the transaction is started).
	 * @param pInvocation A invocation to be add in the transaction.
	 * @throws TransactionException When an invocation is trying to be add and the transaction hasn't started yet.
	 * @throws RemoteException
	 */
	public void addInvocation(Invocation pInvocation) throws TransactionException, RemoteException 
	{
		if (!started)
			throw new TransactionException(String.format("The transaction %d hasen't started yet", id));
		
		bufferedInvocations.add(pInvocation);
	}
	/** 
	 * Returns the list of all invocations that were added in the transaction.
	 * @throws RemoteException
	 * @return The list of all invocations that were added in the transaction.
	 */
	public List<Invocation> getInvocations() throws RemoteException 
	{
		return bufferedInvocations;
	}
	/**
	 * Returns a textual message that resume the state of the transaction.
	 * @throws RemoteException
	 * @return A textual message that resume the state of the transaction.
	 */
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
	/**
	 * Returns a textual representation of the transaction.
	 * @return A textual representation of the transaction.
	 */
	public String toString() 
	{
		return "Transaction "+ id;
	}
}