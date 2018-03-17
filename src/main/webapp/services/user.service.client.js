(function () {
    angular
        .module("BookYourTrip")
        .factory("UserService", userService);

    function userService($http) {
        var api = {
            "login" : login,
            "logout": logout,
            "findUserByCredentials": findUserByCredentials,
            "findUserById": findUserById,
            "createUser": register,
            "updateUser": updateUser,
            "findUserByUserName": findUserByUserName,
            "deleteUser" : deleteUser,
            "isAdmin" :isAdmin,
            "updateProfile" :updateProfile,
            "findUserByUserId": findUserByUserId,
            "addUserFollowingList": addUserFollowingList,
            "removeFollowerById": removeFollowerById,
            "unFollowUserById": unFollowUserById,
            "findAllUsers": findAllUsers,
            "registerByAdmin": registerByAdmin
        };
        return api;

        function findAllUsers(){
            return $http.get("/api/allUsers")
                .then(function (response) {
                    return response.data;
                });
        }

        function login(user) {
            return $http.post('/api/login', user)
                .then(function (response) {
                    return response.data;
                });
        }

        function logout() {
            return $http.post('/api/logout')
                .then(function (response) {
                    return response.data;
                });
        }

        function isAdmin() {
            return $http.post('/api/isAdmin')
                .then(function (response) {
                    return response.data;
                });
        }

        function updateProfile(user) {
            return $http.put('/api/updateProfile/' + user)
                .then(function (response) {
                    return response.data;
                });
        }

        function deleteUser(userId) {
            return $http.delete('/api/user/' + userId)
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

        function registerByAdmin(user) {
            return $http.post('/api/registerByAdmin', user)
                .then(function (response) {
                    return response.data;
                });
        }

        function findUserByUserName(username) {
            return $http.get("/api/user?username="+username);
        }

        function updateUser(userId, newUser) {
            return $http.put("/api/user/"+userId, newUser);
        }

        function findUserById(userId) {
            return $http.get("/api/user/"+userId)
                .then(function (response) {
                    return response.data;
                });
        }

        function findUserByUserId(userId) {
            return $http.get("/api/user/id/"+userId)
                .then(function (response) {
                    return response.data;
                });
        }

        function findUserByCredentials(username, password) {
            return $http.get("/api/user?username="+username+"&password="+password);
        }

        function addUserFollowingList(userId){
            return $http.get("/api/user/follow/"+userId)
                .then(function (response) {
                    return response.data;
                });
        }

        function removeFollowerById(userId){
            return $http.get("/api/user/removeFollower/"+userId)
                .then(function (response) {
                    return response.data;
                });
        }

        function unFollowUserById(userId) {
            return $http.get("/api/user/unFollow/"+userId)
                .then(function (response) {
                    return response.data;
                });
        }

    }
})();