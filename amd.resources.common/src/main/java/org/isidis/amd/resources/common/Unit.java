package org.isidis.amd.resources.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface implemented by the concrete units.
 */
public interface Unit extends Remote 
{
	/**
	 * Title of the novices.
	 */
	public static final String NOVICE_TITLE = "Novice";
	/**
	 * Title of the soldiers.
	 */
	public static final String SOLDIER_TITLE = "Soldier";
	/**
	 * Title of the warriors.
	 */
	public static final String WARRIOR_TITLE = "Warrior";
	
	/**
	 * Returns the name of the unit.
	 * @return The name of the unit.
	 * @throws RemoteException
	 */
	public String getName() throws RemoteException;
	/**
	 * Returns the title of the unit.
	 * @return The title of the unit.
	 * @throws RemoteException
	 */
	public String getTitle() throws RemoteException;
	/**
	 * Returns a textual presentation of the unit.
	 * @return A textual presentation of the unit.
	 * @throws RemoteException
	 */
	public String present() throws RemoteException;
}