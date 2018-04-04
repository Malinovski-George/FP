'use strict';

angular.module('helpDesk').controller('FeedbackController', ['$scope', '$window', 'FeedbackService', function ($scope, $window, FeedbackService) {


    var self = this;
    var regExpText = /^[a-zA-Z][\sa-zA-Z0-9-\~\.\"\(\)\,\:\;\<\>\@\[\]\!\#\$\%\&\'\*\+\-\/\=\?\^\_\`\{\|\}]{1,500}$/;
    var REST_SERVICE_URI = /*[[@{/}]]*/ '';

    self.regExpText = regExpText;

    self.feedbackDto = {
        rate: '',
        date: '',
        text: ''
    };

   self.submit = submit;

    function submit() {
        self.feedbackDto.date = new Date();
        createFeedback(self.feedbackDto);
    }

    function createFeedback(feedbackDto) {
        FeedbackService.createFeedback(feedbackDto)
            .then(
                $window.location.href = REST_SERVICE_URI + '/home.html',
                function (errResponse) {
                    console.error('Error while creating Comment');
                }
            );
    }

}]);

