package com.icastiblanco.trilateration.model;

public class TopSecretShipSplit {
	private Satellite satellite;
	private double distance;
	private String[] message;
	
	public TopSecretShipSplit(Satellite satellite, double distance, String[] message) {
		this.satellite = satellite;
		this.distance = distance;
		this.message = message;
	}

	public Satellite getSatellite() {
		return satellite;
	}
	public void setSatellite(Satellite satellite) {
		this.satellite = satellite;
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
}
