'use strict';


bookstoreApp.controller('BookController', ['$scope', '$http', '$routeParams', '$location',
    function ($scope, $http, $routeParams, $location) {


        $scope.bookId = $routeParams.bookId;

        $http.get('../rest/books').success(function (data) {
            $scope.books = data;
        }, function (error) {
            $scope.error = error;
        });

        if ($scope.bookId !== undefined) {
            $http.get('../rest/books/' + $scope.bookId).success(function (data) {

                $scope.book = data;
            });
        } else {
            $scope.editing = true;
        }

        $scope.edit = function () {
            $scope.editing = true;
        };

        $scope.newBook = function () {
            $scope.editing = true;
            delete $scope.book;

        };


        $scope.filterBook = function (book) {
            var reName = new RegExp($scope.namefilter, 'i');
            var reAuthor = new RegExp($scope.authorfilter, 'i');
            var reDescription = new RegExp($scope.descriptionfilter, 'i');
            var reISBN = new RegExp($scope.isbnfilter, 'i');
            return reName.test(book.name) && reAuthor.test(book.author) && reDescription.test(book.description) && reISBN.test(book.isbn);
        };

        $scope.saveBook = function () {
            if ($scope.book.id !== undefined) {
                $http.put('../rest/books/' + $scope.book.id, $scope.book).success(function (data) {
                    $scope.editing = false;
                });
            } else {
                $http.post('../rest/books/', $scope.book).success(function (data) {
                    $location.path('books');
                });
            }
        };

        $scope.cancel = function () {

            if ($scope.book.id !== undefined) {
                $http.get('../rest/books/' + $scope.book.id).success(function (data) {
                    $scope.book = data;
                });
            }
            $scope.editing = false;

        };

        $scope.delete = function () {
            if ($scope.book.id !== undefined) {
                $http.delete('../rest/books/' + $scope.book.id).success(function (data) {
                    $location.path('books');
                });
            }
        };

    }]);

