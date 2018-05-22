/**
 * Service class for Vehicle CRUD operations
 */
'use strict';

angular.module('myApp').factory('VehicleService', ['$http', '$log', function($http, $log){
	
	var baseURL = 'http://localhost:8080/MitchellVehicleService/vehicles/';
	
	var factory = {
			getVehicle: getVehicle,
			addVehicle: addVehicle,
			deleteVehicle: deleteVehicle,
			updateVehicle: updateVehicle,
			getAllVehicles: getAllVehicles
	};
	
	return factory;
	
	function getVehicle(id){
		return $http({
			method: 'GET',
			url: baseURL+id
		});
	}
	
	function addVehicle(id, make, model, year){
		return $http({
			method: 'POST',
			url: baseURL,
			data: {id:id, make:make, model:model, year:year}
		});
	}
	
	function deleteVehicle(id){
		return $http({
			method: 'DELETE',
			url: baseURL+id
		});
	}
	
	function updateVehicle(id, make, model, year){
        $log.info('In service.updateVehicle');
		return $http({
			method: 'PUT',
			url: baseURL+id,
			data: {id:id, make:make, model:model, year:year}
		});
	}
	
	function getAllVehicles(){
        $log.info("In VehicleService.getAllVehicles");
		return $http({
			method: 'GET',
			url: baseURL
		});
	}
}]);
