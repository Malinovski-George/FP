'use strict';

angular.module('helpDesk').factory('HomeService', ['$http', '$q', function ($http, $q) {

    var REST_SERVICE_URI = 'http://localhost:8889';

    return {
        getAllTickets: getAllTickets,
        getMyTickets: getMyTickets,
        updateState: updateState
    };

    function getAllTickets() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + '/tickets/list')
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while fetching Tickets');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function getMyTickets() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + '/user-tickets')
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while fetching Tickets');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function updateState(id, stateId) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI + '/tickets/' + id + '/edit?state=' + stateId)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while fetching Tickets');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

}]);
