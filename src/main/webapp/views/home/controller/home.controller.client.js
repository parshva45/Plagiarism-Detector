(function () {
    angular
        .module("PlagiarismDetector")
        .controller("HomeController", homeController);

    function homeController(HomeService, $location) {
        var vm = this;
        vm.loading = false;

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