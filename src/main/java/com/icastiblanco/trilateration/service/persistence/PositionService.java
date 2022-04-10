package com.icastiblanco.trilateration.service.persistence;

import java.util.List;

import org.springframework.stereotype.Service;

import com.icastiblanco.trilateration.model.Position;
import com.icastiblanco.trilateration.model.Satellite;
import com.icastiblanco.trilateration.service.IPositionService;

@Service
public class PositionService implements IPositionService {

	@Override
	public Position getShipCoordinates(List<Satellite> satellites) {
		Position position = new Position();
		int satelliteInOrigin = getSatelliteInOriginIndex(satellites);
		int satelliteInParallelWithOriginIndex = getSatelliteInParallelWithOriginIndex(satellites, satelliteInOrigin);
		Satellite satellite_1 = satellites.get(satelliteInOrigin);
		Satellite satellite_2 = satellites.get(satelliteInParallelWithOriginIndex);
		Satellite satellite_3 = satellites.get(getLastSatelliteIndex(satellites, satelliteInOrigin, satelliteInParallelWithOriginIndex));
		
		System.out.println("Satellite 1 is=>" + satellite_1.toString());
		System.out.println("Satellite 2 is=>" + satellite_2.toString());
		System.out.println("Satellite 3 is=>" + satellite_3.toString());
		double dist1_2 = satellite_1.getDistance() * satellite_1.getDistance();
        double dist2_2 = satellite_2.getDistance() * satellite_2.getDistance();
        double dist3_2 = satellite_3.getDistance() * satellite_3.getDistance();
        
        double d = satellite_2.getX();
        double d_2 = d*d;
        double i = satellite_3.getX();
        double j = satellite_3.getY();
        double i_2 = i * i;
        double j_2 = j * j;
        
        System.out.println("dist1_2=> "+ dist1_2 + ", " + "dist2_2=> "+ dist2_2 + ", " +"dist3_2=> "+ dist3_2 + ", ");
        System.out.println("d =>" + d + ", " + "i =>" + i + ", " + "j =>" + j + ", ");
        position.setX(((dist1_2)-(dist2_2)+d_2)/(2*d));
        double yResta = (i/j)*position.getX();
        double yDivision = 2*j;
        position.setY(((dist1_2 - dist3_2 + i_2 + j_2)/yDivision) - yResta);
        
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
	public int getSatelliteInParallelWithOriginIndex(List<Satellite> satellites, int satelliteInOriginIndex) {
		int i = 0;
		for(Satellite satellite: satellites) {
			if(satellite.getY()==satellites.get(satelliteInOriginIndex).getY() && i != satelliteInOriginIndex) {
				return i;
			}
			i++;
		}
		return -1;
	}

	@Override
	public int getLastSatelliteIndex(List<Satellite> satellites, int satelliteInOriginIndex,
			int parallelSatelliteIndex) {
		int i = 0;
		for(Satellite satellite: satellites) {
			if(i != satelliteInOriginIndex && i != parallelSatelliteIndex) {
				return i;
			}
			i++;
		}
		return -1;
	}

}
