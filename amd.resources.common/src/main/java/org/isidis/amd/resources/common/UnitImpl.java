package org.isidis.amd.resources.common;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UnitImpl extends UnicastRemoteObject implements Unit {
	private static final long serialVersionUID = 694743541204182812L;
	private String title;
	private String name;
	public UnitImpl(String pTitle, String pName) throws RemoteException {
		title = pTitle;
		name = pName;
	}
	@Override
	public String getName() throws RemoteException {
		return name;
	}
	@Override
	public String getTitle() throws RemoteException {
		return title;
	}
	@Override
	public String present() {
		return String.format("%s %s", title, name);
	}
}
