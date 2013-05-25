'use strict';

var app = angular.module('CarMoodApp', [])
  .config(['$routeProvider',function ($routeProvider) {
    $routeProvider
      .when('/', {templateUrl: 'views/home.html', controller: 'MainCtrl'})
            .when('/game', {templateUrl: 'views/game.html', controller: 'GameCtrl'})
            .otherwise({redirectTo: '/'});
  }]);

app.run(function(Poller) {});

app.factory('Poller', function($http, $timeout) {
  var data = {car:{}, weather:{}};
  data.car = { response: {}, calls: 0 };
  data.weather = { response: {}, calls: 0 };
  var poller = function() {
    //$http.get('assets/data/car.json').then(function(r) {
    $http.get('http://10.13.10.151:8080/CarMood/SpeedServlet').then(function(r) {
      data.car.response = r.data;
      data.car.calls++;
      $timeout(poller, 60000);
    });
    $http.get('assets/data/weather.json').then(function(r) {
    //$http.get('http://10.13.10.151:8080/CarMood/WeatherServlet?lat=45.4973432&lon=9.2212303').then(function(r) {
      data.weather.response = r.data;
      data.weather.calls++;
      $timeout(poller, 60000);
    });
  };
  poller();
  
  return {
    data: data
  };
});


function getLocation(scope)
  {
  if (navigator.geolocation)
    {
    navigator.geolocation.getCurrentPosition(showPosition);
    }
  else{x.innerHTML="Geolocation is not supported by this browser.";}
  }
function showPosition(position)
  {
var x=document.getElementById("location");
  x.innerHTML="Latitude: " + position.coords.latitude + 
  "<br>Longitude: " + position.coords.longitude;	
  }