package org.isidis.amd.cos.transactions.api;

public class Configuration {
	public static final String TRANSACTIONS_MANAGER_HOST = "192.168.1.27";
	public static final int TRANSACTIONS_MANAGER_PORT = 1099;
	public static final String TRANSACTIONS_MANAGER_SERVICE = "tmanager";
	public static final String TRANSACTIONS_MANAGER_ADDRESS = String.format("rmi://%s:%s/%s/", TRANSACTIONS_MANAGER_HOST, TRANSACTIONS_MANAGER_PORT, TRANSACTIONS_MANAGER_SERVICE);
	private Configuration() {
		
	}
}
