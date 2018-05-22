package com.mitchell.vehicle.rest.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mitchell.vehicle.rest.dao.VehicleDao;
import com.mitchell.vehicle.rest.model.Vehicle;

@Repository(value = "vehicleDao")
public class VehicleDaoImpl implements VehicleDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VehicleDao.class);
	private static Map<Integer, Vehicle> vehiclesRepository;
		
	public VehicleDaoImpl(){
		vehiclesRepository = new HashMap<Integer, Vehicle>();
		vehiclesRepository.put(123, new Vehicle(123, 1960, "WolksWagon", "Beats"));
		vehiclesRepository.put(456, new Vehicle(456, 1961, "Toyota", "Corolla"));
		vehiclesRepository.put(789, new Vehicle(789, 1962, "WolksWagon", "Beats"));
	}
	
	public List<Vehicle> getVehicles() {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		if(!vehiclesRepository.isEmpty()){
			for(Integer ids : vehiclesRepository.keySet()){
				vehicles.add(vehiclesRepository.get(ids));
			}
		}
		return vehicles;
	}

	public Vehicle getVehicleByKey(Integer key){
		LOGGER.info("In DAO getVehicleByKey");
		if(null != vehiclesRepository.get(key))
			return vehiclesRepository.get(key);
		return null;
	}

	public Vehicle persistVehicle(Integer key, Vehicle vehicle) {
		if(vehiclesRepository.containsKey(key)){
			return null;
		}
		return vehiclesRepository.put(key, vehicle);
	}

	public Vehicle updateVehicle(Integer key, Vehicle vehicle){
		Vehicle updatesVehicle = null;
		if(vehiclesRepository.containsKey(key)){
			updatesVehicle = vehiclesRepository.replace(key, vehicle);
		}
		return vehicle;
	}

	public Vehicle deleteVehicle(Integer key){
		if(vehiclesRepository.containsKey(key))
			return vehiclesRepository.remove(key);
		return null;
	}

	public List<Vehicle> getVehiclesByMakeAndModel(String make, String model) {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		if(!vehiclesRepository.isEmpty()){
			for(Integer ids : vehiclesRepository.keySet()){
				if((null != make && !make.equals("")) && (null != model && !model.equals(""))){
					if(vehiclesRepository.get(ids).getMake().equalsIgnoreCase(make) && vehiclesRepository.get(ids).getModel().equalsIgnoreCase(model))
						vehicles.add(vehiclesRepository.get(ids));	
				}else if(null != make && !make.equals("")){
					if(vehiclesRepository.get(ids).getMake().equalsIgnoreCase(make))
						vehicles.add(vehiclesRepository.get(ids));
				}else if(null != model && !model.equals("")){
					if(vehiclesRepository.get(ids).getModel().equalsIgnoreCase(model))
						vehicles.add(vehiclesRepository.get(ids));
				}				
			}
		}
		LOGGER.info("Returning vehicles: "+vehicles);
		return vehicles;
	}

}
