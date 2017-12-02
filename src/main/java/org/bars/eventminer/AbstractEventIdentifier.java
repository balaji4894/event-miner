package org.bars.eventminer;

/**
 * 
 * @author Balaji
 *
 * @param <T>
 */
public abstract class AbstractEventIdentifier<T> implements Comparable<AbstractEventIdentifier<T>> {
	protected T eventIdentifier;
	public abstract T getEventIdentifier();
	public abstract void setEventIdentifier(T eventIdentifier);
	public AbstractEventIdentifier(){}
	public AbstractEventIdentifier(T eventidentifier){
		this.eventIdentifier = eventidentifier;
	}
}
