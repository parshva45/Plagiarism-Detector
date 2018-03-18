(function () {
    angular
        .module("PlagiarismDetector")
        .controller("HomeController", homeController);

    function homeController(HomeService, $location, currentUser, UserService) {
        var vm = this;
        vm.loading = false;
        vm.currentUser = currentUser;
        vm.logout = logout;

        // function logout() {
        //     UserService
        //         .logout()
        //         .then(function () {
        //             vm.currentUser = undefined;
        //             $location.url('/');
        //         });
        // }

        function init() {

        }
        init();

    }
})();