package org.isidis.amd.cos.transactions;

import java.lang.reflect.Method;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Invocation extends Remote {
	public Object getBean() throws RemoteException;
	public Method getMethod() throws RemoteException;
	public Object[] getArgs() throws RemoteException;
	public String getResume() throws RemoteException;
}
