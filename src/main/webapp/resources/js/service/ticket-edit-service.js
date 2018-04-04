'use strict';

angular.module('helpDesk').factory('TicketEditService', ['$http', '$q', function ($http, $q) {
    var REST_SERVICE_URI = /*[[@{/}]]*/ '';

    return {
        getTicket: getTicket,
        updateTicket: updateTicket,
        getAttachments: getAttachments,
        deleteAttachment: deleteAttachment
    };

    function getTicket() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + 'dto')
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while getting ticket');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function getAttachments() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + 'files')
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while getting attachments');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }


    function deleteAttachment(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI + 'files/' + id)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while deleting attachments');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function updateTicket(data) {
        var deferred = $q.defer();
        var config = {
            transformRequest: angular.identity,
            transformResponse: angular.identity,
            headers: {
                'Content-Type': undefined
            }
        };

        $http.post(REST_SERVICE_URI + 'edit', data, config)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while updating Ticket');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }
}]);
