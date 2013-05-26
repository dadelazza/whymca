'use strict';

var app = angular.module('CarMoodApp', [])
  .config(['$routeProvider',function ($routeProvider) {
    $routeProvider
      .when('/', {templateUrl: 'views/home.html', controller: 'MainCtrl'})
            .when('/game', {templateUrl: 'views/game.html', controller: 'GameCtrl'})
            .otherwise({redirectTo: '/'});
  }]);

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