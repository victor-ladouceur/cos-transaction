package org.isidis.amd.resources.common;

import java.rmi.RemoteException;

import org.isidis.amd.cos.transactions.TransactionResource;

public interface TrainingCamp extends TransactionResource 
{
	public Unit train(Unit pUnit) throws RemoteException;
}