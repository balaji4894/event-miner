package org.bars.eventminer.transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bars.eventminer.abstractions.AbstractEventIdentifier;
import org.bars.eventminer.events.Event;
import org.bars.eventminer.events.EventLink;
import org.bars.eventminer.exceptions.EventProcessingException;

/**
 * 
 * @author Balaji
 *
 * @param <T>
 * @param <U>
 * @param <V>
 */
public class Transaction<T, U, V> {

	private List<Event<T, U, V>> events;

	/**
	 * Constructor
	 * 
	 * @param events
	 */
	public Transaction(List<Event<T, U, V>> events) {
		this.events = events;
	}

	/**
	 * Provides the mined links between events, categorized against
	 * AbstractTransactionKey and Sorted by AbstractEventSorter
	 * 
	 * @return Map<T, List<EventLink<V>>> - Map of event links
	 * @throws EventProcessingException
	 */
	public Map<T, List<EventLink<V>>> generateEventLinks() throws EventProcessingException {
		Map<T, List<Event<T, U, V>>> categorizedEvents = this.categorizeEvents();
		Map<T, List<EventLink<V>>> categorizedEventLinks = new HashMap<T, List<EventLink<V>>>();

		categorizedEvents.forEach((key, value) -> {
			try {
				generateEventLinksSingleTransaction(key, value, categorizedEventLinks);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new EventProcessingException(e.getMessage());
			}
		});

		return categorizedEventLinks;
	}

	/**
	 * Not yet completed
	 * 
	 * @param eventLinks
	 * @return
	 */
	public List<AbstractEventIdentifier<V>> generateLineage(List<EventLink<V>> eventLinks) {
		List<AbstractEventIdentifier<V>> returnList = new ArrayList<AbstractEventIdentifier<V>>();
		return returnList;
	}

	/**
	 * Work in progress need to make them concurrent
	 * 
	 * @return
	 */
	private Map<T, List<Event<T, U, V>>> categorizeEvents() {
		Map<T, List<Event<T, U, V>>> categorizedEvents = new HashMap<T, List<Event<T, U, V>>>();

		this.events.forEach(s -> {
			categorizedEvents.computeIfAbsent(s.getTransactionKey().getTransactionKey(),
					key -> addToNewEventList(key, s));
			categorizedEvents.computeIfPresent(s.getTransactionKey().getTransactionKey(),
					(key, value) -> addToEventList(value, s));
		});

		return categorizedEvents;
	}

	/**
	 * Add more events to same key bucket, used for the lambda in
	 * categorizeEvents()
	 * 
	 * @param old
	 * @param event
	 * @return
	 */
	private List<Event<T, U, V>> addToEventList(List<Event<T, U, V>> old, Event<T, U, V> event) {
		old.add(event);
		return old;
	}

	/**
	 * Create new list for new keys, used for the lambda in categorizeEvents()
	 * 
	 * @param key
	 * @param event
	 * @return
	 */
	private List<Event<T, U, V>> addToNewEventList(T key, Event<T, U, V> event) {
		List<Event<T, U, V>> list = new ArrayList<Event<T, U, V>>();
		list.add(event);
		return list;
	}

	/**
	 * Very important method to mine the data, identifies the link between
	 * events based on the AbstractEventSorter
	 * 
	 * @param key
	 * @param events
	 * @param categorizedEventLinks
	 * @throws Exception
	 */
	private void generateEventLinksSingleTransaction(T key, List<Event<T, U, V>> events,
			Map<T, List<EventLink<V>>> categorizedEventLinks) throws Exception {
		List<Event<T, U, V>> eventsSorted = null;
		try {
			// A pit fall of having the sort method in EventIdentifier class,
			// need to call the method through one instance, this will fail if
			// there are no events
			eventsSorted = events.get(0).sort(events);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		List<EventLink<V>> linkedEvents = new ArrayList<EventLink<V>>();

		// How to improve this piece of code, through lambdas or something
		// This piece creates the link objects, based on current and previous
		// nodes in the sorted order
		for (int i = 0; i < eventsSorted.size() - 1; i++) {
			EventLink<V> obj = new EventLink<V>();
			obj.setSourceEventIdentifier(eventsSorted.get(i).getEventIdentifier());
			obj.setTargetEventIdentifier(eventsSorted.get(i + 1).getEventIdentifier());
			linkedEvents.add(obj);
		}

		categorizedEventLinks.put(key, linkedEvents);
	}
}
