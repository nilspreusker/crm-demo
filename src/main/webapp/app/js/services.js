'use strict';

/* Services */

angular.module('CrmDemo.services', [ 'ngResource' ]).value('Debouncer', {
  /**
   * Debounce a function call, making it callable an arbitrary number of times
   * before it is actually executed once.
   * 
   * @param {function()}
   *          func The function to debounce.
   * @param {number}
   *          wait The debounce timeout.
   * @return {function()} A function that can be called an arbitrary number of
   *         times within the given time.
   */
  debounce : function(func, wait) {
    var timer;
    return function() {
      var context = this, args = arguments;
      clearTimeout(timer);
      timer = setTimeout(function() {
        timer = null;
        func.apply(context, args);
      }, wait);
    };
  }
}).value('version', '0.1').factory(
    'Customer',
    function($resource) {

      var Customer = $resource(
          'http://localhost\\:8080/crm-demo/rest/customer/:id', {
            id : '@id'
          }, {
            update : {
              method : 'PUT'
            }
          });

      return Customer;
    });
