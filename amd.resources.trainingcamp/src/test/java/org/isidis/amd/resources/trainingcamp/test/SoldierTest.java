package org.isidis.amd.resources.trainingcamp.test;

import java.rmi.RemoteException;

import junit.framework.TestCase;

import org.isidis.amd.resources.common.Unit;
import org.isidis.amd.resources.common.UnitBean;
import org.isidis.amd.resources.trainingcamp.Soldier;

public class SoldierTest extends TestCase 
{
	private Soldier soldier;
	
	public SoldierTest()
	{
		super();
	}
	
	protected void setUp() throws Exception
	{
		Unit unit = new UnitBean("Novice","JD");
		soldier = new Soldier(unit);
	}
	
	protected void tearDown() throws Exception
	{
		super.tearDown();
		soldier = null;
	}
	
	public void testSoldier()
	{
		assertNotNull("instance created", soldier);
	}
	
	public void testGetName() throws RemoteException
	{
		assertEquals("name is it ok ?","JD",soldier.getName());
	}
	
	public void testGetTitle() throws RemoteException
	{
		assertEquals("title is it ok ?","Soldier", soldier.getTitle());
	}
}