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
                        console.log(user);
                        if(user.role === 1){
                            $location.url('/profile/'+user.id);
                        }else{
                            $location.url('/coursestaff/'+user.id);
                        }
                    }else{
                        vm.error = "Enter valid username and password combination";
                    }
                }, function (err) {
                    vm.error = err.statusText;
                });
        }
    }
})();