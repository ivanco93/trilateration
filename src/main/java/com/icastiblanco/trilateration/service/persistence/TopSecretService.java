package com.icastiblanco.trilateration.service.persistence;

import org.springframework.stereotype.Service;

import com.icastiblanco.trilateration.service.ITopSecretService;

@Service
public class TopSecretService implements ITopSecretService {

	@Override
	public String getDecodedMessage(String[][] messages) {
		String[] response = new String[messages[0].length];
		for(int i = 0; i<messages.length-1; i++) {
			System.out.println("messages " + i + " is =>" + String.join(",", messages[i]));
			System.out.println("messages " + i + "+1 is =>" + String.join(",", messages[i+1]));
			for(int k =0; k<messages[i].length;k++) {
				response[k] = response[k]==null?"": response[k];
				response[k] = !response[k].equals("")?response[k]:(!messages[i][k].equals("")?messages[i][k]:messages[i+1][k]);
			}
		}
		System.out.println("response is =>" + String.join("+", response));
		return String.join(" ", response);
	}

}
