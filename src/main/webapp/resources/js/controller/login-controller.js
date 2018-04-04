'use strict';

angular.module('helpDesk').controller('LoginController', ['$scope', function ($scope) {

    /* TODO правильный regExp учесть символы Unicode флаг u*/
    var regExpLogin = /^([^@.]+@.+\.[^@.]+)$/u;
    var regExpPass = /^((?=.*[0-9])(?=.*[.~"():;!<?>@#$%&'*+-\/=^_`{|}])(?=.*[a-z])(?=.*[A-Z])(.){6,})$/u;

    $scope.regExpLogin = regExpLogin;
    $scope.regExpPass = regExpPass;

}]);
