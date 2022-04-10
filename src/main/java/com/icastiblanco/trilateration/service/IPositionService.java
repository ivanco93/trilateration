package com.icastiblanco.trilateration.service;

import java.util.List;

import com.icastiblanco.trilateration.model.Position;
import com.icastiblanco.trilateration.model.Satellite;


public interface IPositionService {
	Position getShipCoordinates(List<Satellite> satellites);
	int getSatelliteInOriginIndex(List<Satellite> satellites);
	int getSatelliteInParallelWithOriginIndex(List<Satellite> satellites, int satelliteInOriginIndex);
	int getLastSatelliteIndex(List<Satellite> satellites, int satelliteInOriginIndex, int parallelSatelliteIndex);
}
