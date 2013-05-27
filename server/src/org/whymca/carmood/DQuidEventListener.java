package org.whymca.carmood;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
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
		
		for(Data d : lastUpdatedData.values()) {
			System.out.println(d.toVerbose());
			if(d.getName().equals("SignalNameSpeed")) {
				writeDatumOnFile(d.getValue(), "speed.txt");			
				try {
					Thread.sleep(5000);
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			} else if(d.getName().equals("SignalNameFrontLeftDoorStatus")) {
				writeDatumOnFile(d.getValue(), "frontLeftDoor.txt");
			} else if(d.getName().equals("SignalNameFrontRightDoorStatus")) {
				writeDatumOnFile(d.getValue(), "frontRightDoor.txt");
			} else if(d.getName().equals("SignalNameBackRightDoorStatus")) {
				writeDatumOnFile(d.getValue(), "backRightDoor.txt");
			} else if(d.getName().equals("SignalNameBackLeftDoorStatus")) {
				writeDatumOnFile(d.getValue(), "backLeftDoor.txt");
			} else if(d.getName().equals("SignalNameHeadlightsStatus")) {
				writeDatumOnFile(d.getValue(), "headlights.txt");
			} 
		}		
	}
	
	private void writeDatumOnFile(Object value, String filename) {
		PrintStream out = null;
		try {
			out = new PrintStream(new FileOutputStream(new File(filename)));
			String valStr = value.toString() + "\n";
			out.write(valStr.getBytes());
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if(out != null) {
				out.close();
			}
		}
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
