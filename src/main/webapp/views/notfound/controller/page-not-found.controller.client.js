(function () {
    angular
        .module("BookYourTrip")
        .controller("NotFoundController", notFoundController);

    function notFoundController($location, currentUser, UserService) {
        var vm = this;
        vm.user = currentUser;
        vm.confirm = confirm;
        vm.logout = logout;

        function init() {

        }
        init();

        function logout() {
            UserService
                .logout()
                .then(function () {
                    $location.url('/');
                });
        }

        function back() {
           if(vm.user)
               $location.url("/profile")
            else
                $location.url("/login")
        }
    }
})();