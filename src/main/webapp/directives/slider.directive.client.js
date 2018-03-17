angular.module('BookYourTrip')
    .directive('slider', slider);

slider.$inject = ['$document'];

function slider($document) {
    var directive = {
        restrict: 'AC', //Apply the directive through Class or Attribute
        scope: {
            images: '='
        },
        template: '<ul class="slides">'
        + '<li data-ng-repeat="img in images">'
        + '<img data-ng-src="{{img}}">'
        // + '<div class="caption {{img.align}}-align">'
        // + '<h3>{{img.caption}}</h3>'
        // + '<h5 class="light grey-text text-lighten-3">{{img.slogan}}</h5>'
        // + '</div>'
        + '</li>'
        + '</ul>',
        link: linkFunc
    };

    return directive;

    function linkFunc(scope, elem, attr) {
        $document.ready(function(){
            elem.slider({
                full_width: false,
                indicators: true,
                transition: 500,
                interval: 1000
            });
        });
    }

}