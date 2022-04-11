package com.icastiblanco.trilateration.service;

import java.util.List;

import com.icastiblanco.trilateration.model.Position;
import com.icastiblanco.trilateration.model.Satellite;


public interface IPositionService {
	Position getShipCoordinates(List<Satellite> satellites) throws Exception;
	int getSatelliteInOriginIndex(List<Satellite> satellites);
	int getSatelliteInParallelWithOriginIndex(List<Satellite> satellites, int satelliteInOriginIndex) throws Exception;
	int getLastSatelliteIndex(List<Satellite> satellites, int satelliteInOriginIndex, int parallelSatelliteIndex) throws Exception;
	boolean interceptionExists(double r1_sqr, double r2_sqr, double d_sqr);
	boolean isValidSolution(Position position, double r1_sqr);
}
