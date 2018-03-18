(function () {
    angular
        .module("PlagiarismDetector")
        .factory("UserService", userService);

    function userService($http) {
        var api = {
            "login" : login,
            "findByUserIdAndUserName" : findByUserIdAndUserName,
            "createUser": register
        };
        return api;

        function login(user) {
            return $http.post('/api/login', user)
                .then(function (response) {
                    return response.data;
                });
        }

        function register(user) {
            return $http.post('/api/register', user)
                .then(function (response) {
                    return response.data;
                });
        }


        function findByUserIdAndUserName(userId){
            return $http({
                url: "/api/user",
                method: "GET",
                params: {id: userId}
            }).then(function (response) {
                return response.data;
            });
        }

    }
})();