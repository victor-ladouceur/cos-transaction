package org.isidis.amd.resources.common;

import java.rmi.RemoteException;

import org.isidis.amd.cos.transactions.TransactionResource;

public interface BattleField extends TransactionResource {
	public Unit deploy(Unit pUnit) throws RemoteException;
}
