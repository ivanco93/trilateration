package com.icastiblanco.trilateration.model;

import java.util.List;

public class TopSecret {
	List<SatelliteInterception> satellites;
	
	public TopSecret() {
		
	}
	public TopSecret(List<SatelliteInterception> satellites) {
		this.satellites = satellites;
	}

	public List<SatelliteInterception> getSatellites() {
		return satellites;
	}

	public void setSatellites(List<SatelliteInterception> satellites) {
		this.satellites = satellites;
	}
	@Override
	public String toString() {
		return "TopSecret [satellites=" + satellites + "]";
	}
}
