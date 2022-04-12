package com.icastiblanco.trilateration.common;

public class ResponseMessages {
	public static final String SATELLITE_SAVED_OK = "Satellite saved successfully";
	public static final String SATELLITES_SAVED_OK = "Satellites saved successfully";
	public static final String SATELLITE_DUPLICATED_NAME = "Satellite name must be unique";
	public static final String SATELLITES_QTY_COMPLETED = "Total of satellites already registered";
	public static final String SATELLITE_NOT_FOUND = "Satellite not found";
	public static final String NOT_VALID_SOLUTION = "There's no valid solution";
	public static final String NOT_INTERCEPTION = "There's no interception among the satellites radio";
	public static final String NOT_PARALLEL_WITH_ORIGIN = "There's no satellite in parallel with satellite in origin [0,0]";
	public static final String WRONG_SATELLITES_QTY = "Wrong satellites quantity";
}
