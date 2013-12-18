package org.isidis.amd.resources.trainingcamp;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.isidis.amd.resources.common.Unit;
import org.isidis.amd.resources.common.UnitImpl;

public class Soldier extends UnicastRemoteObject implements Unit {
	private static final long serialVersionUID = 694743541204182812L;
	private Unit unit;
	public Soldier(Unit pUnit) throws RemoteException {
		if (!pUnit.getTitle().equals(NOVICE_TITLE))
			throw new IllegalArgumentException();
		
		unit = new UnitImpl(SOLDIER_TITLE, pUnit.getName());
	}
	@Override
	public String getName() throws RemoteException {
		return unit.getName();
	}
	@Override
	public String getTitle() throws RemoteException {
		return unit.getTitle();
	}
	@Override
	public String present() throws RemoteException {
		return unit.present();
	}
}
