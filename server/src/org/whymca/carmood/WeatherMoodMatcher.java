package org.whymca.carmood;

public class WeatherMoodMatcher {

	public static String getMoodByWeather(String weather) {
		if(weather.equals("rainy")) {
			return "lost in thought";
		} else if(weather.equals("clear")) {
			return "happy";
		} else if(weather.equals("snowy")) {
			return "christmas";
		} else if(weather.equals("foggy")) {
			return "foggy";
		}
		
		return "happy";
	}
	
}
