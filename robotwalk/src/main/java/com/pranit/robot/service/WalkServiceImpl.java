package com.pranit.robot.service;

import com.pranit.robot.Robot;
import com.pranit.robot.exception.BatteryDownException;

public class WalkServiceImpl implements WalkService{

	private static final WalkServiceImpl instance = new WalkServiceImpl();
	
	private WalkServiceImpl(){}
	
	public static WalkServiceImpl getInstance() {
		return instance;
	}
	
	@Override
	public void walk(Robot robot, double km) throws BatteryDownException {
		System.out.print("Walking...");
		double dist = 0;
		try {
			while(robot.getBattery() > 0 && km != 0.0){
				System.out.print(".");
				double distance = Math.min(km, robot.getRateOfWalk());
				double weightFactor = robot.getWeight()==0 ? 1 : 1.2 * robot.getWeight();
				int batteryConsumption = (int) Math.round(((distance / robot.getRateOfWalk()) * weightFactor));
				dist = dist + distance;
				km = km - distance;
				robot.reduceBattery(batteryConsumption);
				Thread.sleep(250);
			}
			
			System.out.println();
			
			if(robot.getBattery() <= 0){
				System.out.println("Robot - "+robot.getId()+" walked - "+dist+" km and battery went down.");
				throw new BatteryDownException("Battery is Zero for Robot - "+robot.getId());
			}
			else
				System.out.println("Robot - "+robot.getId()+" walked for- "+dist+" km.");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
