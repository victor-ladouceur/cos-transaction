package org.isidis.amd.cos.transactions.api;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

import org.isidis.amd.cos.transactions.InvocationBean;
import org.isidis.amd.cos.transactions.Transaction;
import org.isidis.amd.cos.transactions.TransactionFactory;
import org.isidis.amd.cos.transactions.TransactionResource;

public class ProxyInvocationHandler implements InvocationHandler, Serializable 
{
	private static final long serialVersionUID = -2597006015044220992L;
	private static final Method []transactionsMethods = TransactionResource.class.getMethods();
	private Class<?> resourceInterface;
	private Object bean;
	private TransactionFactory tfactory;
	
	public ProxyInvocationHandler(Class<?> pResourceInterface, Object pBean, TransactionFactory pTfactory) 
	{
		resourceInterface = pResourceInterface;
		tfactory = pTfactory;
		bean = pBean;
	}
	
	public boolean isTransactionMethod(Method pMethod) 
	{
		for (Method m : transactionsMethods)
			if (m.getName().equals(pMethod.getName())) 
				return true;
		return false;
	}
	
	public Object invoke(Object pProxy, Method pMethod, Object []pArgs) throws Throwable 
	{
		if (isTransactionMethod(pMethod)) throw new RuntimeException("Illegal call: only transaction can manipulate a resource");
		
		int interceptedInvocations = 0;
		List<Transaction> transactions = tfactory.getTransactions(bean);
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