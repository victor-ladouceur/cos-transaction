package org.isidis.amd.resources.battlefield;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import org.isidis.amd.resources.common.BattleField;

/**
 * Application that launch the battlefield resource server.
 */
public class BattleFieldServer 
{
	public static final String SERVICE_NAME = "battlefield";
	public static final int HOST_PORT = 1096;
	public static String HOST_NAME;
	static 
	{
		try 
		{
			HOST_NAME = InetAddress.getLocalHost().getHostAddress();
		} 
		catch (UnknownHostException e) 
		{
			HOST_NAME = "localhost";
		}
	}

	public static void main(String[] args) 
	{
		try 
		{
			int hostPort = HOST_PORT;
			String hostName = HOST_NAME;
			String serviceName = SERVICE_NAME;	
			String serviceUrl = String.format("rmi://%s:%d/%s/", hostName, hostPort, serviceName);

			BattleField service = new BattleFieldBean();

			if (System.getSecurityManager() == null) 
			{
				System.setProperty("java.security.policy", "all.policy");
				System.setSecurityManager(new RMISecurityManager());
			}
			
			LocateRegistry.createRegistry(hostPort);
			Naming.rebind(serviceUrl, service);
			System.out.println(String.format("Service publication %s (%s)", serviceName, serviceUrl));
			System.out.println("Server started!");	
		} 
		catch (RemoteException e) 
		{
			e.printStackTrace();
		} 
		catch (MalformedURLException e) 
		{
			e.printStackTrace();
		}
	}
}