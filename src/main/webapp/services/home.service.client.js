(function () {
    angular
        .module("BookYourTrip")
        .factory("HomeService", homeService);

    function homeService($http) {
        var flightDetails = {};
        var api = {
            "findFlightsByDetails": findFlightsByDetails,
            "getFlightDetails" : getFlightDetails,
            "setFlightDetails" : setFlightDetails
        };
        return api;

        function getFlightDetails() {
                return flightDetails;
        }

        function setFlightDetails(value) {
                flightDetails = value;
        }

        function findFlightsByDetails(flightData) {
            return $http.post("/api/flightDetails",flightData);
        }

    }
})();