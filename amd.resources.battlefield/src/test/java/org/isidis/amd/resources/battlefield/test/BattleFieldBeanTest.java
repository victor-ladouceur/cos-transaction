package org.isidis.amd.resources.battlefield.test;

import java.rmi.RemoteException;

import junit.framework.TestCase;

import org.isidis.amd.resources.battlefield.BattleFieldBean;
import org.isidis.amd.resources.battlefield.Warrior;
import org.isidis.amd.resources.common.Unit;
import org.isidis.amd.resources.common.UnitBean;

public class BattleFieldBeanTest extends TestCase 
{
	private BattleFieldBean instance;
	
	public BattleFieldBeanTest()
	{
		super();
	}
	
	protected void setUp() throws Exception
	{
		instance = new BattleFieldBean();
	}
	
	protected void tearDown() throws Exception
	{
		super.tearDown();
	}
	
	public void testTrain() throws RemoteException
	{
		Unit soldier = instance.deploy(new UnitBean("Soldier","JD"));
		assertTrue("Is it a soldier ?",soldier instanceof Warrior);
	}
}