package com.mitchell.test;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.mitchell.vehicle.rest.model.Vehicle;

public class MitchellVehicleTestClient {
	
	private static final String REST_SERVICE_URI = "http://localhost:8080/MitchellVehicleService";
	private static RestTemplate restTemplate;
	
	//POST
	private static void buildVehicle(){
		System.out.println("----- TESTING CREATE VEHICLE -------");
		restTemplate = new RestTemplate();
		Vehicle v = Vehicle.VehicleBuilder.getVehicleInstance().withId(123).withMake("Hyundai").withModel("Accent").withYear(2001).build();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		System.out.println("Building vehicle: "+v.getId());
		String jsonInput = "{\"id\": 123, \"year\": 2004, \"make\": \"ABC\", \"model\": \"DEF\" }";
		HttpEntity<Void> request = new HttpEntity(jsonInput, headers);
		ResponseEntity<Vehicle> vResponse = restTemplate.postForEntity(REST_SERVICE_URI+"/vehicles", request, Vehicle.class);
		System.out.println("after post vehicle: "+vResponse);
//		URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/vehicles", v);
//		Vehicle response = restTemplate.postForObject(REST_SERVICE_URI+"/vehicles", v, Vehicle.class);
//		System.out.println("Location : "+uri.toASCIIString());
	}
	
	// GET ALL
	@SuppressWarnings("unchecked")
	private static void getAllVehicles(){
		System.out.println("----- TESTING GET ALL VEHICLES -------");
		restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>> vehicles = restTemplate.getForObject(REST_SERVICE_URI+"/vehicles", List.class);
		int counter = 0;
		if(vehicles != null){
			for(LinkedHashMap<String, Object> v : vehicles){
				System.out.println("Vehicle["+counter+"]: ");
				System.out.println("id - "+v.get("id")+", make - "+v.get("make")+", model - "+v.get("model")+", year - "+v.get("year"));
				counter++;
			}
		}else{
			System.out.println("List is empty");
		}
	}
	
	//GET
	private static void getVehicle(){
		System.out.println("----- TESTING GET VEHICLE 123 -------");
		restTemplate = new RestTemplate();
		Vehicle v = restTemplate.getForObject(REST_SERVICE_URI+"/vehicles/123", Vehicle.class);
		System.out.println("Vehicle: "+v);
	}
	
	//PUT
	private static void updateVehicle(){
		System.out.println("----- TESTING UPDATE VEHICLE 123 -------");
		restTemplate = new RestTemplate();
		Vehicle v = Vehicle.VehicleBuilder.getVehicleInstance().withId(123).withMake("Acura").withModel("Acura").withYear(2001).build();
		restTemplate.put(REST_SERVICE_URI+"/vehicles/123", v);
		System.out.println("Vehicle: "+v);
	}
	
	//DELETE
	private static void deleteVehicle(){
		System.out.println("----- TESTING UPDATE VEHICLE 123 -------");
		restTemplate = new RestTemplate();
		restTemplate.delete(REST_SERVICE_URI+"/vehicles/123", Vehicle.class);
	}
	
	public static void main(String[] args){
		getAllVehicles();
		buildVehicle();
		getVehicle();
		updateVehicle();
		getAllVehicles();
		deleteVehicle();
		getAllVehicles();
	}
}
