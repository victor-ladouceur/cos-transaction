package org.isidis.amd.cos.transactions;

import java.io.Serializable;

/**
 * Exception that can be thrown during the usage of transactions.
 */
public class TransactionException extends Exception implements Serializable {
	private static final long serialVersionUID = -1732622011824368738L;
	public TransactionException() {
		super();
	}
	public TransactionException(String pMessage) {
		super(pMessage);
	}
}