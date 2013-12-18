package org.isidis.amd.cos.transactions.api;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.net.MalformedURLException;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import org.isidis.amd.cos.transactions.Transaction;
import org.isidis.amd.cos.transactions.TransactionException;
import org.isidis.amd.cos.transactions.TransactionFactory;
import org.isidis.amd.cos.transactions.TransactionManager;
import org.isidis.amd.cos.transactions.TransactionResource;

public class TransactionAPI 
{
	private static final long serialVersionUID = 9134600758008409512L;
	private Map<Class<?>, Object> resources;
	private TransactionManager tmanager;
	private TransactionFactory tfactory;

	/** Holder of the unique instance of the TransactionAPI class */
	private static class TransactionAPIHolder 
	{
		/** The unique instance of the TransactionAPI class */
		private final static TransactionAPI instance;
		static 
		{
			try 
			{
				instance = new TransactionAPI();
			} 
			catch (Exception e) 
			{
				throw new RuntimeException("The COS Transaction can't be reached!");
			}
		}
	}

	/**
	 * Private constructor of the API (the TransactionAPI class can't be instantiated)
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	private TransactionAPI() throws MalformedURLException, RemoteException, NotBoundException 
	{
		resources = new HashMap<Class<?>, Object>();
		tmanager = (TransactionManager) Naming.lookup(Configuration.TRANSACTIONS_MANAGER_ADDRESS);
		tfactory = tmanager.getTransactionFactory();
		// shutdown hook
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					// cancel transactions not closed
					for (Transaction t : tfactory.getTransactions())
						if (t.hasStarted())
							t.rollback();	
				} 
				catch (RemoteException e) 
				{
					e.printStackTrace();
				} 
				catch (TransactionException e) 
				{
					e.printStackTrace();
				}
			}
		}));
	}

	/**
	 * Return the API Transaction
	 * @return The unique instance of the TransactionAPI class
	 * @throws MalformedURLException If the URL is malformed
	 * @throws RemoteException 
	 * @throws NotBoundException
	 */
	public static TransactionAPI init() throws MalformedURLException, RemoteException, NotBoundException 
	{
		return TransactionAPIHolder.instance;
	}
	/**
	 * Register a remote resource and return the proxy on it
	 * @param pResource Interface class of the remote resource
	 * @param pResourceUrl URL of the remote resource
	 * @return Proxy on the remote resource given in the call
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public <T> T registerAsResource(Class<T> pResourceInterface, String pResourceUrl) throws MalformedURLException, RemoteException, NotBoundException {
		try 
		{
			T bean = (T) Naming.lookup(pResourceUrl);
			if (!pResourceInterface.isInstance(bean))
				throw new RuntimeException(String.format("The given resource interface (%s) doesn't match with the remote resource", pResourceInterface.getSimpleName()));

			if (resources.containsKey(pResourceInterface))
				throw new RuntimeException(String.format("The resource interface (%s) is already registered", pResourceInterface.getSimpleName()));

			InvocationHandler handler = new ProxyInvocationHandler(pResourceInterface, bean, tfactory);
			T proxy = (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[] { pResourceInterface }, handler);
			resources.put(pResourceInterface, bean);
			return proxy;
		} 
		catch (ConnectException e) 
		{
			throw new ConnectException(String.format("The remote resource (%s) can't be reached", pResourceInterface.getSimpleName()), e);
		}
	}
	/**
	 * Know if a resource is registered in the API
	 * @param pResource Tested resource
	 * @return Indicate if a resource is registered in the API
	 */
	public boolean isRegisteredResource(Object pResource) 
	{
		for (Object i : pResource.getClass().getInterfaces())
			if (resources.containsKey(i))
				return true;
		return false;
	}
	/**
	 * Create a transaction
	 * @return A new transaction
	 * @throws RemoteException
	 */
	public Transaction createTransaction() throws RemoteException 
	{
		return tfactory.createTransaction();
	}
	/**
	 * Create a transaction attached with remote resources given in the call
	 * @param pResources Resources to attach on the new transaction
	 * @return A new transaction attached with remote sources
	 * @throws RemoteException
	 */
	public Transaction createTransaction(Object ...pResources) throws RemoteException 
	{		
		Transaction transaction = createTransaction();
		for (Object resource : pResources)
			attachResource(transaction, resource);
		return transaction;
	}
	/**
	 * Attach a remote resource on a transaction
	 * @param pTransaction The transaction attached with the remote source given in the call
	 * @param pResource Remote resource to attach on the transaction given in the call
	 * @throws RemoteException
	 */
	public void attachResource(Transaction pTransaction, Object pResource) throws RemoteException 
	{
		if (!isRegisteredResource(pResource))
			throw new RuntimeException("A given resource isn't registered in the API");
		Object bean = null;
		for (Object i : pResource.getClass().getInterfaces()) 
		{
			if (resources.containsKey(i)) 
			{
				bean = resources.get(i);
				break;
			}
		}
		pTransaction.getCoordinator().registerResource((TransactionResource) bean);
	}
}