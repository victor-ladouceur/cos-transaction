package org.isidis.amd.resources.base;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.isidis.amd.cos.transactions.Transaction;
import org.isidis.amd.cos.transactions.TransactionException;
import org.isidis.amd.resources.common.Base;
import org.isidis.amd.resources.common.Unit;

/**
 * Concrete base. It's an implementation of the interface Base.
 * @see Base
 */
public class BaseBean extends UnicastRemoteObject implements Base 
{
	private static final long serialVersionUID = -6622439355462427448L;
	/**
	 * Register of the base.
	 */
	private List<Unit> register;
	/**
	 * Transaction that will be used by the transaction operations (prepare, commit, rollback).
	 */
	private Transaction currentTransaction;
	
	/**
	 * Constructor of a new base resource.
	 * @throws RemoteException
	 */
	public BaseBean() throws RemoteException 
	{
		super();
		register = new ArrayList<Unit>();
	}
	/**
	 * Returns a novice unit.
	 * @param pName Name of the novice.
	 * @return A novice unit.
	 * @throws RemoteException
	 */
	public Unit getNovice(String pName) throws RemoteException 
	{
		Unit novice = new Novice(pName);
		register.add(novice);
		System.out.println("Register updated");
		return novice;
	}
	/**
	 * Prepare the transaction resource with the current transaction (activated by the activeTransaction method).
	 * @throws RemoteException
	 */
	@Override
	public void prepare() throws RemoteException 
	{
		System.out.println("Base resource is now prepared by the transaction "+ currentTransaction.getId());
	}
	/**
	 * Commit the transaction resource with the current transaction (activated by the activeTransaction method).
	 * @throws RemoteException
	 */
	@Override
	public void commit() throws TransactionException, RemoteException 
	{
		System.out.println(String.format("Base resource is now committed (%d operation%s) by the transaction %d ", currentTransaction.getInvocations().size()
				, (currentTransaction.getInvocations().size() == 1)?"":"s", currentTransaction.getId()));
	}
	/**
	 * Rollback the transaction resource with the current transaction (activated by the activeTransaction method).
	 * @throws RemoteException
	 */
	@Override
	public void rollback() throws TransactionException, RemoteException 
	{
		System.out.println(String.format("Base resource is now rollback (%d operation%s) by the transaction %d ", currentTransaction.getInvocations().size()
				, (currentTransaction.getInvocations().size() == 1)?"":"s", currentTransaction.getId()));
	}
	/**
	 * Active a transaction that will be used by the transaction operations.
	 * @param pTransaction The transaction that will be used by the transaction operations (prepare, commit, rollback).
	 * @throws RemoteException
	 */
	@Override
	public void activeTransaction(Transaction pTransaction) throws RemoteException
	{
		currentTransaction = pTransaction;
	}
}