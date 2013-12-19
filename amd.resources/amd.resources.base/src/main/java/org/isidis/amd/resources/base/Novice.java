package org.isidis.amd.resources.base;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.isidis.amd.resources.common.Unit;
import org.isidis.amd.resources.common.UnitBean;

public class Novice extends UnicastRemoteObject implements Unit 
{
	private static final long serialVersionUID = 694743541204182812L;
	private Unit unit;
	
	public Novice(String pName) throws RemoteException 
	{
		unit = new UnitBean(Unit.NOVICE_TITLE, pName);
	}
	
	@Override
	public String getName() throws RemoteException 
	{
		return unit.getName();
	}
	
	@Override
	public String getTitle() throws RemoteException 
	{
		return unit.getTitle();
	}
	
	@Override
	public String present() throws RemoteException 
	{
		return unit.present();
	}
}