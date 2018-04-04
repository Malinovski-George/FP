angular.module('helpDesk').controller('TableController', function($scope) {

});

window.onload=function(){
    $(document).ready(function () {
        $("#SearchInput").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#TicketTable tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
};