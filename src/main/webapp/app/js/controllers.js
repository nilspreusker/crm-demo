'use strict';

/* Controllers */

function CustomerListCtrl($scope, $http, $location, Customer) {

    if (angular.isUndefined($scope.credentials) || angular.isUndefined($scope.credentials.username) || angular.isUndefined($scope.credentials.password)) {
        $location.path('/login');
    }

    $scope.$on('event:loginRequired', function () {

        console.log("catching login required event...");
        $location.path('/login');

    });

    $scope.customers = Customer.query();
    $scope.filteredResults = false;

    $scope.search = function () {
        $scope.customers = Customer.query(
            {
                searchString: $scope.searchString
            },
            function () {
                $scope.filteredResults = true;
            }
        );
    }

    $scope.clearSearch = function () {
        $scope.searchString = null;
        $scope.filteredResults = false;
        $scope.customers = Customer.query();
    }

    $scope.deleteCustomer = function (customerId) {
        Customer.delete(
            {
                id: customerId
            },
            function () {
                if (!!$scope.searchString && !!$scope.filteredResults) {
                    $scope.search();
                } else {
                    $scope.customers = Customer.query();
                }
            });
    }

}

function CustomerDetailCtrl($scope, $location, $routeParams, Customer) {

    if ($location.path() === "/new") {
        $scope.customer = {
            createDate: new Date(),
            sex: 'unknown'
        };
    } else {
        $scope.customer = Customer.get({
            id: $routeParams.customerId
        });
    }

    $scope.save = function () {
        if (!!$scope.customer.id) {
            Customer.update($scope.customer, function (customer) {
                $location.path('/list');
            });
        } else {
            Customer.save($scope.customer, function (customer) {
                $location.path('/list');
            });
        }
    }

}

function LoginCtrl($scope, $rootScope, $http, $location) {

    // reset credentials and Authentication header
    $rootScope.credentials = {};
    $http.defaults.headers.common['Authorization'] = undefined;

    $scope.login = function () {
        // TODO: verify user credentials against server
        $http.defaults.headers.common['Authorization'] = 'Basic ' + Base64.encode($scope.credentials.username + ':' + $scope.credentials.password);
        $location.path('/list');
    }

}

// j_spring_security_logout