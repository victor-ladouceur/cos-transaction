package org.isidis.amd.cos.transactions;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Interface implemented by the concrete transactions.
 */
public interface Transaction extends Remote {
	/** 
	 * Returns the coordinator of the transaction.
	 * @throws RemoteException
	 * @return The coordinator of the transaction.
	 */
	public TransactionCoordination getCoordinator() throws RemoteException;
	/** 
	 * Returns the ID of the transaction.
	 * @throws RemoteException
	 * @return ID of the transaction.
	 */
	public int getId() throws RemoteException;
	/** 
	 * Starts the transaction (prepare all the resources that are registered on the transaction).
	 * @throws TransactionException When the transaction has already begun.
	 * @throws RemoteException
	 */
	public void begin() throws TransactionException, RemoteException;
	/** 
	 * Commit the transaction (commit all the resources that are registered on the transaction).
	 * @throws TransactionException When the transaction hasen't started.
	 * @throws RemoteException
	 */
	public void commit() throws TransactionException, RemoteException;
	/** 
	 * Rollback the transaction (rollback all the resources that are registered on the transaction).
	 * @throws TransactionException When the transaction hasen't started.
	 * @throws RemoteException
	 */
	public void rollback() throws TransactionException, RemoteException;
	/** 
	 * Know if the transaction is started.
	 * @throws RemoteException
	 * @return Boolean that indicates if the transaction is started.
	 */
	public boolean hasStarted() throws RemoteException;
	/** 
	 * Add an invocation in the the transaction (the transaction is started).
	 * @param pInvocation A invocation to be add in the transaction.
	 * @throws TransactionException When an invocation is trying to be add and the transaction hasn't started yet.
	 * @throws RemoteException
	 */
	public void addInvocation(Invocation pInvocation) throws TransactionException, RemoteException;
	/** 
	 * Returns the list of all invocations that were added in the transaction.
	 * @throws RemoteException
	 * @return The list of all invocations that were added in the transaction.
	 */
	public List<Invocation> getInvocations() throws RemoteException;
	/**
	 * Returns a textual message that resume the state of the transaction.
	 * @throws RemoteException
	 * @return A textual message that resume the state of the transaction.
	 */
	public String getInvocationsResume() throws RemoteException;
}
