package com.pranit.robot.event;

public class ScanFailedEvent extends Event{

	public ScanFailedEvent(String eventMessage) {
		super.message = eventMessage;
	}
}
