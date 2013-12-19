package org.isidis.amd.client.transaction.launcher;

public class Configuration 
{
	public static final String TRANSACTIONS_MANAGER_HOST = "10.10.64.173";
	public static final String SERVICES_HOST = "10.10.64.173";
	
	public static final int TRANSACTIONS_MANAGER_PORT = 1099;
	public static final String TRANSACTIONS_MANAGER_SERVICE = "tmanager";
	
	public static final int BASE_RESOURCE_PORT = 1098;
	public static final String BASE_RESOURCE_SERVICE = "base";
	
	public static final int TRAININGCAMP_RESOURCE_PORT = 1097;
	public static final String TRAININGCAMP_RESOURCE_SERVICE = "trainingcamp";
	
	public static final int BATTLEFIELD_RESOURCE_PORT = 1096;
	public static final String BATTLEFIELD_RESOURCE_SERVICE = "battlefield";

	public static final String TRANSACTIONS_MANAGER_ADDRESS = String.format("rmi://%s:%s/%s/", TRANSACTIONS_MANAGER_HOST, TRANSACTIONS_MANAGER_PORT, TRANSACTIONS_MANAGER_SERVICE);
	public static final String BASE_RESOURCE_ADDRESS = String.format("rmi://%s:%s/%s/", SERVICES_HOST, BASE_RESOURCE_PORT, BASE_RESOURCE_SERVICE);
	public static final String TRAININGCAMP_RESOURCE_ADDRESS = String.format("rmi://%s:%s/%s/", SERVICES_HOST, TRAININGCAMP_RESOURCE_PORT, TRAININGCAMP_RESOURCE_SERVICE);
	public static final String BATTLEFIELD_RESOURCE_ADDRESS = String.format("rmi://%s:%s/%s/", SERVICES_HOST, BATTLEFIELD_RESOURCE_PORT, BATTLEFIELD_RESOURCE_SERVICE);
	
	private Configuration() 
	{
		
	}
}