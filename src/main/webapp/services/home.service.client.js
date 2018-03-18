(function () {
    angular
        .module("PlagiarismDetector")
        .factory("HomeService", homeService);

    function homeService($http) {
        var flightDetails = {};
        var api = {
        };
        return api;

    }
})();