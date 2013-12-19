package org.isidis.amd.resources.battlefield;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.isidis.amd.cos.transactions.Transaction;
import org.isidis.amd.cos.transactions.TransactionException;
import org.isidis.amd.resources.common.BattleField;
import org.isidis.amd.resources.common.Unit;

/**
 * Concrete battlefield. It's an implementation of the interface BattleField.
 * @see BattleField
 */
public class BattleFieldBean extends UnicastRemoteObject implements BattleField 
{
	private static final long serialVersionUID = -6622439355462427448L;
	/**
	 * Register of the battlefield.
	 */
	private List<Unit> register;
	/**
	 * Transaction that will be used by the transaction operations (prepare, commit, rollback).
	 */
	private Transaction currentTransaction;
	
	/**
	 * Constructor of a new battlefield resource.
	 * @throws RemoteException
	 */
	public BattleFieldBean() throws RemoteException 
	{
		super();
		register = new ArrayList<Unit>();
	}
	/**
	 * Returns a warrior unit.
	 * @param pUnit The soldier unit who is deployed on the battlefield.
	 * @return A warrior unit.
	 * @throws RemoteException
	 */
	public Unit deploy(Unit pUnit) throws RemoteException 
	{
		Unit novice = new Warrior(pUnit);
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