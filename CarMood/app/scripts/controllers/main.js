'use strict';

angular.module('CarMoodApp')
  .controller('MainCtrl', function ($scope, Poller) {
  	$http.get('http://10.13.10.151:8080/CarMood/SpeedServlet').success(function(data) {
console.log(data);
  	});
    //$http.get('assets/data/car.json').success(function(data) {
		//$scope.car = data;
		
console.log(Poller.data);
		$scope.car = Poller.data.car.response;
		$scope.weather = Poller.data.weather.response;
		//getLocation($scope);
	//});
  });
  //MainCtrl.$inject=['$defer']; 
