package com.pranit.robot.event;

public class BatteryLowEvent extends Event{

	public BatteryLowEvent(String eventMessage) {
		super.message = eventMessage;
	}
}
