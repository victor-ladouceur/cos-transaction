package org.isidis.amd.cos.transactions;

import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Concrete invocation. It's an implementation of the interface Invocation.
 * @see Transaction
 */
public class InvocationBean extends UnicastRemoteObject implements Invocation 
{
	private static final long serialVersionUID = 1811477713422003131L;
	/**
	 * Remote resource of the invocation.
	 */
	private Object bean;
	/**
	 * Method of the invocation.
	 */
	private Method method;
	/**
	 * Arguments of the invocation.
	 */
	private Object[] args;
	
	/**
	 * Constructor of a concrete invocation.
	 * @param pBean Remote resource of the invocation.
	 * @param pMethod Method of the invocation.
	 * @param pArgs Arguments of the invocation.
	 * @throws RemoteException
	 */
	public InvocationBean(Object pBean, Method pMethod, Object[] pArgs) throws RemoteException 
	{
		bean = pBean;
		method = pMethod;
		args = pArgs;
	}
	/**
	 * Returns the remote resource of the invocation.
	 * @return The remote resource of the invocation.
	 * @throws RemoteException
	 */
	public Object getBean() throws RemoteException 
	{
		return bean;
	}
	/**
	 * Returns the method of the invocation.
	 * @return The method of the invocation.
	 * @throws RemoteException
	 */
	public Method getMethod() throws RemoteException 
	{
		return method;
	}
	/**
	 * Returns the arguments of the invocation.
	 * @return The arguments of the invocation.
	 * @throws RemoteException
	 */
	public Object[] getArgs() throws RemoteException 
	{
		return args;
	}
	/**
	 * Returns a textual representation of the invocation.
	 * @return A textual representation of the invocation.
	 * @throws RemoteException
	 */
	public String getResume() throws RemoteException 
	{
		String interfaceString = (getBean().getClass().getInterfaces())[getBean().getClass().getInterfaces().length-1].getSimpleName();
		StringBuffer argsString = new StringBuffer();
		for (Object a : getArgs())
			argsString.append(String.format("%s(%s),", a.getClass().getSimpleName(), a.toString()));
		
		return String.format("%s.%s(%s)", 
				interfaceString,
				getMethod().getName(),
				argsString.substring(0,argsString.length()-1)
			);
	}
}
