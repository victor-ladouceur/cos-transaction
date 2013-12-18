package org.isidis.amd.cos.transactions;

import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class InvocationBean extends UnicastRemoteObject implements Invocation {
	private static final long serialVersionUID = 1811477713422003131L;
	private Object bean;
	private Method method;
	private Object[] args;
	public InvocationBean(Object pBean, Method pMethod, Object[] pArgs) throws RemoteException {
		bean = pBean;
		method = pMethod;
		args = pArgs;
	}
	public Object getBean() throws RemoteException {
		return bean;
	}
	public Method getMethod() throws RemoteException {
		return method;
	}
	public Object[] getArgs() throws RemoteException {
		return args;
	}
	public String getResume() throws RemoteException {
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
