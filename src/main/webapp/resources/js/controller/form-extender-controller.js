'use strict';

angular.module('helpDesk').controller('FormExtenderController', ['$scope', 'FormExtenderService', function ($scope, FormExtenderService) {
    var self = this;


    self.formExtender = {
        category: null,
        urgency: null
    };

    getFormFilling();

    function getFormFilling() {
        FormExtenderService.getFormFilling()
            .then(
                function (d) {
                    self.formExtender = d;
                },
                function (errResponse) {
                    console.error('Error while getting formExtender');
                }
            );
    }
}]);
