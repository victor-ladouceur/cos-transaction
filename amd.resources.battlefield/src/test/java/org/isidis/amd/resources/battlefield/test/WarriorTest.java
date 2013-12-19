package org.isidis.amd.resources.battlefield.test;

import java.rmi.RemoteException;

import junit.framework.TestCase;

import org.isidis.amd.resources.battlefield.Warrior;
import org.isidis.amd.resources.common.Unit;
import org.isidis.amd.resources.common.UnitBean;

public class WarriorTest extends TestCase 
{
	private Warrior instance;
	
	public WarriorTest()
	{
		super();
	}
	
	protected void setUp() throws Exception
	{
		Unit unit = new UnitBean("Soldier","JD");
		instance = new Warrior(unit);
	}
	
	protected void tearDown() throws Exception
	{
		super.tearDown();
	}
	
	public void testWarrior()
	{
		assertNotNull("Instance created",instance);
	}
	
	public void testGetName() throws RemoteException
	{
		assertEquals("name is it ok ?", "JD",instance.getName());
	}
	
	public void testGetTitle() throws RemoteException
	{
		assertEquals("titile is it ok ?","Warrior", instance.getTitle());
	}
}