package org.isidis.amd.cos.transactions.api;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

import org.isidis.amd.cos.transactions.InvocationBean;
import org.isidis.amd.cos.transactions.Transaction;
import org.isidis.amd.cos.transactions.TransactionFactory;
import org.isidis.amd.cos.transactions.TransactionResource;

/**
 * Invocation handler of a proxy instance used for transactions.
 */
public class ProxyInvocationHandler implements InvocationHandler 
{
	/**
	 * Static list of methods of the interface TransactionResource.
	 */
	private static final Method []transactionsMethods = TransactionResource.class.getMethods();
	/** 
	 * Know if the method given in the call is a method of the TransactionResource interface.
	 * @return True if the method given in the call is a method of the TransactionResource interface.
	 */
	public static boolean isTransactionMethod(Method pMethod) 
	{
		for (Method m : transactionsMethods)
			if (m.getName().equals(pMethod.getName())) 
				return true;
		return false;
	}
	
	
	/**
	 * Interface class of the remote resource.
	 */
	private Class<?> resourceInterface;
	/**
	 * Remote resource/
	 */
	private Object bean;
	/**
	 * The transaction factory of the API.
	 */
	private TransactionFactory tfactory;
	
	/**
	 * Constructor of a proxy invocation handler.
	 * @param pResourceInterface Interface class of the remote resource.
	 * @param pBean Remote resource.
	 * @param pTfactory The transaction factory of the API.
	 */
	public ProxyInvocationHandler(Class<?> pResourceInterface, Object pBean, TransactionFactory pTfactory) 
	{
		resourceInterface = pResourceInterface;
		tfactory = pTfactory;
		bean = pBean;
	}
	/**
	 * Invocation interceptor.
	 * @param pProxy The object that call the intercepted method.
	 * @param pMethod The intercepted method.
	 * @param pArgs The arguments of the intercepted method.
	 * @throws Throwable
	 */
	public Object invoke(Object pProxy, Method pMethod, Object []pArgs) throws Throwable 
	{
		if (isTransactionMethod(pMethod)) throw new RuntimeException("Illegal call: only transaction can manipulate a resource");
		
		int interceptedInvocations = 0;
		List<Transaction> transactions = tfactory.getTransactions((TransactionResource) bean);
		if (transactions != null && transactions.size() > 0) 
		{
			for (Transaction transaction : transactions) 
			{
				if (transaction.hasStarted()) 
				{
					transaction.addInvocation(new InvocationBean(bean, pMethod, pArgs));
					++interceptedInvocations;
				}
			}
			
			if (interceptedInvocations > 0)
				System.out.println(
						String.format(
								"Invocation %s.%s() has been intercepted by %d transaction%s",
								resourceInterface.getSimpleName(),
								pMethod.getName(),
								interceptedInvocations,
								(interceptedInvocations == 1)?"":"s"
							)
						);
		}
		return pMethod.invoke(bean, pArgs);
	}
}