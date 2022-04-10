package com.icastiblanco.trilateration.model;

import java.util.Arrays;

public class SatelliteInterception {
	private String name;
	private double distance;
	private String[] message;
	public SatelliteInterception(String name, double distance, String[] message) {
		this.name = name;
		this.distance = distance;
		this.message = message;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String[] getMessage() {
		return message;
	}
	public void setMessage(String[] message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "SatelliteInterception [name=" + name + ", distance=" + distance + ", message="
				+ Arrays.toString(message) + "]";
	}
}
