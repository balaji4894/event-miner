package org.bars.eventminer.abstractions;

/**
 * 
 * @author Balaji
 *
 * @param <T>
 */
public abstract class AbstractTransactionKey<T> implements Comparable<T> {
	protected T transactionKey;

	public abstract T getTransactionKey();
	public abstract void setTransactionKey(T transactionKey);
	public AbstractTransactionKey(){}
	public AbstractTransactionKey(T transactionKey){
		this.transactionKey = transactionKey;
	}
}
