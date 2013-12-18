package org.isidis.amd.cos.transactions;

import java.io.Serializable;

public class TransactionException extends Exception implements Serializable {
	private static final long serialVersionUID = 1L;
	public TransactionException() {
		super();
	}
	public TransactionException(String pMessage) {
		super(pMessage);
	}
}