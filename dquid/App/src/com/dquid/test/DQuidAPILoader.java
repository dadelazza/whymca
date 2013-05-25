package com.dquid.test;

import com.dquid.clientapi.UserVehicle;

public class DQuidAPILoader {

	public DQuidAPILoader() {
		// TODO Auto-generated constructor stub
	}

	
	public static void main(String[] args){
		DQuidEventListener dqListener = new DQuidEventListener();
		
		UserVehicle.INSTANCE.init(dqListener);
		UserVehicle.INSTANCE.setSimulationMode(true);
		UserVehicle.INSTANCE.connect(-1);
	}
}
