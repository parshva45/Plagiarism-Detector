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