(function () {
    angular
        .module('BookYourTrip')
        .directive('bytDatetime', datetime);

    function datetime() {
        return {
            restrict: 'A',
            require: 'ngModel',
            link: function (scope, element, attrs, ngModelCtrl) {
                scope.deprt = new Date();
                element.datetimepicker({
                    dateFormat: 'dd-MM-yyyy',
                    language: 'en',
                    pickTime: false,
                    startDate: '01-11-2013',      // set a minimum date
                    endDate: '01-11-2030'          // set a maximum date
                }).on('changeDate', function (e) {
                    ngModelCtrl.$setViewValue(e.date);
                    scope.$apply();
                });
            }
        }
    }
})();