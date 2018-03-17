(function () {
    angular
        .module("BookYourTrip")
        .controller("AdminCommentsDetails", commentLoader);

    function commentLoader(CommentsService, AirportService, UserService) {
        var vm = this;
        vm.render = render;

        function init() {
        }
        init();

        function render() {
            CommentsService
                .findCommentById(vm.comment)
                .then(function (comment) {
                    AirportService.findAirportById(comment._airport)
                        .then(function (airport) {
                            UserService
                                .findUserByUserId(comment._user)
                                .then(function (user) {
                                    vm.commentDetails = {
                                        commentText : comment.comment,
                                        commentDate : comment.dateCreated,
                                        airportName : airport.name,
                                        airportCode: comment.airportCode,
                                        user: user.firstName,
                                        userId: user._id
                                    };
                                }, function (err) {
                                    vm.error = "Error Loading All Comments";
                                });
                        }, function (err) {
                            vm.error = "Error Loading All Comments";
                        });
                }, function (err) {
                    vm.error = "Error Loading All Comments";
                });
        }
    }
})();