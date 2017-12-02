package org.bars.eventminer;

/**
 * 
 * @author Balaji
 *
 * @param <T>
 */
public abstract class AbstractEventSorter<T> implements Comparable<AbstractEventSorter<T>> {
	protected T sortElement;

	public abstract T getSortElement();
	public abstract void setSortElement(T sortElement);
	public AbstractEventSorter(){}
	public AbstractEventSorter(T sortElement){
		this.sortElement=sortElement;
	}
}
