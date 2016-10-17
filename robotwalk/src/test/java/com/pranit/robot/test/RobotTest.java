package com.pranit.robot.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.pranit.robot.Robot;

@RunWith(MockitoJUnitRunner.class)
public class RobotTest {

	@Test
	public void testRobot3km(){
		Robot robot = new Robot.RobotBuilder("Robot3.5").build();
		robot.walk(3.5);
		System.out.println("Remaining Battery - "+robot.getBattery());
		Assert.assertEquals(99, robot.getBattery());
	}
	
	@Test
	public void testRobot2km3kg(){
		Robot robot = new Robot.RobotBuilder("Robot2/3").setWeight(3).build();
		robot.walk(2);
		System.out.println("Remaining Battery - "+robot.getBattery());
		Assert.assertEquals(99, robot.getBattery());
	}
	
	@Test(expected=RuntimeException.class)
	public void testRobot12kg(){
		Robot robot = new Robot.RobotBuilder("Robot12").setWeight(12).build();
		robot.walk(2);
		System.out.println("Remaining Battery - "+robot.getBattery());
	}
	
	@Test
	public void testRobotScan(){
		Robot robot = new Robot.RobotBuilder("RobotScan").build();
		robot.scan("|||||");
	}
	
	@Test
	public void testRobot(){
		Robot robot = new Robot.RobotBuilder("RobotPranit").setWeight(2).build();
		robot.walk();
	}
}
