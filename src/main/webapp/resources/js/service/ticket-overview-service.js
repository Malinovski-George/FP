'use strict';

angular.module('helpDesk').factory('TicketOverviewService', ['$http', '$q', function ($http, $q) {

    var REST_SERVICE_URI = /*[[@{/}]]*/ '';

    return {
        getAllStories: getAllStories,
        getAllComments: getAllComments,
        createComment: createComment,
    };


    function getAllStories() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + 'history')
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while fetching Stories');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function getAllComments() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + 'comments')
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while fetching Comments');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function createComment(commentDto) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI + 'comments', commentDto)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while creating Comment');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }
}]);
