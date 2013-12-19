package org.isidis.amd.resources.common;

import java.rmi.RemoteException;

import org.isidis.amd.cos.transactions.TransactionResource;

/**
 * Interface implemented by the concrete base resources.
 */
public interface Base extends TransactionResource 
{
	/**
	 * Returns a novice unit.
	 * @param pName Name of the novice.
	 * @return A novice unit.
	 * @throws RemoteException
	 */
	public Unit getNovice(String pName) throws RemoteException;
}