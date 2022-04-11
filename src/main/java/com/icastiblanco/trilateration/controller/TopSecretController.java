package com.icastiblanco.trilateration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icastiblanco.trilateration.model.RequestResponse;
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
	public ResponseEntity<?> getTopSecretResponse(@RequestBody TopSecret topSecret) {
		try {
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
					throw new Exception("Satellite ["+s.getName()+"] not found");
				}
				i++;
			}
			
			response.setPosition(positionService.getShipCoordinates(satelliteService.searchAll()));
			response.setMessage(topSecretService.getDecodedMessage(messages));
			return ResponseEntity.ok(response);
		}catch(Exception e) {
			System.out.println("Exception getting top secret: " + e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			//return ResponseEntity.ok(new RequestResponse(e.getMessage()));
		}
		
	}
}
