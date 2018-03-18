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
    }

})();