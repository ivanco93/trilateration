package com.icastiblanco.trilateration.service.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icastiblanco.trilateration.model.Satellite;
import com.icastiblanco.trilateration.repository.SatelliteRepository;
import com.icastiblanco.trilateration.service.IPositionService;
import com.icastiblanco.trilateration.service.ISatelliteService;
@Service
public class SatelliteService implements ISatelliteService {
	@Autowired
	private SatelliteRepository satelliteRepo;
	@Autowired
	private IPositionService positionService;
	
	@Override
	public List<Satellite> searchAll() {
		return satelliteRepo.findAll();
	}
	@Override
	public Satellite searchByName(String name) {
		return satelliteRepo.findByName(name);
	}
	@Override
	public void save(Satellite satellite) {
		satelliteRepo.save(satellite);
	}
	@Override
	public void update(Satellite satellite) {
		satelliteRepo.save(satellite);
	}
	@Override
	public void deleteAll() {
		satelliteRepo.deleteAll();
	}
	@Override
	public long count() {
		return satelliteRepo.count();
	}
	@Override
	public Satellite searchById(Long id) {
		Optional<Satellite> satellite =  satelliteRepo.findById(id);
		if(satellite.isPresent()) {
			return satellite.get();
		}
		return null;
	}
	
	@Override
	//public List<Satellite> translateToOrigin(List<Satellite> satellites) throws Exception {
	public void translateToOrigin(List<Satellite> satellites) throws Exception {
		int satelliteInOrigin = positionService.getSatelliteInOriginIndex(satellites);
		if(satelliteInOrigin==-1) {
			Satellite aux1, aux2, aux3;
			aux1 = satellites.get(0);
			int satelliteInParallelWithOriginIndex = positionService.getSatelliteInParallelWithOriginIndex(satellites, 0);
			aux2 = satellites.get(satelliteInParallelWithOriginIndex);
			aux3 = satellites.get(positionService.getLastSatelliteIndex(satellites, 0, satelliteInParallelWithOriginIndex));
			
			aux1.setX(aux1.getX()-aux1.getOriginalX());
			aux1.setY(aux1.getY()-aux1.getOriginalY());
			
			aux2.setX(aux2.getX()-aux1.getOriginalX());
			aux2.setY(aux2.getY()-aux1.getOriginalY());
			
			aux3.setX(aux3.getX()-aux1.getOriginalX());
			aux3.setY(aux3.getY()-aux1.getOriginalY());
		}
		
		//return satellites;
	}
}
