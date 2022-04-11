package com.icastiblanco.trilateration.service.persistence;

import org.springframework.stereotype.Service;

import com.icastiblanco.trilateration.service.ITopSecretService;

@Service
public class TopSecretService implements ITopSecretService {

	@Override
	public String getDecodedMessage(String[][] messages) {
		//String[][] messagesWithoutGap = fixGap(messages);
		messages = fixGap(messages);
		String[] response = new String[messages[0].length];
		for(int i = 0; i<messages.length-1; i++) {
			for(int k =0; k<messages[i].length;k++) {
				response[k] = response[k]==null?"": response[k];
				response[k] = !response[k].equals("")?response[k]:(!messages[i][k].equals("")?messages[i][k]:messages[i+1][k]);
			}
		}
		System.out.println("response is =>" + String.join("+", response));
		return String.join(" ", response);
	}
	
	@Override
	public String[][] fixGap(String[][] messages) {
		String[][] response = new String[messages[0].length][];
		boolean hasAllTheSameQty = true;
		int messageLength = messages[0].length;
		for(int k = 1; k<messages.length; k++) {
			hasAllTheSameQty = messageLength == messages[k].length;
			if(!hasAllTheSameQty) {
				break;
			}
		}
		
		if(!hasAllTheSameQty) {
			int i, j, l, m;
	        for (i = 0; i < messages.length; i++) {
	            for (j = i+1; j < messages.length; j++) {
	                if(messages[i].length != messages[j].length) {
	                	for (l = 0; l < messages[i].length; l++) {
	                		if(!messages[i][l].equals("")) {
		                		for (m = 0; m < messages[j].length; m++) {
			                		if(messages[i][l].equals(messages[j][m]) && l != m){
			                			if(messages[i].length<messages[j].length) {
			                				System.out.println("if");
			                				messages[i] = addArrayPosition(messages[i], l);
			                				l++;
			                			}else {
			                				System.out.println("else");
			                				messages[j] = addArrayPosition(messages[j], m);
			                				m++;
			                			}
			                		}
			                	}
	                		}
	                	}
	                }
	            }
	        }
		}
		return response;
	}
	
	@Override
	public int getMaximumMessagesLength(String[][] messages) {
		int messageLength = messages[0].length;
		for(int i = 1; i<messages.length; i++) {
			messageLength = messageLength>= messages[i].length? messageLength: messages[i].length;
		}
		
		return messageLength;
	}
	
	@Override
	public String[] addArrayPosition(String[] messages, int newPositionIndex) {
		int newLength = messages.length+1;
		String newArray[] = new String[newLength];
		int k = 0;
		for(int i = 0; i<newLength; i++) {
			if(i == newPositionIndex) {
				newArray[i] = "";
			}else {
				newArray[i] = messages[k];
				k++;
			}
		}
		System.out.println("new array length is " + newArray.length);
		return newArray;
	}
}
