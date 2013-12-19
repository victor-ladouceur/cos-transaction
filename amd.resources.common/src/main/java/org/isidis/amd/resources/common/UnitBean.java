package org.isidis.amd.resources.common;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Unit. It's an implementation of the interface Unit.
 * @see Unit
 */
public class UnitBean extends UnicastRemoteObject implements Unit 
{
	private static final long serialVersionUID = 694743541204182812L;
	/**
	 * Title of the unit.
	 */
	private String title;
	/**
	 * Name of the unit.
	 */
	private String name;
	
	/**
	 * Constructor of a new unit.
	 * @param pTitle Title of the new unit.
	 * @param pName Name of the new unit.
	 * @throws RemoteException
	 */
	public UnitBean(String pTitle, String pName) throws RemoteException 
	{
		title = pTitle;
		name = pName;
	}
	/**
	 * Returns the name of the unit.
	 * @return The name of the unit.
	 * @throws RemoteException
	 */
	@Override
	public String getName() throws RemoteException 
	{
		return name;
	}
	/**
	 * Returns the title of the unit.
	 * @return The title of the unit.
	 * @throws RemoteException
	 */
	@Override
	public String getTitle() throws RemoteException 
	{
		return title;
	}
	/**
	 * Returns a textual presentation of the unit.
	 * @return A textual presentation of the unit.
	 * @throws RemoteException
	 */
	@Override
	public String present() 
	{
		return String.format("%s %s", title, name);
	}
}