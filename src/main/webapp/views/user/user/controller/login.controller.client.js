(function () {
    angular
        .module("BookYourTrip")
        .controller("LoginController", loginController);

    function loginController(UserService, $location) {
        var vm = this;
        vm.login = login;

        function login(user) {
            UserService
                .login(user)
                .then(function (user) {
                    if(user) {
                        if(user.role == 'ADMIN')
                            $location.url('/admin');
                        else
                            $location.url('/profile');
                    }else{
                        vm.error = "Enter valid username and password combination";
                    }
                }, function (err) {
                    vm.error = err.statusText;
                });
        }
    }
})();