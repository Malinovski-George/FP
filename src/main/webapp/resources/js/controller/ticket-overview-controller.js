'use strict';

angular.module('helpDesk').controller('TicketOverviewController', ['$scope', '$window', 'TicketOverviewService', function ($scope, $window, TicketOverviewService) {
    var self = this;

    var regExpText = /^[a-zA-Z][\sa-zA-Z0-9-\~\.\"\(\)\,\:\;\<\>\@\[\]\!\#\$\%\&\'\*\+\-\/\=\?\^\_\`\{\|\}]{1,500}$/;

    self.isWholeTableShown = false;
    self.isButtonShowHiden = false;

    self.regExpText = regExpText;

    self.historyDto = {
        stringDate: '',
        userName: '',
        action: '',
        description: ''
    };

    self.stories = [];
    self.storiesAll = [];
    self.storiesTop = [];


    self.commentDto = {
        date: '',
        text: '',
        userName: '',
        stringDate: ''
    };

    self.comments = [];
    self.commentsAll = [];
    self.commentsTop = [];

    self.submit = submit;
    self.getStories = getStories;
    self.getComments = getComments;
    self.showTopTable = showTopTable;
    self.showWholeTable = showWholeTable;

    $scope.edit = edit;

    getAllStories();

    function edit() {
        $window.location.href = 'edit';
    }

    function getStories() {
        getAllStories();
        $window.document.getElementById('historyTable').style.display = 'block';
        $window.document.getElementById('commentsTable').style.display = 'none';
        $window.document.getElementById('comments').style.display = 'none';

    }

    function getComments() {
        getAllComments();

        $window.document.getElementById('historyTable').style.display = 'none';
        $window.document.getElementById('commentsTable').style.display = 'block';
        $window.document.getElementById('comments').style.display = 'block';

    }

    function submit() {
        self.commentDto.date = new Date();
        createComment(self.commentDto);
        getComments;
        reset();
    }

    function getAllStories() {
        TicketOverviewService.getAllStories()
            .then(
                function (d) {
                    self.storiesAll = d;
                    self.stories = [];
                    self.storiesTop = [];

                    if (self.storiesAll.length > 5) {
                        for (var i = 0; i < 5; i++) {
                            self.storiesTop.push(self.storiesAll[i]);
                        }

                        self.isButtonShowHiden = false;
                        self.stories = self.storiesTop;
                    } else {
                        self.isButtonShowHiden = true;
                        self.stories = self.storiesAll;
                    }
                    self.isWholeTableShown = false;

                },
                function (errResponse) {
                    console.error('Error while fetching Stories');
                }
            );
    }

    function getAllComments() {
        TicketOverviewService.getAllComments()
            .then(
                function (d) {
                    self.commentsAll = d;
                    self.comments = [];
                    self.commentsTop = [];
                    if (self.commentsAll.length > 5) {
                        for (var i = 0; i < 5; i++) {
                            self.commentsTop.push(self.commentsAll[i]);
                        }
                        self.isButtonShowHiden = false;
                        self.comments = self.commentsTop;
                    } else {
                        self.isButtonShowHiden = true;
                        self.comments = self.commentsAll;

                    }
                    self.isWholeTableShown = false;
                },
                function (errResponse) {
                    console.error('Error while fetching Stories');
                }
            );
    }

    function createComment(commentDto) {
        TicketOverviewService.createComment(commentDto)
            .then(
                getAllComments,
                function (errResponse) {
                    console.error('Error while creating Comment');
                }
            );
    }

    function reset() {
        self.commentDto = {
            date: '',
            text: '',
            userName: '',
            stringDate: ''
        };
        $scope.commentForm.$setPristine();
    }

    function showTopTable() {
        self.comments = self.commentsTop;
        self.stories = self.storiesTop;
        self.isWholeTableShown = false;
    }

    function showWholeTable() {
        self.comments = self.commentsAll;
        self.stories = self.storiesAll;

        self.isWholeTableShown = true;
    }

}]);
