package org.bars.eventminer;

/**
 * 
 * @author Balaji
 *
 * @param <T>
 */
public class EventLink<T> implements Comparable<EventLink<T>> {

	private AbstractEventIdentifier<T> sourceEventIdentifier;
	private AbstractEventIdentifier<T> targetEventIdentifier;

	public AbstractEventIdentifier<T> getSourceEventIdentifier() {
		return sourceEventIdentifier;
	}

	public void setSourceEventIdentifier(AbstractEventIdentifier<T> sourceEventIdentifier) {
		this.sourceEventIdentifier = sourceEventIdentifier;
	}

	public AbstractEventIdentifier<T> getTargetEventIdentifier() {
		return targetEventIdentifier;
	}

	public void setTargetEventIdentifier(AbstractEventIdentifier<T> targetEventIdentifier) {
		this.targetEventIdentifier = targetEventIdentifier;
	}

	public int compareTo(EventLink<T> o) {
		// TODO Auto-generated method stub
		return this.sourceEventIdentifier.compareTo(o.sourceEventIdentifier)
				- this.targetEventIdentifier.compareTo(o.targetEventIdentifier);
	}

}
