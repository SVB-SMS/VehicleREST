package com.mitchell.vehicle.rest.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mitchell.vehicle.rest.exception.ResourceNotFoundException;
import com.mitchell.vehicle.rest.model.Vehicle;

@Repository(value = "vehicleDao")
public interface VehicleDao {

	public List<Vehicle> getVehicles();
	
	public Vehicle getVehicleByKey(Integer key);

	public Vehicle persistVehicle(Integer key, Vehicle vehicle);

	public Vehicle updateVehicle(Integer key, Vehicle vehicle);

	public Vehicle deleteVehicle(Integer key);
	
	public List<Vehicle> getVehiclesByMakeAndModel(String make, String model);
	
}
