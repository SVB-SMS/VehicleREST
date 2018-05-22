/**
 * Vehicle CRUD operation - Angular
 */

'use strict';
var app = angular.module('myApp', []);

/*app.controller('VehicleCtrl', ['$scope', 'VehicleService', function($scope, VehicleService){
	
	$scope.updateVehicle = function(){
		VehicleService.updateVehicle($scope.vehicle.id, $scope.vehicle.model, $scope.vehicle.make, $scope.vehicle.year)
			.then(function success(response){
				$scope.message = 'Details updated';
				$scope.errorMessage = '';
			}, function error(response){
				$scope.message = '';
				$scope.errorMessage = 'Error while updating';
			});
	}
	
	$scope.getVehicle = function(){
		var id = $scope.vehicle.id;
		VehicleService.getVehicle(id)
			.then(function success(response){
				$scope.vehicle = response.data;
				$scope.vehicle.id = id;
				$scope.message = '';
				$scope.errorMessage = '';
			}, function error(response){
				$scope.message = '';
				if(response.status == 404){
					$scope.errorMessage = 'Vehicle not found';
				}else{
					$scope.errorMessage = 'Error in getting vehicle';
				}
			});
	}
	
	$scope.addVehicle = function(){
		if($scope.make != null && $scope.model != null){
			VehicleService.addVehicle($scope.vehicle.id, $scope.vehicle.make, $scope.vehicle.model, $scope.vehicle.year)
				.then(function success(response){
					$scope.message('Vehicle added');
					$scope.errorMessage = '';
				}, function error(){
					$scope.message = '';
					$scope.errorMessage = 'Error while adding vehicle';
				});
		}else{
			$scope.errorMessage('Please enter make, model and year details');
			$scope.message = '';
		}
	}
	
	$scope.deleteVehicle = function(){
		VehicleService.deleteVehicle($scope.vehicle.id)
		.then(function success(response){
				$scope.message('Vehicle deleted');
				$scope.errorMessage('');
				$scope.vehicle = null;
			}, function error(response){
				$scope.errorMessage = 'Error deleting vehicle';
				$scope.message = '';
			});
	}
	
	$scope.getAllVehicles = function(){
		VehicleService.getAllVehicles().then(function success(response){
			$scope.vehicles = response.data._embedded.vehicles;
			$scope.message = '';
			$scope.errorMessage = '';
		}, function error(response){
			$scope.message = '';
			$scope.errorMessage = 'Error getting Vehicles!';
		});
	}
}]);*/

/*app.service('VehicleService', ['$http', function($http){
	
	this.getVehicle = function getVehicle(id){
		return $http({
			method: 'GET',
			url: 'http://localhost:8080/MitchellVehicleService/vehicles/'+id
		});
	}
	
	this.addVehicle = function addVehicle(id, make, model, year){
		return $http({
			method: 'POST',
			url: 'http://localhost:8080/MitchellVehicleService/vehicles',
			data: {id:id, make:make, model:model, year:year}
		});
	}
	
	this.deleteVehicle = function deleteVehicle(id){
		return $http({
			method: 'DELETE',
			url: 'http://localhost:8080/MitchellVehicleService/vehicles/'+id
		});
	}
	
	this.updateVehicle = function updateVehicle(id, make, model, year){
		return $http({
			method: 'PATCH',
			url: 'http://localhost:8080/MitchellVehicleService/vehicles/'+id,
			data: {id:id, make:make, model:model, year:year}
		});
	}
	
	this.getAllVehicles = function getAllVehicles(){
		return $http({
			method: 'GET',
			url: 'http://localhost:8080/MitchellVehicleService/vehicles/'
		});
	}
}]);*/