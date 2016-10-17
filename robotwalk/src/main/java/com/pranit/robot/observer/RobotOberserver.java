package com.pranit.robot.observer;

import com.pranit.robot.event.Event;

public class RobotOberserver implements Observer{

	private static final RobotOberserver instance = new RobotOberserver();
	
	private RobotOberserver(){}
	
	public static RobotOberserver getInstance() {
		return instance;
	}
	
	@Override
	public void action(Event event) {
		System.out.println(event.getMessage());
	}
}
