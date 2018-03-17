(function () {
    angular
        .module("BookYourTrip")
        .controller("HomeController", homeController);

    function homeController(HomeService, $location, currentUser, UserService) {
        var vm = this;
        vm.loading = false;
        vm.flight = {};
        vm.searchFlight = searchFlight;
        vm.currentUser = currentUser;
        vm.logout = logout;

        function logout() {
            UserService
                .logout()
                .then(function () {
                    vm.currentUser = undefined;
                    $location.url('/');
                });
        }

        function init() {
            vm.flight.depart = new Date();
        }
        init();

        function searchFlight(flight) {
            vm.loading = true;
            if(flight === undefined) {
                vm.error = "Please fill All Details";
                vm.loading = false;
            }
            else {
                flight.depart = new Date(vm.flight.depart).toISOString();
                var promise = HomeService.findFlightsByDetails(flight);
                promise.success(function (data) {
                    HomeService.setFlightDetails(data);
                    $location.url("/flightDetails")
                    })
                    .error(function (err) {
                        vm.error = "Error finding flights";
                    });
            }
        }
    }
})();