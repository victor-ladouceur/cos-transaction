package org.isidis.amd.resources.common;

import java.rmi.RemoteException;

import org.isidis.amd.cos.transactions.TransactionResource;

public interface Base extends TransactionResource {
	public Unit getNovice(String pName) throws RemoteException;
}
