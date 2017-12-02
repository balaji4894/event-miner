package org.bars.eventminer;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Balaji
 *
 * @param <T>
 * @param <U>
 * @param <V>
 */
public class Event<T, U, V> implements Comparable<Event<T, U, V>> {

	/**
	 * Private properties of an event
	 */

	private AbstractTransactionKey<T> transactionKey;
	private AbstractEventSorter<U> eventSorter;
	private AbstractEventIdentifier<V> eventIdentifier;
	private Map<?, ?> nonCriticalAttributes;

	/**
	 * Getters and setters of private properties
	 */

	public AbstractTransactionKey<T> getTransactionKey() {
		return transactionKey;
	}

	public void setTransactionKey(AbstractTransactionKey<T> transactionKey) {
		this.transactionKey = transactionKey;
	}

	public AbstractEventIdentifier<V> getEventIdentifier() {
		return eventIdentifier;
	}

	public void setEventIdentifier(AbstractEventIdentifier<V> eventIdentifier) {
		this.eventIdentifier = eventIdentifier;
	}
	
	public AbstractEventSorter<U> getEventSorter() {
		return eventSorter;
	}

	public void setEventSorter(AbstractEventSorter<U> eventSorter) {
		this.eventSorter = eventSorter;
	}
	
	public Map<?, ?> getNonCriticalAttributes() {
		return nonCriticalAttributes;
	}

	public void setNonCriticalAttributes(Map<?, ?> nonCriticalAttributes) {
		this.nonCriticalAttributes = nonCriticalAttributes;
	}
	
	/**
	 * Constructor
	 * @param transactionKey
	 * @param eventSortingproperty
	 * @param eventIdentifier
	 */
	public Event(AbstractTransactionKey<T> transactionKey, AbstractEventSorter<U> eventSortingproperty,
			AbstractEventIdentifier<V> eventIdentifier) {
		this.setTransactionKey(transactionKey);
		this.setEventIdentifier(eventIdentifier);
		this.setEventSorter(eventSortingproperty);
		this.setNonCriticalAttributes(new HashMap<String, String>());
	}
	
	/**
	 * Sorts a given set of events in the parameter
	 * @param events
	 * @return
	 */
	public List<Event<T, U, V>> sort(List<Event<T, U, V>> events) {
		try {
			Collections.sort(events);
			return events;
		} catch (Exception e) {
			return null;
		}

	}
	
	/**
	 * Compare to method determines how the events will be sorted, it delegates the event sorting to the compareTo of AbstractEventSorting
	 */
	public int compareTo(Event<T, U, V> o) {
		return this.getEventSorter().compareTo(o.getEventSorter());
	}
}
