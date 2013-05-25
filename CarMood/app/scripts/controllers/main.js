'use strict';

angular.module('CarMoodApp')
  .controller('MainCtrl', function MainCtrl($scope, $http, Poller) {
//  	$http.get('http://10.13.10.151:8080/CarMood/SpeedServlet').success(function(data) {
    	//$scope.car = data;
		$scope.car = Poller.data.car.response;
		$scope.weather = Poller.data.weather.response;
		navigator.geolocation.getCurrentPosition(function(pos){
  			console.log("I'm located at ",pos.coords.latitude,' and ',pos.coords.longitude);
  			$scope.position = pos;
		});
		$http.get('assets/data/stereomood.json').success(function(r) {
		    //$http.get('http://10.13.10.151:8080/CarMood/WeatherServlet?lat=45.4973432&lon=9.2212303').then(function(r) {
		    	console.log(r);
		      $scope.songs = r.songs;
		    });
	});
  //MainCtrl.$inject=['$defer']; 
