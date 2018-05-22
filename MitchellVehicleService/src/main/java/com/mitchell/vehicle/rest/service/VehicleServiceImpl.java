package com.mitchell.vehicle.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.mitchell.vehicle.rest.dao.VehicleDao;
import com.mitchell.vehicle.rest.exception.ResourceNotFoundException;
import com.mitchell.vehicle.rest.exception.ValidationException;
import com.mitchell.vehicle.rest.model.Vehicle;
import com.mitchell.vehicle.rest.model.Vehicle.VehicleBuilder;
import com.mitchell.vehicle.util.ValidationEngine;

@Service(value = "vehicleService")
public class VehicleServiceImpl implements VehicleService {

	private static final Logger LOGGER = LoggerFactory.getLogger(VehicleService.class);
	
	@Autowired
	private VehicleDao vehicleDao;
	
	public Vehicle getVehicle(Integer id) {
		Vehicle vehicle = null;
		if(!(id instanceof Integer)){
			throw new MethodArgumentTypeMismatchException(id, Integer.class, "id", null, null);
		}			
		vehicle = vehicleDao.getVehicleByKey(id);
		if(null != vehicle)
			return vehicle;
		throw new ResourceNotFoundException(id);
	}

	public List<Vehicle> getVehicles(String make, String model) {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		if((null != make && !make.equals("")) || (null != model && !model.equals(""))){
			vehicles = vehicleDao.getVehiclesByMakeAndModel(make, model);
		}else{
			vehicles = vehicleDao.getVehicles();	
		}
		if(vehicles.isEmpty())
			throw new ResourceNotFoundException();
		return vehicles;
	}

	public Vehicle createVehicle(Vehicle vehicle) {		 
        if (isVehicleExist(vehicle)) {
            	throw new IllegalArgumentException("A Vehicle with id " + vehicle.getId() + " already exist");
        }
        List<String> validationErrors = ValidationEngine.validate(vehicle);
        if(null != validationErrors && validationErrors.size() > 0){
        	throw new ValidationException("Vehicle cannot be created", null, vehicle, validationErrors);
        }
		return vehicleDao.persistVehicle(vehicle.getId(), vehicle);
	}

	public Vehicle updateVehicleDetails(Vehicle vehicle) {
        if (!isVehicleExist(vehicle))
            	throw new IllegalArgumentException("A Vehicle with id " + vehicle.getId() + " does not exist to update");
        Vehicle updateVehicle = null;
        List<String> validationErrors = ValidationEngine.validate(vehicle);
        if(null != validationErrors && validationErrors.size() > 0){
        	vehicle = null;
        	throw new ValidationException("Vehicle cannot be updated", null, vehicle, validationErrors);
        }else
        	updateVehicle = vehicleDao.updateVehicle(vehicle.getId(), vehicle);
        return updateVehicle;
	}

	public Vehicle deleteVehicle(Integer id) {
		Vehicle deleteVehicle = new Vehicle(VehicleBuilder.getVehicleInstance().withId(id));
        if (!isVehicleExist(deleteVehicle)) 
            	throw new IllegalArgumentException("A Vehicle with id " + deleteVehicle.getId() + " does not exist to delete");
		return vehicleDao.deleteVehicle(id);
	}

	@Override
	public boolean isVehicleExist(Vehicle vehicle) {
		if(null != vehicleDao.getVehicleByKey(vehicle.getId()))
			return true;
		return false;
	}


}
