(function () {
    angular
        .module("BookYourTrip")
        .controller("CommentsDetails", userLoader);

    function userLoader(CommentsService, AirportService) {
        var vm = this;
        vm.render = render;

        function init() {
            // CommentsService
            //     .findCommentById($scope.comment)
            //     .then(function (comment) {
            //         AirportService.findAirportById(comment._airport)
            //             .then(function (airport) {
            //                 $scope.commentDetails = {
            //                     commentText : comment.comment,
            //                     commentDate : comment.dateCreated,
            //                     airportName : airport.name,
            //                     airportCode: comment.airportCode
            //                 }
            //             });
            //     });
        }
        init();

        function render() {
            CommentsService
                .findCommentById(vm.comment)
                .then(function (comment) {
                    AirportService.findAirportById(comment._airport)
                        .then(function (airport) {
                            vm.commentDetails = {
                                commentText : comment.comment,
                                commentDate : comment.dateCreated,
                                airportName : airport.name,
                                airportCode: comment.airportCode
                            }
                        });
                });
        }
    }
})();