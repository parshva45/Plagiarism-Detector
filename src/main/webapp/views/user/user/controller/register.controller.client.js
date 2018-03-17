(function () {
    angular
        .module("BookYourTrip")
        .controller("RegisterController", registerController);

    function registerController($location, UserService) {
        var vm = this;
        vm.createUser = createUser;

        function createUser(user) {
            if(user && user.password && user.password2 && user.emailId)  {
                if (user.password === user.password2) {
                    UserService
                        .findUserByUserName(user.username)
                        .success(function (user) {
                            vm.error = "Sorry username '" + user.username + "' is already taken"
                        })
                        .error(function () {
                            UserService
                                .createUser(user)
                                .then(function (user) {
                                    $location.url('/profile');
                                }, function (err) {
                                    vm.error = err.data.message;
                                });
                        });
                } else
                    vm.error = "passwords do not match";

            }else
                vm.error = "Please fill all details.";

        }
    }
})();