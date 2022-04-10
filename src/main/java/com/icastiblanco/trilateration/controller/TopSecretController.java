package com.icastiblanco.trilateration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icastiblanco.trilateration.model.Satellite;
import com.icastiblanco.trilateration.model.SatelliteInterception;
import com.icastiblanco.trilateration.model.TopSecret;
import com.icastiblanco.trilateration.model.TopSecretResponse;
import com.icastiblanco.trilateration.service.IPositionService;
import com.icastiblanco.trilateration.service.ISatelliteService;
import com.icastiblanco.trilateration.service.ITopSecretService;

@RestController
@RequestMapping("/topsecret")
public class TopSecretController {
	@Autowired
	private ISatelliteService satelliteService;
	
	@Autowired
	private IPositionService positionService;
	
	@Autowired
	private ITopSecretService topSecretService;
	
	@PostMapping("")
	public TopSecretResponse getTopSecretResponse(@RequestBody TopSecret topSecret) {
		System.out.println(topSecret.toString());
		TopSecretResponse response = new TopSecretResponse();
		String[][] messages = new String[topSecret.getSatellites().size()][];
		int i = 0;
		for(SatelliteInterception s: topSecret.getSatellites()) {
			messages[i] = s.getMessage();
			Satellite st = satelliteService.searchByName(s.getName());
			if(st!=null) {
				st.setDistance(s.getDistance());
				satelliteService.update(st);
			}else {
				
			}
			i++;
		}
		
		response.setPosition(positionService.getShipCoordinates(satelliteService.searchAll()));
		response.setMessage(topSecretService.getDecodedMessage(messages));
		return response;
	}
}
