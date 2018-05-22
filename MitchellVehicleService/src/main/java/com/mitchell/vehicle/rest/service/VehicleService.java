package com.mitchell.vehicle.rest.service;

import java.util.List;

import com.mitchell.vehicle.rest.model.Vehicle;

public interface VehicleService {
	
	public Vehicle getVehicle(Integer id);
	
	public List<Vehicle> getVehicles(String make, String model);
	
	public Vehicle createVehicle(Vehicle vehicle);
	
	public Vehicle updateVehicleDetails(Vehicle vehicle);
	
	public Vehicle deleteVehicle(Integer id);
	
	public boolean isVehicleExist(Vehicle vehicle);

}
