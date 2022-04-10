package com.icastiblanco.trilateration.service.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icastiblanco.trilateration.model.Satellite;
import com.icastiblanco.trilateration.repository.SatelliteRepository;
import com.icastiblanco.trilateration.service.ISatelliteService;
@Service
public class SatelliteService implements ISatelliteService {
	@Autowired
	private SatelliteRepository satelliteRepo;
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
}
