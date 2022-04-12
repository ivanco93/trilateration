package com.icastiblanco.trilateration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icastiblanco.trilateration.common.ResponseMessages;
import com.icastiblanco.trilateration.model.RequestResponse;
import com.icastiblanco.trilateration.model.Satellite;
import com.icastiblanco.trilateration.model.TopSecret;
import com.icastiblanco.trilateration.model.TopSecretResponse;
import com.icastiblanco.trilateration.model.TopSecretShipSplit;
import com.icastiblanco.trilateration.service.IPositionService;
import com.icastiblanco.trilateration.service.ISatelliteService;
import com.icastiblanco.trilateration.service.ITopSecretService;

@RestController
@RequestMapping("/topsecret_split")
public class TopSecretSplitController {
	@Autowired
	private ISatelliteService satelliteService;
	
	@Autowired
	private IPositionService positionService;
	
	@Autowired
	private ITopSecretService topSecretService;
	
	@PostMapping("/{name}")
	public ResponseEntity<?> setSatelliteSplitedInfo(@PathVariable("name") String name, @RequestBody TopSecretShipSplit t){
		Satellite st = satelliteService.searchByName(name);
		if(st!=null) {
			st.setDistance(t.getDistance());
			st.setMessage(t.getMessage());
			
			satelliteService.update(st);
			return ResponseEntity.ok(st);
		}else {
			return ResponseEntity.ok(new RequestResponse(ResponseMessages.SATELLITE_NOT_FOUND));
		}
	}
	
	@GetMapping("")
	public ResponseEntity<?> getTopSecretResponse() {
		try {
			TopSecretResponse response = new TopSecretResponse();
			List<Satellite> satellites = satelliteService.searchAll();
			satelliteService.translateToOrigin(satellites);
			String[][] messages = new String[satellites.size()][];
			int i = 0;
			for(Satellite s: satellites) {
				messages[i] = s.getMessage();
				i++;
			}
			
			response.setPosition(positionService.getShipCoordinates(satellites));
			response.setMessage(topSecretService.getDecodedMessage(messages));
			return ResponseEntity.ok(response);
		}catch(Exception e) {
			System.out.println("Exception getting splitted top secret: " + e.getMessage());
			return ResponseEntity.ok(new RequestResponse(e.getMessage()));
		}
		
	}
}
