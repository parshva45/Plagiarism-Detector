(function() {
    angular
        .module("BookYourTrip")
        .controller("ModifyProfileController", modifyProfileController);

    function modifyProfileController($location, $routeParams, UserService, adminUser, CommentsService) {
        var vm = this;
        vm.currentUser = adminUser;
        vm.userId = $routeParams['userId'];
        vm.deleteComment = deleteComment;
        vm.logout = logout;
        vm.update = update;
        vm.deleteUser = deleteUser;

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
                    vm.userProfile = angular.copy(vm.user);
                }, function (error) {
                    $location.url("/404");
                });
        }
        init();

        function deleteComment(comment) {
            CommentsService
                .deleteComment(comment)
                .then(function (success) {
                    vm.user.comments = vm.user.comments.filter( function(item) {
                        return !(item == comment);
                    });
                }, function (err) {
                    vm.error = "unable to Delete comment";
                });

        }

        function deleteUser(user) {
            UserService
                .deleteUser(user._id)
                .then(function (user) {
                    vm.message = "User Deleted";
                    $location.url("/admin");
                }, function (err) {
                    vm.error = "Error Deleting"
                })
        }

        function update (newUser) {
            if(vm.userPassword != undefined){
                if(vm.userPassword == vm.userPasswordConfirm) {
                    newUser.userPassword = vm.userPassword;
                    newUser.userPasswordConfirm = vm.userPasswordConfirm;
                    updateServiceWrapper(vm.user._id, newUser);
                }else
                    vm.error = "Passwords do not match";
            }else
                updateServiceWrapper(vm.user._id, newUser);
        }

        function updateServiceWrapper(userId, newUser){
            UserService
                .updateUser(userId, newUser)
                .then(function (user) {
                    angular.copy(vm.userProfile, vm.user);
                    vm.message = "user successfully updated";
                }, function (err) {
                    vm.error = err.data;
                });
        }

    }
})();