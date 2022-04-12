package com.icastiblanco.trilateration.service.persistence;

import java.util.List;

import org.springframework.stereotype.Service;

import com.icastiblanco.trilateration.common.ResponseMessages;
import com.icastiblanco.trilateration.model.Position;
import com.icastiblanco.trilateration.model.Satellite;
import com.icastiblanco.trilateration.service.IPositionService;

@Service
public class PositionService implements IPositionService {

	@Override
	public Position getShipCoordinates(List<Satellite> satellites) throws Exception {
		Position position = new Position();
		int satelliteInOrigin = getSatelliteInOriginIndex(satellites);
		int satelliteInParallelWithOriginIndex = getSatelliteInParallelWithOriginIndex(satellites, satelliteInOrigin);
		Satellite satellite_1 = satellites.get(satelliteInOrigin);
		Satellite satellite_2 = satellites.get(satelliteInParallelWithOriginIndex);
		Satellite satellite_3 = satellites.get(getLastSatelliteIndex(satellites, satelliteInOrigin, satelliteInParallelWithOriginIndex));
		
		double dist1_2 = satellite_1.getDistance() * satellite_1.getDistance();
        double dist2_2 = satellite_2.getDistance() * satellite_2.getDistance();
        double dist3_2 = satellite_3.getDistance() * satellite_3.getDistance();
        
        double d = satellite_2.getX();
        double d_2 = d*d;
        double i = satellite_3.getX();
        double j = satellite_3.getY();
        double i_2 = i * i;
        double j_2 = j * j;
        
        //Check if there is circumference interception
        if(interceptionExists(dist1_2, dist2_2, d_2)) {
	        position.setX(((dist1_2)-(dist2_2)+d_2)/(2*d));
	        double yResta = (i/j)*position.getX();
	        double yDivision = 2*j;
	        position.setY(((dist1_2 - dist3_2 + i_2 + j_2)/yDivision) - yResta);
	        
	        if(!isValidSolution(position, dist1_2)) {
	        	throw new Exception(ResponseMessages.NOT_VALID_SOLUTION);
	        }
        }else {
        	throw new Exception(ResponseMessages.NOT_INTERCEPTION);
        }
        
        if(satellite_1.getX()!=satellite_1.getOriginalX()) {
        	satellite_1.setX(satellite_1.getOriginalX());
        	satellite_1.setY(satellite_1.getOriginalY());
        	
        	satellite_2.setX(satellite_2.getOriginalX());
        	satellite_2.setY(satellite_2.getOriginalY());
        	
        	satellite_3.setX(satellite_3.getOriginalX());
        	satellite_3.setY(satellite_3.getOriginalY());
        	
        	position.setX(position.getX()+satellite_1.getOriginalX());
        	position.setY(position.getY()+satellite_1.getOriginalY());
        }
        
		return position;
	}
	
	
	@Override
	public int getSatelliteInOriginIndex(List<Satellite> satellites) {
		int i = 0;
		for(Satellite satellite: satellites) {
			if(satellite.getX() == 0 && satellite.getY()==0) {
				return i;
			}
			i++;
		}
		return -1;
	}

	@Override
	public int getSatelliteInParallelWithOriginIndex(List<Satellite> satellites, int satelliteInOriginIndex) throws Exception {
		int i = 0;
		for(Satellite satellite: satellites) {
			if(satellite.getY()==satellites.get(satelliteInOriginIndex).getY() && i != satelliteInOriginIndex) {
				return i;
			}
			i++;
		}
		throw new Exception(ResponseMessages.NOT_PARALLEL_WITH_ORIGIN);
	}

	@Override
	public int getLastSatelliteIndex(List<Satellite> satellites, int satelliteInOriginIndex,
			int parallelSatelliteIndex) throws Exception {
		int i = 0;
		for(Satellite satellite: satellites) {
			if(i != satelliteInOriginIndex && i != parallelSatelliteIndex) {
				return i;
			}
			i++;
		}
		throw new Exception(ResponseMessages.WRONG_SATELLITES_QTY);
	}

	@Override
	public boolean interceptionExists(double r1_sqr, double r2_sqr, double d_sqr) {
		double squareNumerator = (r1_sqr-r2_sqr+d_sqr) * (r1_sqr-r2_sqr+d_sqr);
		double d_sqrByFour = 4*d_sqr;
		double squareRootY = r1_sqr - (squareNumerator/d_sqrByFour);
		
		return squareRootY >=0;
	}

	@Override
	public boolean isValidSolution(Position position, double r1_sqr) {
		double x_sqr = position.getX() * position.getX();
		double y_sqr = position.getY() * position.getY();
		
		return (r1_sqr-x_sqr-y_sqr) >=0;
	}

}
