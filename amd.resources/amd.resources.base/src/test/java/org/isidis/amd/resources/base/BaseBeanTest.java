package org.isidis.amd.resources.base;

import java.rmi.RemoteException;

import junit.framework.TestCase;

import org.isidis.amd.resources.common.Unit;

public class BaseBeanTest extends TestCase
{
	private BaseBean instance;
	
	public BaseBeanTest()
	{
		super();
	}
	
	protected void setUp() throws Exception
	{
		instance = new BaseBean();
	}
	
	protected void tearDown() throws Exception
	{
		super.tearDown();
	}
	
	public void testTrain() throws RemoteException
	{
		Unit novice = instance.getNovice("JD");
		assertTrue("Is it a novice ?",novice instanceof Novice);
	}
}