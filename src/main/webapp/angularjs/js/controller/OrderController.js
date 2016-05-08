'use strict';


bookstoreApp.controller('OrderController', ['$scope', '$http', '$routeParams', '$location',
    function ($scope, $http, $routeParams, $location) {


        $scope.orderId = $routeParams.orderId;

        $http.get('../rest/orders').success(function (data) {
            $scope.orders = data;
        }, function (error) {
            $scope.error = error;

        });

        if ($scope.orderId !== undefined) {
            $http.get('../rest/orders/' + $scope.orderId).success(function (data) {
                $scope.order = data;
            });
        }

    }]);

