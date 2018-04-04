'use strict';

angular.module('helpDesk').factory('FeedbackService', ['$http', '$q', '$location', function ($http, $q, $location) {

    var url = $location.url();

    return {
        createFeedback: createFeedback,
    };

    function createFeedback(feedbackDto) {
        var deferred = $q.defer();
        $http.post(url, feedbackDto)
            .then(

                function (errResponse) {
                    console.error('Error while creating feedback');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }
}]);
