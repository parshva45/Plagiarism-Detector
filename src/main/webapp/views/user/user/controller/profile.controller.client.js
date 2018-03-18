(function(){
    angular
        .module("PlagiarismDetector")
        .controller("ProfileController", profileController);

    function profileController($location, $scope, $routeParams, $uibModal, UserService) {
        var vm = this;
        vm.userId = $routeParams['userId'];
        vm.unregisterUser = unregisterUser;
        vm.logout = logout;
        vm.update = update;
        vm.user = undefined;
        vm.userPassword = undefined;
        vm.userProfile = undefined;


        function init() {
            console.log(vm.userId);
            vm.user = UserService.findByUserIdAndUserName(vm.userId);
            console.log(vm.user)
            vm.userProfile = angular.copy(vm.user);
        }
        init();


        function logout() {
            UserService
                .logout()
                .then(function () {
                    $location.url('/');
                });
        }

        function unregisterUser(user) {
            var answer = confirm("Are you sure?");
            if(answer) {
                UserService
                    .deleteUser(user._id)
                    .then(function () {
                        $location.url("/");
                    }, function (err) {
                        vm.error = 'unable to remove user';
                    });
            }
        }

        function update (newUser) {
            if(vm.userPassword !== undefined){
                if(vm.userPassword === vm.userPasswordConfirm) {
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