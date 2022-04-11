package com.icastiblanco.trilateration.service;

public interface ITopSecretService {
	String getDecodedMessage(String[][] messages);
	String[][] fixGap(String[][] messages);
	int getMaximumMessagesLength(String[][] messages);
	String[] addArrayPosition(String[] messages, int newPositionIndex);
}
