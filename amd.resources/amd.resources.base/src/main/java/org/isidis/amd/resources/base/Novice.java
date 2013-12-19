package org.isidis.amd.resources.base;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.isidis.amd.resources.common.Unit;
import org.isidis.amd.resources.common.UnitBean;

/**
 * Novice unit.
 */
public class Novice extends UnicastRemoteObject implements Unit 
{
	private static final long serialVersionUID = 694743541204182812L;
	/**
	 * Unit behind the novice.
	 */
	private Unit unit;
	
	/**
	 * Constructor of a new novice unit.
	 * @param pName Name of the new novice unit.
	 * @throws RemoteException
	 */
	public Novice(String pName) throws RemoteException 
	{
		unit = new UnitBean(Unit.NOVICE_TITLE, pName);
	}
	/**
	 * Returns the name of the novice unit.
	 * @return The name of the novice unit.
	 * @throws RemoteException
	 */
	@Override
	public String getName() throws RemoteException 
	{
		return unit.getName();
	}
	/**
	 * Returns the title of the novice unit.
	 * @return The title of the novice unit.
	 * @throws RemoteException
	 */
	@Override
	public String getTitle() throws RemoteException 
	{
		return unit.getTitle();
	}
	/**
	 * Returns a textual presentation of the novice unit.
	 * @return A textual presentation of the novice unit.
	 * @throws RemoteException
	 */
	@Override
	public String present() throws RemoteException 
	{
		return unit.present();
	}
}