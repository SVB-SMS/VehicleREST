package com.mitchell.vehicle.rest.resource;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.mitchell.vehicle.rest.model.Vehicle;
import com.mitchell.vehicle.rest.model.Vehicle.VehicleBuilder;
import com.mitchell.vehicle.rest.service.VehicleService;

@RestController
@RequestMapping("vehicles")
public class VehicleResource {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VehicleResource.class);
	
    @Autowired
    VehicleService vehicleService;  //Service which will do all data retrieval/manipulation work
 
     
    //-------------------Retrieve All Vehicles--------------------------------------------------------
     
    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Vehicle>> listAllVehicles(@RequestParam(required = false, value = "make") String make, 
    													 @RequestParam(required = false, value= "model") String model){
    	List<Vehicle> vehicles = vehicleService.getVehicles(make, model);
        if(null == vehicles && vehicles.isEmpty()){
            return new ResponseEntity<List<Vehicle>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Vehicle>>(vehicles, HttpStatus.OK);
    }
 
 
    //-------------------Retrieve Single Vehicle--------------------------------------------------------
     
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Vehicle> getVehicle(@PathVariable("id") Integer id){
    	Vehicle vehicle = vehicleService.getVehicle(id);
        if (vehicle == null)
            return new ResponseEntity<Vehicle>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Vehicle>(vehicle, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a Vehicle--------------------------------------------------------
     
    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<Vehicle> createVehicle(@Valid @RequestBody Vehicle vehicle, UriComponentsBuilder ucBuilder){
    	LOGGER.info("Creating Vehicle " + vehicle.getModel());
 
        Vehicle newVehicle = vehicleService.createVehicle(vehicle);
        if(null == newVehicle)
        	return new ResponseEntity<Vehicle>(HttpStatus.NOT_FOUND);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/vehicle/{id}").buildAndExpand(vehicle.getId()).toUri());
        return new ResponseEntity<Vehicle>(headers, HttpStatus.CREATED);
    }
 
     
    //------------------- Update a Vehicle --------------------------------------------------------
     
    @RequestMapping(value = "{id}", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable("id") Integer id, @Valid @RequestBody Vehicle vehicle) {
    	LOGGER.info("Updating Vehicle " + id +" vehicle: "+vehicle);
         
        Vehicle currentVehicle = vehicleService.getVehicle(id);
        Vehicle updateVehicle = null; 
        if (currentVehicle==null) 
            return new ResponseEntity<Vehicle>(HttpStatus.NOT_FOUND);
        updateVehicle = VehicleBuilder.getVehicleInstance().
        		withId(vehicle.getId()).
        		withMake(vehicle.getMake()).
        		withModel(vehicle.getModel()).
        		withYear(vehicle.getYear()).build();
         
        updateVehicle = vehicleService.updateVehicleDetails(updateVehicle);
        if(null == updateVehicle)
        	return new ResponseEntity<Vehicle>(updateVehicle, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Vehicle>(updateVehicle, HttpStatus.OK);
    }
 
    //------------------- Delete a Vehicle --------------------------------------------------------
     
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Vehicle> deleteVehicle(@PathVariable("id") int id){
    	LOGGER.info("Fetching & Deleting Vehicle with id " + id);
 
        Vehicle vehicle = vehicleService.getVehicle(id);
        if (vehicle == null)
            return new ResponseEntity<Vehicle>(HttpStatus.NOT_FOUND); 
        vehicleService.deleteVehicle(id);
        return new ResponseEntity<Vehicle>(HttpStatus.NO_CONTENT);
    }
 
     
    //------------------- Delete All Vehicles --------------------------------------------------------
     
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Vehicle> deleteAllVehicles() {
    	LOGGER.info("Deleting All Vehicles");
 
//        vehicleService.deleteAllVehicles();
        return new ResponseEntity<Vehicle>(HttpStatus.NO_CONTENT);
    }
 
}
