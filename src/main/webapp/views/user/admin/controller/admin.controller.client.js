(function(){
    angular
        .module("PlagiarismDetector")
        .controller("AdminController", adminController);

    function adminController($location, $uibModal, $routeParams, UserService) {
        var vm = this;
        vm.userId = $routeParams['userId'];
        vm.user = undefined;
        vm.userProfile = undefined;
        vm.studentHomeWorks = undefined;
        vm.students = [];
        vm.hwId = 1;
        vm.courseId = 1;

        function init() {
            UserService.findByUserIdAndUserName(vm.userId)
                .then(function(data){
                    vm.user = data.result[0];
                    vm.userProfile = angular.copy(vm.user);
                });

            UserService.findStudentHomeWorksForCourseHomeWork(vm.courseId, vm.hwId)
                .then(function(data){
                    vm.studentHomeWorks = data.result;
                    for(var i=0; i<vm.studentHomeWorks.length; i++){
                        var studentId = vm.studentHomeWorks[i].userId;
                        UserService.findByUserIdAndUserName(studentId)
                        .then(function(data){
                            vm.students.push(data.result[0]);
                        });
                    }
                });
        }
        init();



    }
})();