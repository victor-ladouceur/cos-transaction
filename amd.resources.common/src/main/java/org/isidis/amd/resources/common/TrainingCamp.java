package org.isidis.amd.resources.common;

import java.rmi.RemoteException;

import org.isidis.amd.cos.transactions.TransactionResource;

/**
 * Interface implemented by the concrete training camp resources.
 */
public interface TrainingCamp extends TransactionResource 
{
	/**
	 * Returns a soldier unit.
	 * @param pUnit The novice unit who is trained in the training camp.
	 * @return A soldier unit.
	 * @throws RemoteException
	 */
	public Unit train(Unit pUnit) throws RemoteException;
}