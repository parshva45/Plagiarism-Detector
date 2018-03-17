(function () {
    angular
        .module("PlagiarismDetector")
        .controller("LoginController", loginController);

    function loginController(UserService, $location) {
        var vm = this;
        vm.login = login;

        function login(user) {
            UserService
                .login(user)
                .then(function (user) {
                    if(user) {
                        // if(user.role == 1)
                        //     $location.url('/admin');
                        // else
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