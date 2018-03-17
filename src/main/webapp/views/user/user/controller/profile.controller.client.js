(function(){
    angular
        .module("BookYourTrip")
        .controller("ProfileController", profileController);

    function profileController($location, $scope, $uibModal, UserService, currentUser, CommentsService, TicketService) {
        var vm = this;
        vm.unregisterUser = unregisterUser;
        vm.logout = logout;
        vm.update = update;
        vm.user = currentUser;
        vm.userPassword = undefined;
        vm.userProfile = angular.copy(vm.user);
        vm.deleteComment = deleteComment;
        vm.cancelTicket = cancelTicket;
        vm.removeFollowing = removeFollowing;
        vm.removeFollower = removeFollower;

        function init() {
            if(vm.user.role == 'ADMIN')
                $location.url('/admin');
            if(vm.user.image == undefined)
                vm.user.image= "https://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg";
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

        function removeFollowing(userId) {
            UserService
                .unFollowUserById(userId)
                .then(function (user) {
                    vm.user.following = vm.user.following.filter( function(item) {
                        return !(item == userId);
                    });
                }, function (err) {
                    vm.error = "unable to Remove Following";
                });
        }

        function removeFollower(userId) {
            UserService
                .removeFollowerById(userId)
                .then(function (user) {
                    vm.user.followed = vm.user.followed.filter( function(item) {
                        return !(item == userId);
                    });
                }, function (err) {
                    vm.error = "unable to Remove Follower";
                });
        }

        function cancelTicket(ticket) {
            TicketService
                .cancelTicket(ticket)
                .then(function () {
                    vm.message = "ticket Cancelled";
                }, function (error) {
                    vm.error = "Unable to cancel Ticket";
                })
        }

        vm.open = function(details) {
            $scope.trips = details;
            var modalinstance = $uibModal.open({
                templateUrl: "views/user/user/templates/tabs/ticket-detail.modal.tab.view.client.html",
                scope : $scope,
                resolve: {
                    trips: details
                }
            })
        };
    }

})();