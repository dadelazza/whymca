package org.whymca.carmood;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class CarData {

	private String speed;
	private String frontLeftDoor;
	private String backLeftDoor;
	private String frontRightDoor;
	private String backRightDoor;
	private String headlights;
	
	public void load() {
		try {
			setSpeed(loadFromFile("speed.txt"));
			setFrontLeftDoor(loadFromFile("frontLeftDoor.txt"));
			setFrontRightDoor(loadFromFile("frontRightDoor.txt"));
			setBackLeftDoor(loadFromFile("backLeftDoor.txt"));
			setBackRightDoor(loadFromFile("backRightDoor.txt"));
			setHeadlights(loadFromFile("headlights.txt"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String loadFromFile(String filename) throws Exception {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filename))));
			String line = in.readLine();			
			return line;
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if(in != null) {
				in.close();
			}
		}
		return "";
	}
	
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getFrontLeftDoor() {
		return frontLeftDoor;
	}
	public void setFrontLeftDoor(String frontLeftDoor) {
		this.frontLeftDoor = frontLeftDoor;
	}
	public String getBackLeftDoor() {
		return backLeftDoor;
	}
	public void setBackLeftDoor(String backLeftDoor) {
		this.backLeftDoor = backLeftDoor;
	}
	public String getFrontRightDoor() {
		return frontRightDoor;
	}
	public void setFrontRightDoor(String frontRightDoor) {
		this.frontRightDoor = frontRightDoor;
	}
	public String getBackRightDoor() {
		return backRightDoor;
	}
	public void setBackRightDoor(String backRightDoor) {
		this.backRightDoor = backRightDoor;
	}
	public String getHeadlights() {
		return headlights;
	}
	public void setHeadlights(String headlights) {
		this.headlights = headlights;
	}		
}
