(function() {
    angular
        .module("PlagiarismDetector")
        .controller("UserProfileController", userProfileController);

    function userProfileController($location, $routeParams, UserService) {
        var vm = this;
        vm.userId = $routeParams['userId'];

        function init() {
            // UserService
            //     .findUserById(vm.userId)
            //     .then(function (user) {
            //         if(user.image == undefined)
            //             user.image= "https://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg";
            //         vm.user = user;
            //     }, function (error) {
            //         $location.url("/404");
            //     });
            // showFollow();
        }
        init();



    }
})();