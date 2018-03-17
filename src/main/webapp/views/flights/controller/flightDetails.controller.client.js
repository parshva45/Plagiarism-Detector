(function () {
    angular
        .module("BookYourTrip")
        .controller("FlightDetailsController", flightDetailsController);

    function flightDetailsController(HomeService, $location, currentUser, TicketService, UserService) {
        var vm = this;
        vm.oneAtATime = true;
        vm.currentUser = currentUser;
        vm.book = book;
        vm.loading = true;
        vm.logout = logout

        function init() {
            vm.details = HomeService.getFlightDetails();
            if(vm.details.length === undefined)
                $location.url("/");
            else
                vm.loading = false;
        }
        init();

        function logout() {
            UserService
                .logout()
                .then(function () {
                    $location.url('/');
                });
        }

        function book(trip) {
            TicketService.setSelectedTicketDetails(trip);
            $location.url("/flightDetails/passengerInfo")
        }
    }
})();