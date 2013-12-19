package org.isidis.amd.resources.trainingcamp;

import java.rmi.RemoteException;

import junit.framework.TestCase;

import org.isidis.amd.resources.common.Unit;
import org.isidis.amd.resources.common.UnitBean;
import org.isidis.amd.resources.trainingcamp.Soldier;

public class SoldierTest extends TestCase 
{
	private Soldier instance;
	
	public SoldierTest()
	{
		super();
	}
	
	protected void setUp() throws Exception
	{
		Unit unit = new UnitBean("Novice","JD");
		instance = new Soldier(unit);
	}
	
	protected void tearDown() throws Exception
	{
		super.tearDown();
		instance = null;
	}
	
	public void testSoldier()
	{
		assertNotNull("instance created", instance);
	}
	
	public void testGetName() throws RemoteException
	{
		assertEquals("name is it ok ?","JD",instance.getName());
	}
	
	public void testGetTitle() throws RemoteException
	{
		assertEquals("title is it ok ?","Soldier", instance.getTitle());
	}
}