package com.icastiblanco.trilateration.model;

public class TopSecretShipSplit {
	private double distance;
	private String[] message;
	
	public TopSecretShipSplit(double distance, String[] message) {
		this.distance = distance;
		this.message = message;
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
