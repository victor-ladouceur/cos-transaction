package org.isidis.amd.cos.transactions;

import java.lang.reflect.Method;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface implemented by the concrete invocations.
 */
public interface Invocation extends Remote {
	/**
	 * Returns the remote resource of the invocation.
	 * @return The remote resource of the invocation.
	 * @throws RemoteException
	 */
	public Object getBean() throws RemoteException;
	/**
	 * Returns the method of the invocation.
	 * @return The method of the invocation.
	 * @throws RemoteException
	 */
	public Method getMethod() throws RemoteException;
	/**
	 * Returns the arguments of the invocation.
	 * @return The arguments of the invocation.
	 * @throws RemoteException
	 */
	public Object[] getArgs() throws RemoteException;
	/**
	 * Returns a textual representation of the invocation.
	 * @return A textual representation of the invocation.
	 * @throws RemoteException
	 */
	public String getResume() throws RemoteException;
}
