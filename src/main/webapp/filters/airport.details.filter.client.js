(function () {
    angular
        .module("PlagiarismDetector")
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