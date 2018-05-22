package com.mitchell.vehicle.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mitchell.vehicle.rest.model.Vehicle;

public class ValidationEngine {

	private static final Logger LOGGER = LoggerFactory.getLogger(ValidationEngine.class);
	private static List<String> validationErrors;
	
	public static List<String> validate(Vehicle validateVehicle){
		validationErrors = new ArrayList<String>();
		LOGGER.info("In validationengine vehicle: "+validateVehicle);
		if(null != validateVehicle){
			if(!(validateVehicle.getId() instanceof Integer))
				validationErrors.add("Id should be a numeric value. Rejected input "+validateVehicle.getId());
			if(validateVehicle.getYear() < 1950 || validateVehicle.getYear() > 2050)
				validationErrors.add("Acceptable year of make is between 1950 and 2050. Rejected input: "+validateVehicle.getYear());
			if(null == validateVehicle.getMake() || validateVehicle.getMake().equals(""))
				validationErrors.add("Make of the vehicle cannot be left empty. Rejected input: "+validateVehicle.getMake());
			if(null == validateVehicle.getModel() || validateVehicle.getModel().equals(""))
				validationErrors.add("Model of the vehicle cannot be left empty. Rejected input: "+validateVehicle.getModel());	
		}else
			validationErrors.add("Vehicle object is null");
		return validationErrors;
	}
}
