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
            .when("/flightDetails", {
                templateUrl: "views/flights/templates/flightdetails.view.client.html",
                controller: "FlightDetailsController",
                controllerAs: "model"
            })
            .when("/flightDetails/passengerInfo", {
                templateUrl: "views/ticket/templates/ticket.details.view.client.html",
                controller: "TicketDetailsController",
                controllerAs: "model"
            })
            .when("/flightDetails/confirmed", {
                templateUrl: "views/ticket/templates/ticket-confirmed.view.client.html",
                controller: "HomeController",
                controllerAs: "model"
            })
            .when("/login", {
                templateUrl: "views/user/user/templates/login.view.client.html",
                controller: "LoginController",
                controllerAs: "model"
            })
            .when('/admin', {
                templateUrl: 'views/user/admin/templates/admin.profile.view.client.html',
                controller: 'AdminController',
                controllerAs: "model"
            })
            .when("/register", {
                templateUrl: "views/user/user/templates/register.view.client.html",
                controller: "RegisterController",
                controllerAs: "model"
            })
            .when("/profile", {
                templateUrl: "views/user/user/templates/profile.view.client.html",
                controller: "ProfileController",
                controllerAs: "model"
            })
            .when("/profile/:userId", {
                templateUrl: "views/user/user/templates/user.profile.view.client.html",
                controller: "UserProfileController",
                controllerAs: "model"
            })
            .when("/user/:userId", {
                templateUrl: "views/user/admin/templates/modify.user.profile.view.client.html",
                controller: "ModifyProfileController",
                controllerAs: "model"
            })
            .when("/airport/:airportCode", {
                templateUrl: "views/airport/templates/airport.view.client.html",
                controller: "AirportController",
                controllerAs: "model"
            })
            .when("/404", {
                templateUrl: "views/notfound/templates/page-not-found.view.client.html",
                controller: "NotFoundController",
                controllerAs: "model"
            })
            .otherwise({
                redirectTo: '/'

            });

    }

    function checkLogin($q, UserService, $location) {
        var deferred = $q.defer();
        UserService
            .loggedIn()
            .then(function (user) {
                if(user != '0') {
                    deferred.resolve(user);
                } else {
                    $location.url('/login');
                    deferred.reject();
                }
            });
        return deferred.promise;
    }

    function alreadyLoggedIn($q, UserService, $location) {
        var deferred = $q.defer();
        UserService
            .loggedIn()
            .then(function (user) {
                if(user != '0') {
                    if(user.role == "ADMIN")
                        $location.url('/admin');
                    else
                        $location.url('/profile');
                    deferred.reject();
                } else {
                    deferred.resolve(user);
                }
            });
        return deferred.promise;
    }

    function checkLoggedInUser($q, UserService) {
        var deferred = $q.defer();
        UserService
            .loggedIn()
            .then(function (user) {
                if(user != '0') {
                    deferred.resolve(user);
                }
                else{
                    deferred.resolve();
                }
            });
        return deferred.promise;
    }

    function checkAdmin($q, UserService, $location) {
        var defer = $q.defer();
        UserService
            .isAdmin()
            .then(function (user) {
                if(user != '0') {
                    defer.resolve(user);
                } else {
                    defer.reject();
                    $location.url('/');
                }
            });
        return defer.promise;
    }

})();