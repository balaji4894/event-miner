package org.bars.eventminer.exceptions;

public class EventProcessingException extends RuntimeException {
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -475153284984116162L;

	public EventProcessingException(){
		super();
	}
	
	public EventProcessingException(String message){
		super(message);
	}
}
