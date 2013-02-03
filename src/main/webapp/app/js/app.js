'use strict';


// Declare app level module which depends on filters, services and directives
angular.module('CrmDemo', [ 'CrmDemo.filters', 'CrmDemo.services', 'CrmDemo.directives' ])
        .config([ '$routeProvider', function($routeProvider) {
            $routeProvider.when('/list', {
                templateUrl : 'app/partials/customer-list.html',
                controller : CustomerListCtrl
            });
            $routeProvider.when('/edit/:customerId', {
                templateUrl : 'app/partials/customer-detail.html',
                controller : CustomerDetailCtrl
            });
            $routeProvider.when('/new', {
                templateUrl : 'app/partials/customer-detail.html',
                controller : CustomerDetailCtrl
            });
            $routeProvider.otherwise({
                redirectTo : '/list'
            });
        } ]);
