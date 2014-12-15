$(function () {
    $('#error_login').puigrowl();
    $('#error_login').puigrowl('show', [{
        severity: 'warn', summary: 'Login Failed', detail: 'Invalid Username or Password!'
    }]);
    $('#password').puipassword({inline: true});
});

var countryManagerModule = angular.module('countryManagerApp', ['ngRoute']);
countryManagerModule.controller('countryManagerController', function ($scope, $http) {
    $http.defaults.headers.post["Content-Type"] = "application/json";
    $scope.addCountry = function addCountry() {
        $http.post('/api/country', {
            name: $scope.name, codes: $scope.codes, code: $scope.code
        }).success(function(data, status, headers) {
            alert("New country added");
            var newCountryUri = headers()["location"];
            console.log("New added country URI GET " + newCountryUri);
        }).error(function(data, status, headers) {
            alert("New country added error " + status);
        });
    }
});
