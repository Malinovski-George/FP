'use strict';

angular.module('helpDesk').controller('TicketController', ['$scope', '$window', 'TicketService', '$http', function ($scope, $window, TicketService, $http) {

    var self = this;

    var regExpName = /^[a-zA-Z][\sa-zA-Z0-9-\~\.\"\(\)\,\:\;\<\>\@\[\]\!\#\$\%\&\'\*\+\-\/\=\?\^\_\`\{\|\}]{1,100}$/;
    var regExpText = /^[a-zA-Z][\sa-zA-Z0-9-\~\.\"\(\)\,\:\;\<\>\@\[\]\!\#\$\%\&\'\*\+\-\/\=\?\^\_\`\{\|\}]{1,500}$/;
    self.regExpName = regExpName;
    self.regExpText = regExpText;
    self.date = new Date();
    self.isNew = false;

    var REST_SERVICE_URI = /*[[@{/}]]*/ '';

    $scope.sortType = 'name';
    $scope.sortReverse = false;
    $scope.date = new Date();
    $scope.files = [];


    self.ticket = {
        id: -1,
        categoryId: '',
        name: '',
        description: '',
        urgency: '',
        state: 0,
        desiredResolutionDate: null,
        desiredResolutionDateString: '',
        stateName: '',
        urgencyName: '',
        createdOn: $scope.date.getTime(),
        comment: ''
    };

    self.formExtender = {
        category: null,
        urgency: null
    };

    self.tickets = [];

    self.submit = submit;

    function submit() {
        if (self.isNew == true) {
            self.ticket.state = 1;
        }
        createTicket();
    }


    function createTicket() {

        if(self.ticket.desiredResolutionDate != null) {
            self.ticket.desiredResolutionDate = self.ticket.desiredResolutionDate.getTime();
        }

        var files = $scope.files;
        var data = new FormData();
        data.append("ticket", angular.toJson(self.ticket));
        for (var i = 0; i < files.length; i++) {
            data.append("file" + i, files[i]);
        }

        TicketService.createTicket(data)
            .then(
                $window.location.href = REST_SERVICE_URI + '/home.html',
                console.info('createTicket' + self.tickets.length),
                function (errResponse) {
                    console.error('Error while creating Ticket');
                }
            );
    }

    $scope.$on("fileSelected", function (event, args) {
        $scope.$apply(function () {
            $scope.files.push(args.file);
        });
    });

}]);

