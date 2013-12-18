package org.isidis.amd.resources.base;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import org.isidis.amd.resources.common.Base;

public class BaseServer {
	public static final String SERVICE_NAME = "base";
	public static final int HOST_PORT = 1098;
	public static String HOST_NAME;
	static {
		try {
			HOST_NAME = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			HOST_NAME = "localhost";
		}
	}

	public static void main(String[] args) {
		try {
			int hostPort = HOST_PORT;
			String hostName = HOST_NAME;
			String serviceName = SERVICE_NAME;
			
			String serviceUrl = String.format("rmi://%s:%d/%s/", hostName, hostPort, serviceName);

			Base service = new BaseBean();

			if (System.getSecurityManager() == null) {
				System.setProperty("java.security.policy", "all.policy");
				System.setSecurityManager(new RMISecurityManager());
			}
			
			LocateRegistry.createRegistry(hostPort);
			
			Naming.rebind(serviceUrl, service);
			
			System.out.println(String.format("Publication du service %s (%s)", serviceName, serviceUrl));
			
			System.out.println("Serveur démarré!");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
