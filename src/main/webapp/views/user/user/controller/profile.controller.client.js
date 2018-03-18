(function(){
    angular
        .module("PlagiarismDetector")
        .controller("ProfileController", profileController);

    function profileController($location, $scope, $routeParams, $uibModal, UserService) {
        var vm = this;
        vm.userId = $routeParams['userId'];
        vm.user = undefined;

        function init() {
            console.log(vm.userId);
            vm.user = UserService.findByUserIdAndUserName(vm.userId);
            console.log(vm.user)
        }
        init();
    }

})();