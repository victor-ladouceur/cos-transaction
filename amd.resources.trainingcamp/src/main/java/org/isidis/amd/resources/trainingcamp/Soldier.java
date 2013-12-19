package org.isidis.amd.resources.trainingcamp;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.isidis.amd.resources.common.Unit;
import org.isidis.amd.resources.common.UnitBean;

/**
 * Soldier unit.
 */
public class Soldier extends UnicastRemoteObject implements Unit 
{
	private static final long serialVersionUID = 694743541204182812L;
	/**
	 * Unit behind the soldier.
	 */
	private Unit unit;
	
	/**
	 * Constructor of a new soldier unit.
	 * @param pUnit Novice who became a warrior.
	 * @throws RemoteException
	 */
	public Soldier(Unit pUnit) throws RemoteException 
	{
		if (!pUnit.getTitle().equals(NOVICE_TITLE))throw new IllegalArgumentException();
		unit = new UnitBean(SOLDIER_TITLE, pUnit.getName());
	}
	/**
	 * Returns the name of the soldier unit.
	 * @return The name of the soldier unit.
	 * @throws RemoteException
	 */
	@Override
	public String getName() throws RemoteException 
	{
		return unit.getName();
	}
	/**
	 * Returns the title of the soldier unit.
	 * @return The title of the soldier unit.
	 * @throws RemoteException
	 */
	@Override
	public String getTitle() throws RemoteException 
	{
		return unit.getTitle();
	}
	/**
	 * Returns a textual presentation of the soldier unit.
	 * @return A textual presentation of the soldier unit.
	 * @throws RemoteException
	 */
	@Override
	public String present() throws RemoteException 
	{
		return unit.present();
	}
}