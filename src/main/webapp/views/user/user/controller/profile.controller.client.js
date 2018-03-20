(function(){
    angular
        .module("PlagiarismDetector")
        .controller("ProfileController", profileController);

    function profileController($location, $scope, $routeParams, $uibModal, UserService) {
        var vm = this;
        vm.userId = $routeParams['userId'];
        vm.user = undefined;
        vm.userProfile = undefined;

        function init() {
            UserService.findByUserIdAndUserName(vm.userId)
                .then(function(data){
                    vm.user = data.result[0];
                });

            vm.userProfile = angular.copy(vm.user);
            console.log(vm.user)
        }
        init();


    }

})();