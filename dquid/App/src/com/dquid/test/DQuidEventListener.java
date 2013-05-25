package com.dquid.test;

import java.util.ArrayList;
import java.util.HashMap;

import com.dquid.clientapi.DQuidListnerInterface;
import com.dquid.clientapi.Data;

public class DQuidEventListener implements DQuidListnerInterface {

	public DQuidEventListener() {
		super();
	}

	@Override
	public void onConnectionSuccessful(int vehicleId) {
		System.out.println("Connected to vehicle id: " + vehicleId);

	}

	@Override
	public void onDisconnection() {
		System.out.println("Disconnected");

	}

	@Override
	public void onNewData(HashMap<String, Data> lastUpdatedData) {
		System.out.println("New Data Arrived: ");
		
		for(Data d : lastUpdatedData.values())
			System.out.println(d.toVerbose());

	}

	@Override
	public void onError(int errorCode) {
		System.out.println("Error " + errorCode + " occurred");

	}

	@Override
	public void onDtcCodesAvailable(ArrayList<String> troubleCodes) {
		System.out.println("Dtc Codes: ");
		
		for(String s : troubleCodes)
			System.out.println(s);

	}

	@Override
	public void onDtcNumberAvailable(int troubleCodesNumber) {
		System.out.println("Dtc Number: " + troubleCodesNumber);

	}

}
