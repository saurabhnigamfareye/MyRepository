/**
 * Created by saurabh on 27/7/16.
 */

var app=angular.module('myApp',['ui.router']);
app.controller("myController",function($scope,$http,$location){
    $scope.reset=function()
    {
        $scope.flag=false;
        $scope.mssg="";
        $scope.mssgFlag=false;
    };

    //
    //String productName;
    //double cost;
    //String unitOfMeasurement;
    //final int openingBalance;
    //double dimension;
    //int currentBalance;

    $scope.submit=function()
    {   $scope.flag=false;
        var cost=$scope.cost;
        var productName=$scope.productName;
        var unitOfMeasurement=$scope.unitOfMeasurement;
        var openingBalance=$scope.openingBalance;
        var dimension=$scope.dimension;
        if(cost<=0 || productName=="" ||  unitOfMeasurement=="" ||  openingBalance<=0 || dimension<=0)
        {
            $scope.mssgFlag = true;
            $scope.mssg = "Wrong Inputs! Try Again!";

        }
        else {
            var url = "http://localhost:8080/create?productName=" + productName + "&cost=" + cost + "&unitOfMeasurement=" + unitOfMeasurement + "&openingBalance=" + openingBalance + "&dimension=" + dimension;
            $scope.mssgFlag = true;
            $http.get(url).success(function (response) {


                if (response.productName != undefined) {

                    $scope.mssg = "Successful";
                    $location.path("display");
                }
                else {
                    $scope.mssg = "Failed to enter"


                }
                console.log($scope.mssg + "pass")

            }).error(function (response) {
                $scope.mssg = response;

                console.log($scope.mssg + "fail")
            });
        }

    };
    $scope.delete=function()
    {
        $scope.flag=false;



        var url="http://localhost:8080/delete?inventoryId="+$scope.inventoryId;

        $http.get(url).success(function(response)
        {
            if(response.productName=="success")
            {
                $scope.mssg="Deleted Successfully!";
            }
            else
            {
                $scope.mssg="Inventory Does not Exist!";
            }

            console.log($scope.mssg+"pass")

        }).error(function(response)
        {
            $scope.mssg=  "Error! Cannot Delete!";

            console.log($scope.mssg+"fail")
        });

    };
    $scope.update=function()
    {
        $scope.flag=false;

        var cost=$scope.cost;
        var productName=$scope.productName;
        var unitOfMeasurement=$scope.unitOfMeasurement;
        var currentBalance=$scope.currentBalance;
        var dimension=$scope.dimension;
        var inventoryId=$scope.inventoryId;

        if(cost<=0 || productName=="" ||  unitOfMeasurement=="" ||  currentBalance<=0 || dimension<=0 || inventoryId<0)
        {
            $scope.mssgFlag = true;
            $scope.mssg = "Wrong Inputs! Try Again!";

        }
        else {
            var url = "http://localhost:8080/update?productName=" + productName + "&cost=" + cost + "&unitOfMeasurement=" + unitOfMeasurement + "&currentBalance=" + currentBalance + "&dimension=" + dimension + "&inventoryId=" + $scope.inventoryId;
            $http.get(url).success(function (response) {

                var result = response.productName;
                console.log(result);
                if (result === "successful") {
                    $scope.mssg = "Product with given id updated!";
                }
                else {
                    $scope.mssg = "Product with given id doesnot exist!";
                }
                console.log($scope.mssg + "pass")

            }).error(function (response) {
                $scope.mssg = response;

                console.log($scope.mssg + "fail")
            });
        }

    };
    $scope.display=function()
    {
        $scope.flag=true;
         var url="http://localhost:8080/display";

        $http.get(url).success(function(response)
        {
            $scope.records=response;


        }).error(function(response)
        {
            $scope.records=  response;


        });

    }


});
