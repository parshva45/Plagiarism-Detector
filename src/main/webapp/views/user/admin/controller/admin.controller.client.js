(function(){
    angular
        .module("BookYourTrip")
        .controller("AdminController", adminController);

    function adminController($location, $uibModal, UserService, adminUser, CommentsService) {
        var vm = this;
        vm.logout = logout;
        vm.user = adminUser;
        vm.userProfile = angular.copy(vm.user);
        vm.deleteComment = deleteComment;
        vm.loadAllUsers = loadAllUsers;
        vm.loadAllComments = loadAllComments;
        vm.updateProfile = updateProfile;
        vm.deleteUser = deleteUser;
        vm.createUserModal = createUserModal;

        function init() {
            if(vm.user.image == undefined)
                vm.user.image= "https://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg";
            loadAllUsers();
            loadAllComments();
        }
        init();

        function createUserModal() {
            $uibModal.open({
                templateUrl: 'views/user/admin/templates/modal/create.user.admin.modal.view.client.html',
                controller: [ '$uibModal','UserService',createUserController],
                controllerAs: 'model'
            });
        }

        function logout() {
            UserService
                .logout()
                .then(function () {
                    $location.url('/');
                });
        }

        function loadAllUsers(){
            UserService
                .findAllUsers()
                .then(function (users) {
                    vm.allUsers = users.filter( function(item) {
                        return !(item._id == vm.user._id);
                    });
                }, function (err) {
                    vm.error = "error in loading All Users"
                });
        }

        function loadAllComments() {
            CommentsService
                .findAllComments()
                .then(function (allCommets) {
                    vm.allComments = allCommets;
                }, function (err) {
                    vm.error = "Error loading the commnets";
                });

        }

        function deleteComment(comment) {
            CommentsService
                .deleteComment(comment._id)
                .then(function (success) {
                    vm.allComments = vm.allComments.filter( function(item) {
                        return !(item._id == comment._id);
                    });
                }, function (err) {
                    vm.error = "unable to Delete comment";
                });

        }

        function updateProfile(userId) {
            UserService
                .updateProfile(userId)
                .then(function (user) {
                    vm.message = "user upgraded to Admin";
                    loadAllUsers();
                }, function (err) {
                    vm.error = "Error upgrading user to Admin role"
                })
        }

        function deleteUser(userId) {
            UserService
                .deleteUser(userId)
                .then(function (user) {
                    vm.message = "User Deleted";
                    loadAllUsers();
                }, function (err) {
                    vm.error = "Error Deleting"
                })
        }

    }

    function createUserController($uibModal, UserService) {
        var vm = this;
        vm.createUser = createUser;

        function createUser(user) {
            if(user && user.password && user.password2) {
                if (user.password === user.password2) {
                    UserService
                        .findUserByUserName(user.username)
                        .success(function (user) {
                            vm.error = "Sorry username '" + user.username + "' is already taken"
                        })
                        .error(function () {
                            UserService
                                .registerByAdmin(user)
                                .then(function (user) {
                                    vm.message = "User Created Successfully";
                                    vm.user = undefined;
                                    //TODO close modal
                                }, function (err) {
                                    vm.error = err.data.message;
                                });
                        });
                } else
                    vm.error = "passwords do not match";
            }else {
                vm.error = "please enter details";
            }

        }

        function close() {
            $uibModal.close();
        }

    }
})();