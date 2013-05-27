'use strict';

angular.module('CarMoodApp')
  .controller('MainCtrl', function MainCtrl($scope, $http, $timeout) {
//  	$http.get('http://10.13.10.151:8080/CarMood/SpeedServlet').success(function(data) {
    	//$scope.car = data;
		//$scope.car = Poller.data.car.response;
		//$scope.weather = Poller.data.weather.response;
		$http.defaults.useXDomain = true;
		navigator.geolocation.getCurrentPosition(function(pos){
  			console.log("I'm located at ",pos.coords.latitude,' and ',pos.coords.longitude);
  			$scope.position = pos;
		});
		(function tick(){
			$http({method: 'GET', url: 'assets/data/stereomood.json'}).success(function(r) {
		    	//$http.get('http://10.13.10.151:8080/CarMood/WeatherServlet?lat=45.4973432&lon=9.2212303').then(function(r) {
		    	$scope.songs = r.songs;
		    });
		    $http.get('assets/data/car.json').success(function(r) {
    			//$http.get('http://10.13.10.151:8080/CarMood/SpeedServlet').then(function(r) {
				$scope.car = r;
				$scope.car.speed = Math.random()*140;
			});
    		$http.get('assets/data/weather.json').success(function(r) {
    			//$http.get('http://10.13.10.151:8080/CarMood/WeatherServlet?lat=45.4973432&lon=9.2212303').then(function(r) {
      			$scope.weather = r;
    		});
    		$http.get('https://api.usergrid.com/%7Borganization%7D/%7Bapplication%7D/groups/surfers/feed')
    		.success(function(r){
    			$scope.test = r;
    		}).error(function(r){
    			$scope.test = r;
    		});
		    $timeout(tick, 5000);
		})();
		

	});
  //MainCtrl.$inject=['$defer']; 
