package com.icastiblanco.trilateration.service;

import java.util.List;

import com.icastiblanco.trilateration.model.Satellite;

public interface ISatelliteService{
	List<Satellite> searchAll();
	Satellite searchById(Long id);
	Satellite searchByName(String name);
	void save(Satellite satellite);
	void update(Satellite satellite);
	void deleteAll();
	long count();
	void translateToOrigin(List<Satellite> satellites) throws Exception;
}
