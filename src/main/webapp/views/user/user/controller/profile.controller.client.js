(function(){
    angular
        .module("PlagiarismDetector")
        .controller("ProfileController", profileController);

    function profileController($location, $scope, $routeParams, $uibModal, UserService) {
        var vm = this;
        vm.userId = $routeParams['userId'];
        vm.user = undefined;
        vm.userProfile = undefined;
        vm.hwId = 1;
        vm.courseId = 1;

        vm.update = update;

        function init() {
            UserService.findByUserIdAndUserName(vm.userId)
                .then(function(data){
                    vm.user = data.result[0];
                    vm.userProfile = angular.copy(vm.user);
                });

            UserService.getHomeWorksForStudent(vm.userId)
                .then(function(data){
                    vm.submissions = data.result;
                    for(var i=0; i<vm.submissions.length; i++){
                        var filename = vm.submissions[i].filePath.replace(/^.*[\\\/]/, '')
                        vm.submissions[i].fileName = filename
                    }
                });

            UserService.getCourseName(vm.courseId)
                .then(function(data){
                    vm.courseName = data.result.courseName;
                });

            UserService.getHomeWorkDescription(vm.hwId)
                .then(function(data){
                    vm.homeWorkDescription = data.result[0].description;
                });
        }
        init();

        function update (newUser) {
            console.log(vm.user.id);
            if(vm.userPassword !== undefined){
                if(vm.userPassword === vm.userPasswordConfirm) {
                    newUser.userPassword = vm.userPassword;
                    newUser.userPasswordConfirm = vm.userPasswordConfirm;
                    updateServiceWrapper(vm.user.id, newUser);
                }else
                    vm.error = "Passwords do not match";
            }else
                updateServiceWrapper(vm.user.id, newUser);
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