package org.isidis.amd.cos.transactions;

import java.rmi.RemoteException;

import junit.framework.TestCase;

public class InvocationBeanTest extends TestCase 
{
	private InvocationBean instance;
	private String test;
	public InvocationBeanTest()
	{
		super();
	}
	
	protected void setUp() throws Exception
	{
		test = new String("test");
		instance = new InvocationBean(test, test.getClass().getMethods()[0], null);
	}
	
	protected void tearDown() throws Exception
	{
		super.tearDown();
	}
	
	public void testGetMethod() throws RemoteException
	{
		assertTrue(instance.getMethod().getName()==test.getClass().getMethods()[0].getName());
	}
	
	public void testGetBean() throws RemoteException
	{
		assertTrue(instance.getBean()==test);
	}
	
	public void testGetArgs() throws RemoteException
	{
		assertTrue(instance.getArgs()==null);
	}
}