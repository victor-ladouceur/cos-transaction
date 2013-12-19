package org.isidis.amd.cos.transactions.api;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import junit.framework.TestCase;

public class TransactionAPITest extends TestCase 
{
	private TransactionAPI instance;
	
	public TransactionAPITest()
	{
		super();
	}
	
	protected void setUp() throws MalformedURLException, RemoteException, NotBoundException, Exception
	{
		instance = TransactionAPI.init("10.10.64.173");
	}
	
	protected void tearDown() throws Exception
	{
		super.tearDown();
		instance = null;
	}
}