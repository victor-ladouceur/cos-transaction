package org.isidis.amd.resources.trainingcamp.test;

import java.rmi.RemoteException;

import junit.framework.TestCase;

import org.isidis.amd.resources.common.Unit;
import org.isidis.amd.resources.common.UnitBean;
import org.isidis.amd.resources.trainingcamp.Soldier;
import org.isidis.amd.resources.trainingcamp.TrainingCampBean;

public class TrainingCampBeanTest extends TestCase
{
	private TrainingCampBean instance;
	
	public TrainingCampBeanTest()
	{
		super();
	}
	
	protected void setUp() throws Exception
	{
		instance = new TrainingCampBean();
	}
	
	protected void tearDown() throws Exception
	{
		super.tearDown();
	}
	
	public void testTrain() throws RemoteException
	{
		Unit soldier = instance.train(new UnitBean("Novice","JD"));
		assertTrue("Is it a soldier ?",soldier instanceof Soldier);
	}
}