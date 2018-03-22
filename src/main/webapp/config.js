(function(){
    angular
        .module("PlagiarismDetector")
        .config(configuration);

    function configuration($routeProvider, $httpProvider) {
        $httpProvider.defaults.headers.post['Content-Type'] = 'application/json;charset=utf-8';
        $httpProvider.defaults.headers.put['Content-Type'] = 'application/json;charset=utf-8';
        $routeProvider
            .when("/", {
                templateUrl: "views/home/templates/home.view.client.html",
                controller: "HomeController",
                controllerAs: "model"

            })
            .when("/login", {
                templateUrl: "views/user/user/templates/login.view.client.html",
                controller: "LoginController",
                controllerAs: "model"
            })
            .when("/register", {
                templateUrl: "views/user/user/templates/register.view.client.html",
                controller: "RegisterController",
                controllerAs: "model"
            })
            .when("/profile/:userId", {
                templateUrl: "views/user/user/templates/profile.view.client.html",
                controller: "ProfileController",
                controllerAs: "model"
            })
            .when('/coursestaff/:userId', {
                templateUrl: 'views/user/admin/templates/admin.profile.view.client.html',
                controller: 'AdminController',
                controllerAs: 'model'
            })
            .when('/coursestaff/:userId/checkplagiarism', {
                templateUrl: 'views/user/admin/templates/admin.profile.view.client.html',
                controller: 'AdminController',
                controllerAs: 'model'
            })
            .otherwise({
                redirectTo: '/'

            });
    }

})();