package org.isidis.amd.cos.transactions.launcher;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import org.isidis.amd.cos.transactions.TransactionManager;
import org.isidis.amd.cos.transactions.TransactionManagerImpl;

public class TransactionServer {
	public static final String SERVICE_NAME = "tmanager";
	public static final int HOST_PORT = 1099;
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

			TransactionManager service = new TransactionManagerImpl();

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
