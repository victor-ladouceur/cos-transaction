package org.isidis.amd.resources.common;

import java.rmi.RemoteException;

import org.isidis.amd.cos.transactions.TransactionResource;

/**
 * Interface implemented by the concrete battlefield resources.
 */
public interface BattleField extends TransactionResource 
{
	/**
	 * Returns a warrior unit.
	 * @param pUnit The soldier unit who is deployed on the battlefield.
	 * @return A warrior unit.
	 * @throws RemoteException
	 */
	public Unit deploy(Unit pUnit) throws RemoteException;
}