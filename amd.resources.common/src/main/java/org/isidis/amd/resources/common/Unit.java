package org.isidis.amd.resources.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Unit extends Remote 
{
	public static final String NOVICE_TITLE = "Novice";
	public static final String SOLDIER_TITLE = "Soldier";
	public static final String WARRIOR_TITLE = "Warrior";
	
	public String getName() throws RemoteException;
	public String getTitle() throws RemoteException;
	public String present() throws RemoteException;
}