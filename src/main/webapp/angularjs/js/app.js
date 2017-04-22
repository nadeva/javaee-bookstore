'use strict';

var bookstoreApp = angular.module('bookStoreApp', ['ngRoute', 'ngResource']);

bookstoreApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/home', {templateUrl: 'views/home.html', controller: 'HomeController'});
    $routeProvider.when('/books', {templateUrl: 'views/books.html', controller: 'BookController'});
    $routeProvider.when('/books/:bookId', {templateUrl: 'views/book.html', controller: 'BookController'});
    $routeProvider.when('/newbook', {templateUrl: 'views/book.html', controller: 'BookController'});
    $routeProvider.when('/customer', {templateUrl: 'views/customer.html', controller: 'CustomerController'});
    $routeProvider.when('/orders', {templateUrl: 'views/orders.html', controller: 'OrderController'});
    $routeProvider.when('/orders/:orderId', {templateUrl: 'views/order.html', controller: 'OrderController'});
    $routeProvider.otherwise({redirectTo: '/home'});
}]);
