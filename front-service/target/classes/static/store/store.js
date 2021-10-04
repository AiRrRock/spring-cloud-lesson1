angular.module('market-front').controller('storeController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/market/';
    let currentPageIndex = 1;

    $scope.loadProducts = function () {
        $http({
            url: contextPath + 'api/v1/products',
            method: 'GET'
        }).then(function (response) {
            console.log(response);
            $scope.products = response.data;
        });
    };


    $scope.loadProducts();
});