(function () {
    angular
        .module("BookYourTrip")
        .controller("UserDetails", userLoader);

    function userLoader(UserService) {
        var vm = this;
        vm.render = render;

        function init() {
            // if($scope.comments)
            //     userId = $scope.comments._user
            // else
            //     if($scope.following)
            //         userId = $scope.following;
            //
            // UserService
            //     .findUserByUserId(userId)
            //     .then(function (user) {
            //         if(user.image == undefined)
            //             user.image= "https://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg";
            //         $scope.user = user;
            //     })
        }
        init();

        function render() {
            UserService
                .findUserByUserId(vm.userId)
                .then(function (user) {
                    if(user.image == undefined)
                        user.image= "https://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg";
                    vm.user = user;
                })
        }

    }
})();