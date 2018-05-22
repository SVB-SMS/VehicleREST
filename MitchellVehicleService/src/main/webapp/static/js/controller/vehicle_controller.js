/**
 * A Controller for Vehicle service
 */
'use strict';

angular.module('myApp').controller('VehicleCtrl', ['$scope', '$log', 'VehicleService', function($scope, $log, VehicleService){
	
	$scope.vehicle = {id:null,make:'',model:'',year:''};
	$scope.vehicles = [];
	
	$scope.updateVehicle = function(vehicleId, model, make, year){
        $log.info('In ctrl.updateVehicle');
		VehicleService.updateVehicle(vehicleId, model, make, year)
			.then(function success(response){
                $log.info('updateVehicle SUCCESS response: '+JSON.stringify(response));
				$scope.message = 'Details updated';
				$scope.errorMessage = '';
			}, function error(response){
                $log.info('updateVehicle ERROR response: '+JSON.stringify(response));
				$scope.message = '';
				$scope.errorMessage = 'Error while updating';
			});
        $scope.getAllVehicles();
	}
	
	$scope.getVehicle = function(vehicleId){
		VehicleService.getVehicle(vehicleId)
			.then(function success(response){
				$scope.vehicle = response.data;
				$scope.vehicle.id = id;
				$scope.message = '';
				$scope.errorMessage = '';
			}, function error(response){
                $log.info('getVehicle ERROR response: '+JSON.stringify(response));
				$scope.message = '';
				if(response.status == 404){
					$scope.errorMessage = 'Vehicle not found';
				}else{
					$scope.errorMessage = 'Error in getting vehicle';
				}
			});
	}
	
	$scope.addVehicle = function(vehicleId, make, model, year){
        $log.info('In ctrl.addVehicle');
		if(vehicleId != null && make != null && model != null){
			VehicleService.addVehicle(vehicleId, make, model, year)
				.then(function success(response){
                    $log.info('addVehicle SUCCESS response: '+JSON.stringify(response));
					$scope.message = 'Vehicle added';
					$scope.errorMessage = '';
                    $scope.add.vehicle.id = '';
                    $scope.add.vehicle.make = '';
                    $scope.add.vehicle.model = '';
                    $scope.add.vehicle.year = '';
				}, function error(response){
                    $log.info('addVehicle ERROR response: '+JSON.stringify(response));
					$scope.message = '';
					$scope.errorMessage = 'Error while adding vehicle';
				});
		}else{
			$scope.errorMessage = 'Please enter make, model and year details';
			$scope.message = '';
		}
        $scope.getAllVehicles();
	}
	
	$scope.deleteVehicle = function(vehicleId){
        $log.info("In ctrl.deleteVehicle $scope.vehicle.id: "+vehicleId);
		VehicleService.deleteVehicle(vehicleId)
		.then(function success(response){
				$scope.message = 'Vehicle deleted';
				$scope.errorMessage = '';
			}, function error(response){
				$scope.errorMessage = 'Error deleting vehicle';
				$scope.message = '';
			});
        $scope.getAllVehicles();
	}
	
	$scope.getAllVehicles = function(){
        $log.info("In VehicleCtrl.getAllVehicles");
        $scope.vehicles = [];
		VehicleService.getAllVehicles().then(function success(response){            
            $log.info("getAllVehicles - SUCCESS response: "+JSON.stringify(response));
			$scope.vehicles = response.data;
			$scope.message = '';
			$scope.errorMessage = '';
		}, function error(response){
			$scope.message = '';
			$scope.errorMessage = 'Error getting Vehicles!';
		});
	}
	
	$scope.getAllVehicles();
}]);