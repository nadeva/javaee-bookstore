'use strict';


bookstoreApp.controller('CustomerController', ['$scope', '$http', '$routeParams', '$location',
    function ($scope, $http, $routeParams, $location) {


        $http.get('../rest/books').success(function (data) {
            $scope.books = data;
        }, function (error) {
            $scope.error = error;
        });

        $scope.orderTotal = 0;

        $scope.addBook = function (book) {

            if ($scope.orderLines === undefined || $scope.orderLines.length === 0) {
                $scope.orderLines = [];
                $scope.addOrderLine(book);
            } else {
                var found = false;
                $scope.orderLines.forEach(function (orderLine) {
                    if (orderLine.book === book) {
                        orderLine.quantity = orderLine.quantity + 1;
                        found = true;
                    }
                });
                if (!found) {
                    $scope.addOrderLine(book);
                }
            }
            $scope.orderTotal = $scope.orderTotal + book.price;

        };

        $scope.addOrderLine = function (book) {
            var orderLine = new Object();
            orderLine.book = book;
            orderLine.quantity = 1;
            $scope.orderLines.push(orderLine);
        };

        $scope.sendOrder = function () {
            var order = new Object();
            order.name = $scope.orderName;
            order.address = $scope.orderAddress;
            order.orderLines = $scope.orderLines;
            order.totalOrder = $scope.orderTotal;

            $http.post('../rest/orders', order).success(function (data) {
                $scope.orderName = '';
                $scope.orderAddress = '';
                $scope.orderLines = [];
                $scope.orderTotal = 0;
            });
        };
    }]);

