package org.isidis.amd.cos.transactions;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TransactionManager extends Remote {
	public TransactionFactory getTransactionFactory() throws RemoteException;
	//public Object getResourceProxy(Class resource) throws RemoteException;
	//public <T>T registerAsResource(Class<T> pResourceInterface, String pResourceUrl) throws MalformedURLException, RemoteException, NotBoundException;
	//public List<Class<?>> getResources() throws RemoteException;
	//public boolean isProxyResource(Object pResource) throws RemoteException;
}
