package org.whymca.carmood;

import com.dquid.clientapi.UserVehicle;

public class SpeedPoller implements Runnable {

	public void run() {
		DQuidEventListener dqListener = new DQuidEventListener();
		
		UserVehicle.INSTANCE.init(dqListener);
		UserVehicle.INSTANCE.setSimulationMode(true);
		UserVehicle.INSTANCE.connect(-1);
	}
	
	
}
