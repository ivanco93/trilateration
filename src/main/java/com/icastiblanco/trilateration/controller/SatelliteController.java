package com.icastiblanco.trilateration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icastiblanco.trilateration.common.ResponseMessages;
import com.icastiblanco.trilateration.common.TConstants;
import com.icastiblanco.trilateration.model.RequestResponse;
import com.icastiblanco.trilateration.model.Satellite;
import com.icastiblanco.trilateration.service.ISatelliteService;

@RestController
@RequestMapping("/satellites")
public class SatelliteController {
	@Autowired
	private ISatelliteService satelliteService;
	
	@GetMapping("")
	public List<Satellite> searchAll(){
		return satelliteService.searchAll();
	}
	
	@PostMapping("")
	public Object save(@RequestBody Satellite satellite){
		satellite.setOriginalX(satellite.getX());
		satellite.setOriginalY(satellite.getY());
		return store(satellite);
	}
	
	@PostMapping("/saveMultiple")
	public Object save(@RequestBody List<Satellite> satellites){
		RequestResponse response = new RequestResponse();
		response.setMessage(ResponseMessages.SATELLITES_SAVED_OK);
		for(Satellite satellite: satellites) {
			satellite.setOriginalX(satellite.getX());
			satellite.setOriginalY(satellite.getY());
			Object save = store(satellite);
			if(save instanceof RequestResponse) {
				satelliteService.deleteAll();
				return save;
			}
		}
		return response;
	}
	
	private Object store(Satellite satellite) {
		RequestResponse response = new RequestResponse();
		if(satelliteService.count()<TConstants.AVAILABLE_SATELLITES_QTY) {
			Satellite satelliteAux = satelliteService.searchByName(satellite.getName());
			if(satelliteAux==null) {
				satelliteService.save(satellite);
				return satelliteService.searchById(satellite.getId());
			}else {
				response.setMessage(ResponseMessages.SATELLITE_DUPLICATED_NAME);
			}
		}else {
			response.setMessage(ResponseMessages.SATELLITES_QTY_COMPLETED);
		}
		return response;
	}
}
