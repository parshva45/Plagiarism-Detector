(function () {
    angular
        .module("BookYourTrip")
        .controller("TicketDetailsController", flightDetailsController);

    function flightDetailsController($location, currentUser, TicketService, UserService) {
        var vm = this;
        vm.user = currentUser;
        vm.confirm = confirm;
        vm.logout = logout;

        function init() {
            vm.ticketDetails = TicketService.getSelectedTicketDetails();
            if(vm.user==undefined || vm.ticketDetails.departureTime == undefined){
                $location.url("/login")
            }
        }
        init();

        function logout() {
            UserService
                .logout()
                .then(function () {
                    $location.url('/');
                });
        }

        function confirm(trip) {
            TicketService
                .createTicket(trip)
                .then(function (ticket) {
                    vm.message = "Booking confirmed";
                    $location.url("/profile")
                }, function (err) {
                    vm.error = "Error Booking your Flight";
                });
        }
    }
})();