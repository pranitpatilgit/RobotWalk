package com.pranit.robot.service;

import com.pranit.robot.Robot;
import com.pranit.robot.exception.BatteryDownException;

public interface WalkService {

	/**
	 * REsponsible for walking of robot
	 * 
	 * @param robot
	 * @param km
	 * @throws BatteryDownException
	 */
	public void walk(Robot robot, double km) throws BatteryDownException;
}
