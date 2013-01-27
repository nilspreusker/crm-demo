'use strict';

/* Controllers */

function CustomerListCtrl($scope, $location, Customer) {

  $scope.customers = Customer.query();

}

function CustomerDetailCtrl($scope, $location, $routeParams, Customer) {

  if ($location.path() === "/new") {
    $scope.customer = {
      createDate : new Date(),
      sex : 'unknown'
    };
  } else {
    $scope.customer = Customer.get({
      id : $routeParams.customerId
    });
  }

  $scope.save = function() {
    if (!!$scope.customer.id) {
      Customer.update($scope.customer, function(customer) {
        $location.path('/list');
      });
    } else {
      Customer.save($scope.customer, function(customer) {
        $location.path('/list');
      });
    }
  }

}
