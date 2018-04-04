'use strict';

angular.module('helpDesk').controller('TicketEditController', ['$scope', '$window', '$location', 'TicketEditService',
    function ($scope, $window, $location, TicketEditService) {
        var self = this;

        var regExpName = /^[a-z][\sa-z0-9-\~\.\"\(\)\,\:\;\<\>\@\[\]\!\#\$\%\&\'\*\+\-\/\=\?\^\_\`\{\|\}]{1,100}$/;
        var regExpText = /^[a-zA-Z][\sa-zA-Z0-9-\~\.\"\(\)\,\:\;\<\>\@\[\]\!\#\$\%\&\'\*\+\-\/\=\?\^\_\`\{\|\}]{1,500}$/;
        self.regExpName = regExpName;
        self.regExpText = regExpText;

        $scope.date = new Date();
        $scope.files = [];
        self.isDeleted = false;
        self.isNew = false;

        var REST_SERVICE_URI = /*[[@{/}]]*/ '';

        self.submit = submit;
        self.save = save;
        self.getTicket = getTicket;
        self.overview = overview;
        self.delete = deleteAttachment;

        self.ticketDto = {
            id: -1,
            categoryId: '',
            name: '',
            description: '',
            urgency: '',
            urgencyId: '',
            state: '',
            resolutionDate: '',
            desiredResolutionDate: '',
            createdOn: '',
            comment: '',

        };

        self.attachments = [];

        getTicket();
        getAttachments();

        function overview() {
            $window.location.href = REST_SERVICE_URI + "overview";
        }

        function submit() {
            if (self.isNew) {
                self.ticketDto.state = 1;
            }
            save();
        }

        function save() {
            if (self.ticketDto.state != 1) {
                self.ticketDto.state = 0;
            }

            if (self.ticketDto.resolutionDate == '') {
                self.ticketDto.desiredResolutionDate = null;
            } else {
                self.ticketDto.desiredResolutionDate = self.ticketDto.resolutionDate.getTime();
            }
            self.ticketDto.urgency = self.ticketDto.urgencyId;
            self.ticketDto.createdOn = Date.now();

            var files = $scope.files;
            var data = new FormData();
            data.append("ticket", angular.toJson(self.ticketDto));
            for (var i = 0; i < files.length; i++) {
                data.append("file" + i, files[i]);
            }
            updateTicket(data);
        }

        function getTicket() {
            TicketEditService.getTicket()
                .then(
                    function (d) {
                        self.ticketDto = d;
                        if (self.ticketDto.desiredResolutionDate == null) {
                            self.ticketDto.resolutionDate = '';
                        } else {
                            self.ticketDto.resolutionDate = new Date(self.ticketDto.desiredResolutionDate);
                        }
                    },
                    function (errResponse) {
                        console.error('Error while getting Ticket');
                    }
                );
        }

        function getAttachments() {
            TicketEditService.getAttachments()
                .then(
                    function (d) {
                        self.attachments = d;
                    },
                    function (errResponse) {
                        console.error('Error while getting Attachments');
                    }
                );
        }

        function deleteAttachment(att, id) {
            TicketEditService.deleteAttachment(id)
                .then(
                    function (d) {
                        att.isDeleted = true;
                    },
                    function (errResponse) {

                        console.error('Error while getting Ticket');
                    }
                );
        }


        function updateTicket(data) {
            TicketEditService.updateTicket(data)
                .then(
                    function (response) {
                        $window.location.href = REST_SERVICE_URI + '/tickets/' + self.ticketDto.id + '/overview';
                    },
                    function (errResponse) {
                        console.error('Error while updating Ticket');
                    }
                );
        }

        $scope.$on("fileSelected", function (event, args) {
            $scope.$apply(function () {
                $scope.files.push(args.file);
            });
        });

    }]);



