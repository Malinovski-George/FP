'use strict';

angular.module('helpDesk').controller('HomeController', ['$scope', '$window', 'HomeService', function ($scope, $window, HomeService) {
    var self = this;
    var REST_SERVICE_URI = /*[[@{/}]]*/ '';
    $scope.searchTicket   = '';

    self.tickets = [];

    self.getAllTickets = getAllTickets;
    self.getMyTickets = getMyTickets;
    self.overview = overview;
    self.transfer = transfer;

    $scope.searchTicket   = '';

    function transfer(id, state) {

        var stateId;

        if (state == "Submit") {
            stateId = 1;
            updateState(id, stateId);

        }
        if (state == "Approve") {
            stateId = 2;
            updateState(id, stateId);

        }
        if (state == "Decline") {
            stateId = 3;
            updateState(id, stateId);

        }
        if (state == "Assignee to me") {
            stateId = 4;
            updateState(id, stateId);

        }
        if (state == "Done") {
            stateId = 5;
            updateState(id, stateId);

        }
        if (state == "Cancel") {
            stateId = 6;
            updateState(id, stateId);

        }
     if (state == "Leave feedback") {

         $window.location.href = REST_SERVICE_URI + '/tickets/' + id + '/feedback';

        }
    }

    getAllTickets();

    function getAllTickets() {
        HomeService.getAllTickets()
            .then(
                function (d) {
                    self.tickets = d;
                },
                function (errResponse) {
                    console.error('Error');
                }
            );
    }

    function getMyTickets() {
        HomeService.getMyTickets()
            .then(
                function (d) {
                    self.tickets = d;
                },
                function (errResponse) {
                    console.error('Error');
                }
            );
    }

    function updateState(id, stateId) {
        HomeService.updateState(id, stateId)
            .then(
                function () {
                    getAllTickets();
                },
                function (errResponse) {
                    console.error('Error');
                }
            );
    }

    function overview(id) {
        $window.location.href = REST_SERVICE_URI + 'tickets/' + id + '/overview';
    }


}]);
