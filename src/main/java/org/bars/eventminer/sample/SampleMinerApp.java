package org.bars.eventminer.sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bars.eventminer.AbstractEventIdentifier;
import org.bars.eventminer.AbstractEventSorter;
import org.bars.eventminer.AbstractTransactionKey;
import org.bars.eventminer.Event;
import org.bars.eventminer.EventLink;
import org.bars.eventminer.Transaction;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;



public class SampleMinerApp {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		List<Event<String, Long, String>> events = generateRamdomEvents();
		printObjectOnConsole(events);
		
		Transaction<String, Long, String> transactions = new Transaction<String, Long, String>(events);
		printObjectOnConsole(transactions);
		
		Map<String, List<EventLink<String>>> links = transactions.generateEventLinks();
		printObjectOnConsole(links);
	}
	
	/**
	 * Returns a psuedo-random number between min and max, inclusive.
	 * The difference between min and max can be at most
	 * <code>Integer.MAX_VALUE - 1</code>.
	 *
	 * @param min Minimim value
	 * @param max Maximim value.  Must be greater than min.
	 * @return Integer between min and max, inclusive.
	 * @see java.util.Random#nextInt(int)
	 */
	public static int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	private static List<Event<String, Long, String>> generateRamdomEvents() throws InterruptedException{
		String[] eventidentifierNames = {"START","STEP1","STEP3","STEP4","STEP5","END"};
		List<Event<String, Long, String>> events = new ArrayList<Event<String, Long, String>>();
		int i=0;
		while(i<50){
			int randindex = SampleMinerApp.randInt(0, eventidentifierNames.length-1);
			int randTransactionKey = SampleMinerApp.randInt(1, 4);
			AbstractTransactionKey<String> obj1 = new TransactionKey(randTransactionKey+"");
			Thread.sleep(randindex*50L);
			AbstractEventSorter<Long> obj2 = new EventSortingproperty(new Date().getTime());
			AbstractEventIdentifier<String> obj3 = new EventIdentifier(eventidentifierNames[randindex]);
			Event<String, Long, String> event = new Event<String, Long, String>(obj1, obj2, obj3);
			i++;
			events.add(event);
		}
		
		return events;
	}
	
	private static void printObjectOnConsole(Object obj){
		try {
			ObjectMapper mapperObj = new ObjectMapper();
            // get Employee object as a json string
			mapperObj.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
            String jsonStr = mapperObj.writeValueAsString(obj);
            System.out.println(jsonStr);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
}

class EventIdentifier extends AbstractEventIdentifier<String> {
	public int compareTo(AbstractEventIdentifier<String> arg0) {
		// TODO Auto-generated method stub
		return this.eventIdentifier.compareTo(arg0.getEventIdentifier());
	}

	@Override
	public String getEventIdentifier() {
		// TODO Auto-generated method stub
		return this.eventIdentifier;
	}

	@Override
	public void setEventIdentifier(String eventIdentifier) {
		// TODO Auto-generated method stub
		this.eventIdentifier = eventIdentifier;
	}

	EventIdentifier(String arg0) {
		super(arg0);
	}
}

class TransactionKey extends AbstractTransactionKey<String> {

	public int compareTo(String o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getTransactionKey() {
		// TODO Auto-generated method stub
		return this.transactionKey;
	}

	@Override
	public void setTransactionKey(String transactionKey) {
		// TODO Auto-generated method stub
		this.transactionKey = transactionKey;
	}

	TransactionKey(String arg0) {
		super(arg0);
	}

}

class EventSortingproperty extends AbstractEventSorter<Long> {

	public int compareTo(AbstractEventSorter<Long> o) {
		// TODO Auto-generated method stub
		return this.sortElement.compareTo(o.getSortElement());
	}

	@Override
	public Long getSortElement() {
		// TODO Auto-generated method stub
		return this.sortElement;
	}

	@Override
	public void setSortElement(Long sortElement) {
		// TODO Auto-generated method stub
		this.sortElement = sortElement;
	}
	
	EventSortingproperty(Long l){
		super(l);
	}

}