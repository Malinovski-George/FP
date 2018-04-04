'use strict';

angular.module('helpDesk').factory('TicketService', ['$http', '$q', function ($http, $q) {

    var REST_SERVICE_URI = 'http://localhost:8889';
    var uri = REST_SERVICE_URI + '/tickets';

    return {
        createTicket: createTicket,
    };

    function createTicket(data) {
        var deferred = $q.defer();
        var config = {
            transformRequest: angular.identity,
            transformResponse: angular.identity,
            headers: {
                'Content-Type': undefined
            }
        };

        $http.post(uri, data, config)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                    console.error('createTicket');
                },
                function (errResponse) {
                    console.error('Error while creating Ticket');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

}]);
