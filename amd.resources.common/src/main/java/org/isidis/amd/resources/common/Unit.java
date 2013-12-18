package org.isidis.amd.resources.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Unit extends Remote {
	public static final String NOVICE_TITLE = "Novice";
	public static final String SOLDIER_TITLE = "Soldier";
	public static final String WARRIOR_TITLE = "Warrior";
	public String getName() throws RemoteException;
	public String getTitle() throws RemoteException;
	public String present() throws RemoteException;
}
/*
public abstract class Unit extends UnicastRemoteObject implements Remote {
	private static final long serialVersionUID = -1051379715205891524L;
	public static final String NAME_DEFAULT = "John Doe"; 
	private String name;
	public Unit() throws RemoteException {
		this(NAME_DEFAULT);
	}
	public Unit(String pName) throws RemoteException {
		setName(pName);
	}
	public void setName(String pName) throws RemoteException {
		name = pName;
	}
	public String getName() throws RemoteException {
		return name;
	}
	public String toString() {
		return String.format("%s %s", this.getClass().getSimpleName(), name);
	}
}
*/