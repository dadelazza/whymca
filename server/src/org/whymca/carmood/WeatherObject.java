package org.whymca.carmood;

import java.util.Date;

import org.json.JSONObject;

public class WeatherObject {

	private Date timestamp;
	private String icon;
	private String weather;
	private String temperature;
	private String humidity;
	
	public WeatherObject(JSONObject json) {
		try {
			setTimestamp(javax.xml.bind.DatatypeConverter.parseDateTime(json.getString("timestamp")).getTime());			
			setIcon(json.getString("icon"));
			
			JSONObject weather = json.getJSONObject("weather");
			setWeather(weather.getString("status"));
			JSONObject measured = weather.getJSONObject("measured");
			setTemperature(measured.getString("temperature"));
			setHumidity(measured.getString("humidity"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
}
