package com.icastiblanco.trilateration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icastiblanco.trilateration.model.Satellite;

public interface SatelliteRepository extends JpaRepository<Satellite, Long> {
	Satellite findByName(String name);
}
