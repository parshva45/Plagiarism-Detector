(function () {
    angular
        .module("BookYourTrip")
        .controller("AirportDetails", airportLoaded);

    function airportLoaded(AirportService) {

        function init() {
            // AirportService
            //     .findAirportById($scope.comment)
            //     .then(function (airport) {
            //         $scope.airport = airport;
            //     })
        }
        init();

    }
})();