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
			url: 'http://localhost:8080/MitchellVehicleService/vehicles/'+id
		});
	}
	
	function addVehicle(id, make, model, year){
		return $http({
			method: 'POST',
			url: 'http://localhost:8080/MitchellVehicleService/vehicles',
			data: {id:id, make:make, model:model, year:year}
		});
	}
	
	function deleteVehicle(id){
		return $http({
			method: 'DELETE',
			url: 'http://localhost:8080/MitchellVehicleService/vehicles/'+id
		});
	}
	
	function updateVehicle(id, make, model, year){
		return $http({
			method: 'PATCH',
			url: 'http://localhost:8080/MitchellVehicleService/vehicles/'+id,
			data: {id:id, make:make, model:model, year:year}
		});
	}
	
	function getAllVehicles(){
		return $http({
			method: 'GET',
			url: 'http://localhost:8080/MitchellVehicleService/vehicles/'
		});
	}
}]);
