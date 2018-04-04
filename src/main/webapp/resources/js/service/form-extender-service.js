'use strict';

angular.module('helpDesk').factory('FormExtenderService', ['$http', '$q', function ($http, $q) {

    var REST_SERVICE_URI = /*[[@{/}]]*/ '';

    return {
        getFormFilling: getFormFilling
    };

    function getFormFilling() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + 'init-form-data')
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while getting FormFilling');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }
}]);
