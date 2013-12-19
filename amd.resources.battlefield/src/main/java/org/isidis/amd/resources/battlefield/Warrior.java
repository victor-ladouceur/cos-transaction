package org.isidis.amd.resources.battlefield;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.isidis.amd.resources.common.Unit;
import org.isidis.amd.resources.common.UnitBean;

/**
 * Warrior unit.
 */
public class Warrior extends UnicastRemoteObject implements Unit 
{
	private static final long serialVersionUID = 694743541204182812L;
	/**
	 * Unit behind the warrior.
	 */
	private Unit unit;

	/**
	 * Constructor of a new soldier unit.
	 * @param pUnit Soldier who became a warrior. 
	 * @throws RemoteException
	 */
	public Warrior(Unit pUnit) throws RemoteException 
	{
		if (!pUnit.getTitle().equals(SOLDIER_TITLE)) throw new IllegalArgumentException();
		unit = new UnitBean(WARRIOR_TITLE, pUnit.getName());
	}
	/**
	 * Returns the name of the warrior unit.
	 * @return The name of the warrior unit.
	 * @throws RemoteException
	 */
	@Override
	public String getName() throws RemoteException 
	{
		return unit.getName();
	}
	/**
	 * Returns the title of the warrior unit.
	 * @return The title of the warrior unit.
	 * @throws RemoteException
	 */
	@Override
	public String getTitle() throws RemoteException 
	{
		return unit.getTitle();
	}
	/**
	 * Returns a textual presentation of the warrior unit.
	 * @return A textual presentation of the warrior unit.
	 * @throws RemoteException
	 */
	@Override
	public String present() throws RemoteException 
	{
		return unit.present();
	}
}