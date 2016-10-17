package com.pranit.robot;

import com.pranit.robot.event.Event;

public interface Observable {

	/**
	 * Notifies all the observers to take action on passed event.
	 * @param event
	 */
	public void notifyObservers(Event event);
}
