(function () {
    angular
        .module("PlagiarismDetector")
        .factory("UserService", userService);

    function userService($http) {
        var api = {
            "login" : login,
            "findByUserIdAndUserName" : findByUserIdAndUserName,
            "findStudentHomeWorksForCourseHomeWork": findStudentHomeWorksForCourseHomeWork,
            "createUser": register,
            "updateUser": updateUser,
            "calculateSimilarityMeasure": calculateSimilarityMeasure,
            "listStrategies": listStrategies,
            "getHomeWorksForStudent": getHomeWorksForStudent,
            "getCourseName": getCourseName,
            "getHomeWorkDescription": getHomeWorkDescription,
            "getSystemStatus": getSystemStatus,
            "getCount":getCount,
            "calculateSimilarityAll": calculateSimilarityAll
        };
        return api;

        function calculateSimilarityAll(firstFile, secondFile){
            return $http({
                url: "/api/comparison/compareByAllStrategies",
                method: "GET",
                params: {firstFile:firstFile, secondFile:secondFile}
            }).then(function (response) {
                return response.data;

            });
        }

        function getCount() {
            return $http({
                url: "/api/comparison/getCount",
                method: "GET"
            }).then(function (response) {
                return response.data;
            });
        }

        function getSystemStatus() {
            return $http({
                url: "/metrics",
                method: "GET"
            }).then(function (response) {
                return response.data;
            });
        }

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

        function findStudentHomeWorksForCourseHomeWork(courseId, homeWorkId){
            return $http({
                url: "/api/studentHomeWork/getStudentHomeWorksForCourseHomeWork/"+courseId+"/"+homeWorkId,
                method: "GET",
            }).then(function (response) {
                return response.data;
            });
        }

        function calculateSimilarityMeasure(strategy, firstFile, secondFile){
            return $http({
                url: "/api/comparison/compare2filesByStrategy",
                method: "GET",
                params: {strategy:strategy, firstFile:firstFile, secondFile:secondFile}
            }).then(function (response) {
                return response.data;
            });
        }

        function listStrategies() {
            return $http({
                url: "/api/comparison/listStrategies",
                method: "GET"
            }).then(function (response) {
                return response.data;
            });
        }

        function updateUser(userId, newUser) {
            return $http.put("/api/user/"+userId, newUser);
        }

        function getHomeWorksForStudent(userId){
            return $http({
                url: "/api/studentHomeWork/getHomeWorksForStudent/"+userId,
                method: "GET"
            }).then(function (response) {
                return response.data;
            });
        }

        function getCourseName(courseId){
            return $http({
                url: "/api/course/getCourseById/"+courseId,
                method: "GET"
            }).then(function (response) {
                return response.data;
            });
        }

        function getHomeWorkDescription(homeWorkId){
            return $http({
                url: "/api/homework/getHomeWorkById/"+homeWorkId,
                method: "GET"
            }).then(function (response) {
                return response.data;
            });
        }

    }
})();