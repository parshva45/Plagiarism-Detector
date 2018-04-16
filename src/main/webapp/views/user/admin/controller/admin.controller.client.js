(function(){
    angular
        .module("PlagiarismDetector")
        .controller("AdminController", adminController);

    function adminController($location, $uibModal, $routeParams, $scope, UserService) {
        var vm = this;
        vm.userId = $routeParams['userId'];
        vm.user = undefined;
        vm.userProfile = undefined;
        vm.studentHomeWorks = undefined;
        vm.students = [];
        vm.hwId = 1;
        vm.courseId = 1;
        vm.getSimilarityByAll = getSimilarityByAll;
        vm.compareAll = compareAll,
        $scope.systemStatus = undefined;
        $scope.result_ready = false;
        $scope.countCalls = undefined;
        $scope.fromAll = undefined;
        vm.getCount = getCount;
        vm.findUserName1 = findUserName1;
        vm.findUserName2 = findUserName2;
        $scope.selectedCompareAll = undefined;

        function compareAll() {
            UserService
                .compareAll(vm.userId)
                .then(function (data) {
                    $scope.selectedCompareAll = "Details Will be sent to your registered email";
                });
        }

        function getSimilarityByAll(studentId1, studentId2) {
            if(studentId1 === undefined || studentId2 === undefined){
                vm.error = "Please select both the students";
            }
            else{
                var filePath1 = "";
                var filePath2 = "";
                for(var i=0; i<vm.studentHomeWorks.length; i++){
                    if(vm.studentHomeWorks[i].userId == studentId1){
                        filePath1 = vm.studentHomeWorks[i].filePath;
                    }
                    else if(vm.studentHomeWorks[i].userId == studentId2){
                        filePath2 = vm.studentHomeWorks[i].filePath;
                    }
                    if(filePath1 != "" && filePath2 != "")
                        break;
                }
                UserService.calculateSimilarityAll(filePath1, filePath2)
                    .then(function(data){

                        $scope.fromAll = data;
                    });
            }

        }

        function getCount() {
            UserService
                .getCount()
                .then(function (data) {
                    $scope.countCalls = data;
                });
        }

        function init() {
            UserService.findByUserIdAndUserName(vm.userId)
                .then(function(data){
                    vm.user = data.result[0];
                    vm.userProfile = angular.copy(vm.user);
                });

            UserService.getSystemStatus()
                .then(function (value) {
                    $scope.systemStatus = value;
                });

            UserService.findStudentHomeWorksForCourseHomeWork(vm.courseId, vm.hwId)
                .then(function(data){
                    vm.studentHomeWorks = data.RESULT;
                    for(var i=0; i<vm.studentHomeWorks.length; i++){
                        var studentId = vm.studentHomeWorks[i].userId;
                        UserService.findByUserIdAndUserName(studentId)
                        .then(function(data){
                            vm.students.push(data.result[0]);
                        });
                    }
                });

             UserService.listStrategies()
                .then(function(data){
                    vm.strategies = data.strategies;
                });

            UserService.getCourseName(vm.courseId)
                .then(function(data){
                    vm.courseName = data.RESULT.courseName;
                });

            UserService.getHomeWorkDescription(vm.hwId)
                .then(function(data){
                    vm.homeWorkDescription = data.RESULT[0].description;
                });
        }
        init();

        $scope.submitForm = function(strategy, studentId1, studentId2) {
            $scope.fromAll = undefined;
            if(studentId1 === undefined || studentId2 === undefined){
                vm.error = "Please select both the students";
            }
            else if(strategy === undefined)
                vm.error = "Please select some strategy";
            else{
                findUserName1(studentId1);
                findUserName2(studentId2);
                var filePath1 = "";
                var filePath2 = "";
                for(var i=0; i<vm.studentHomeWorks.length; i++){
                    if(vm.studentHomeWorks[i].userId == studentId1){
                        filePath1 = vm.studentHomeWorks[i].filePath;
                    }
                    else if(vm.studentHomeWorks[i].userId == studentId2){
                        filePath2 = vm.studentHomeWorks[i].filePath;
                    }
                    if(filePath1 != "" && filePath2 != "")
                        break;
                }
                $scope.calculateSimilarityMeasure(strategy, filePath1, filePath2);
            }
        };

        function findUserName1(userId){
            UserService.findByUserIdAndUserName(userId)
                .then(function(data){
                    $scope.user1 =  data.result[0].userName;
                });
        }

        function findUserName2(userId){
            UserService.findByUserIdAndUserName(userId)
                .then(function(data){
                    $scope.user2 =  data.result[0].userName;
                });
        }

        $scope.calculateSimilarityMeasure = function(strategy, firstFile, secondFile){
            UserService.calculateSimilarityMeasure(strategy, firstFile, secondFile)
                .then(function(data){
                    $scope.file1 = firstFile.replace(/^.*[\\\/]/, '')
                    $scope.file2 = secondFile.replace(/^.*[\\\/]/, '')
                    $scope.similarity_measure = data.similarity;
                    $scope.chosen_strategy = strategy;
                    $scope.result_ready = true;
                    $scope.file1Content = data.file1Html;
                    $scope.file2Content = data.file2Html;

                    var result = 'Plagiarism Found';
                    if(data.plagiarism !== "Yes"){
                        result = 'Plagiarism Not Found';
                    }
                    $scope.collapsibleElements = [{
                        color: 'red',
                        icon: 'mdi-image-filter-drama',
                        title: result,
                        content: 'Lorem ipsum dolor sit amet.'
                    }];
                });
        };

    }
})();