(function() {
    angular
        .module("BookYourTrip")
        .controller("UserProfileController", userProfileController);

    function userProfileController($location, $routeParams, UserService, currentUser) {
        var vm = this;
        vm.currentUser = currentUser;
        vm.userId = $routeParams['userId'];
        vm.followUser = followUser;
        vm.logout = logout;

        function logout() {
            UserService
                .logout()
                .then(function () {
                    $location.url('/');
                });
        }

        function init() {
            UserService
                .findUserById(vm.userId)
                .then(function (user) {
                    if(user.image == undefined)
                        user.image= "https://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg";
                    vm.user = user;
                }, function (error) {
                    $location.url("/404");
                });
            showFollow();
        }
        init();

        function showFollow(){
            vm.follow = vm.currentUser.following.indexOf(vm.userId) == -1 && vm.currentUser._id != vm.userId;
        }

        function followUser() {
            UserService
                .addUserFollowingList(vm.userId)
                .then(function (user) {
                    vm.message = "Now Following "+user.firstName;
                    vm.follow = false;
                }, function (error) {
                    vm.error = "Error occurred while trying to follow"
                })
        }


    }
})();