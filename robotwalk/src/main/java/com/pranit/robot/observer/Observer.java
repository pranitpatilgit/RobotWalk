package com.pranit.robot.observer;

import com.pranit.robot.event.Event;

public interface Observer {

	/**
	 * Observer uses this method to take action for the event specified as argument.
	 * @param event
	 */
	public void action(Event event);
}
