package org.isidis.amd.resources.base;

import java.rmi.RemoteException;

import junit.framework.TestCase;

import org.isidis.amd.resources.common.Unit;
import org.isidis.amd.resources.common.UnitBean;

public class NoviceTest extends TestCase
{
	private Novice instance;
	private Unit unit;
	
	public NoviceTest()
	{
		super();
	}
	
	protected void setUp() throws Exception
	{
		unit = new UnitBean("Novice","JD");
		instance = new Novice("JD");
	}
	
	protected void tearDown() throws Exception
	{
		super.tearDown();
	}
	
	public void testNovice()
	{
		assertNotNull("Instance created",instance);
	}
	
	public void testGetName() throws RemoteException
	{
		assertEquals("name is it ok ?", "JD",unit.getName());
	}
	
	public void testGetTitle() throws RemoteException
	{
		assertEquals("titile is it ok ?","Novice", instance.getTitle());
	}
}