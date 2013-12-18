package org.isidis.amd.resources.common;

import java.rmi.RemoteException;

import junit.framework.TestCase;

public class UnitImplTest extends TestCase 
{
	private UnitImpl unit;
	
	public UnitImplTest()
	{
		super();
	}
	
	protected void setUp() throws Exception
	{
		super.setUp();
		unit = new UnitImpl("test", "test");
	}
	
	protected void tearDown() throws Exception
	{
		super.tearDown();
		unit = null;
	}
	
	public void testUnitImpl()
	{
		assertNotNull("instance is created", unit);
	}
	
	public void testGetName() throws RemoteException
	{
		assertEquals("name is it ok ?", "test", unit.getName());
	}
	
	public void testGetTitle() throws RemoteException
	{
		assertEquals("title is it ok ?", "test", unit.getTitle());
	}
}