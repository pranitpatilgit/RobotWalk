package com.pranit.robot;

import java.util.ArrayList;
import java.util.List;

import com.pranit.robot.event.BatteryLowEvent;
import com.pranit.robot.event.Event;
import com.pranit.robot.event.OverweightEvent;
import com.pranit.robot.event.ScanFailedEvent;
import com.pranit.robot.exception.BatteryDownException;
import com.pranit.robot.exception.ScanFailureException;
import com.pranit.robot.observer.Observer;
import com.pranit.robot.observer.RobotOberserver;
import com.pranit.robot.service.ScannerService;
import com.pranit.robot.service.ScannerServiceImpl;
import com.pranit.robot.service.WalkService;
import com.pranit.robot.service.WalkServiceImpl;

public class Robot implements Observable{

	private String id;
	private int weight;

	private int battery;
	private double rateOfWalk;
	private int maxCapacity;
	
	private List<Observer> observers = new ArrayList<>();
	
	private WalkService walkService = WalkServiceImpl.getInstance();
	private ScannerService scannerService = ScannerServiceImpl.getInstance();
	
	public Robot(RobotBuilder robotBuilder) {
		this.id = robotBuilder.id;
		observers.add(RobotOberserver.getInstance());
		this.maxCapacity = robotBuilder.MAX_CAPACITY;
		if(robotBuilder.weight > maxCapacity){
			int total = this.weight + robotBuilder.weight;
			notifyObservers(new OverweightEvent("Robot Overweight. Cannot carry "+total
					+ ". Maximum Capacity is - "+maxCapacity));
			throw new RuntimeException("Robot Overweight...");
		}
			
		this.weight = robotBuilder.weight;
		this.battery = robotBuilder.battery;
		this.rateOfWalk = robotBuilder.rateOfWalk;
	}
	
	/**
	 * Calls overloaded walk(double distance)
	 */
	public void walk(){
		this.walk(this.battery * this.rateOfWalk);
	}
	
	/**
	 * Calls specific walkservice which is responsible for walking of robot
	 * @param km
	 */
	public void walk(double km) {
		try {
			walkService.walk(this, km);
		} catch (BatteryDownException e) {
			notifyObservers(new BatteryLowEvent(e.getMessage()));
		}
	}
	
	/**
	 * Calls scanner service 
	 * @param barcode
	 */
	public void scan(String barcode) {
		try {
			scannerService.scan(barcode);
		} catch (ScanFailureException e) {
			notifyObservers(new ScanFailedEvent("Scan failed for Robot - "+this.id+" for barcode - "+barcode+
					"\nReason for Failure - "+e.getMessage()));
		}
	}
	
	@Override
	public void notifyObservers(Event event) {
		for(Observer observer : observers){
			observer.action(event);
		}
	}

	public int getWeight() {
		return weight;
	}

	public void addWeight(int weight) {
		int total =this.weight + weight;
		if(total > maxCapacity){
			notifyObservers(new OverweightEvent("Robot Overweight. Cannot carry "+ total
					+ ". Maximum Capacity is - "+maxCapacity));
		}
		this.weight = total;
	}

	public int getBattery() {
		return battery;
	}

	public void reduceBattery(int reduceBy) {
		this.battery = this.battery - reduceBy;
		
		if(this.battery < 0) this.battery = 0;

		if (this.battery <= 15) {
			notifyObservers(new BatteryLowEvent("Battery for Robot - "+this.id + " is Low... Remaining Battery is "+this.getBattery()));
		}
	}

	public String getId() {
		return id;
	}

	public double getRateOfWalk() {
		return rateOfWalk;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}
	
	
	/**
	 * Builder class for Robot
	 * Mandatory parameter is id only
	 * Other parameters have their default values set which can be changed using builder
	 * 
	 * @author pranit
	 *
	 */
	public static class RobotBuilder {

		private String id;

		private int weight = 0;
		private int battery = 100;
		private double rateOfWalk = 5.0;
		private int MAX_CAPACITY = 10;
		
		public RobotBuilder(String id){
			this.id = id;
		}

		public RobotBuilder setWeight(int weight) {
			this.weight = weight;
			return this;
		}

		public RobotBuilder setBattery(int battery) {
			this.battery = battery;
			return this;
		}

		public RobotBuilder setRateOfWalk(double rateOfWalk) {
			this.rateOfWalk = rateOfWalk;
			return this;
		}

		public RobotBuilder setMAX_CAPACITY(int mAX_CAPACITY) {
			MAX_CAPACITY = mAX_CAPACITY;
			return this;
		}
		
		/**
		 * Creates new Robot instance
		 * 
		 * @return
		 */
		public Robot build(){
			return new Robot(this);
		}
		
	}

}
