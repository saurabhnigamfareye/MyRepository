

app.config(['$stateProvider', '$urlRouterProvider',function($stateProvider,$urlProvider) {
    $stateProvider
        .state("update", {
            url: '/update',
            templateUrl : "Update.html",
            controller :"myController"
        })
        .state("delete", {
            url: '/delete',
            templateUrl : "Delete.html"
            ,controller :"myController"
        })
        .state("display",{
            url: '/display',
            templateUrl:"Display.html",
            controller :"displayController"
        })
        .state("register",{
            url: '/register',
            templateUrl:"Register.html"
            ,controller :"myController"
        })
        .state("purchaseDisplay",{
            url: '/Purchase_display',
            templateUrl:"purchase/purchaseDisplay.html"
            ,controller :"purchaseOrderDisplay"
        })
        .state("productionDisplay",{
            url: '/Production_display',
            templateUrl:"Production/productionDisplay.html"
            ,controller :"productionDisplay"
        })

        .state("production",{
            url: '/production',
            templateUrl:"Production/productionOrder.html"
            ,controller :"productionOrder"
        })

        .state("productionOrderHistory",{
            url: '/Production_H',
            templateUrl:"Production/productionOrderHistory.html"
            ,controller :"productionHistoryDisplay"
        })
        .state("purchaseOrderHistory",{
            url: '/Purchase_H',
            templateUrl:"purchase/History.html"
            ,controller :"purchaseHistoryDisplay"
        })


        .state("purchase",
            {
                url:'/purchase',


                views: {

                    // the main template will be placed here (relatively named)
                    '': { templateUrl:"purchase/purchaseOrder.html",
                        controller:'purchaseOrderController' },




                    'columnTwo@purchase': {
                        templateUrl:"Display.html",
                        controller :"displayController"
                    }
                }
            })
        .state("userDelete",{
            url: '/User_Delete',
            templateUrl:"User/deleteUser.html"
            ,controller :"userController"
        })
        .state("userRegister",{
            url: '/User_Register',
            templateUrl:"User/addUser.html"
            ,controller :"userController"
        })
        .state("userUpdate",{
            url: '/User_Update',
            templateUrl:"User/updateUser.html"
            ,controller :"userController"
        })
        .state("userDisplay",{
            url: '/Users_Display',
            templateUrl:"User/userDisplay.html"
            ,controller :"userDisplayController"
        })

    ;
    $urlProvider.otherwise('/register' )    ;
}]);

app.controller('userDisplayController',function($scope,$http,$location){
    $scope.userDisplay=function()
    {
        $scope.flag=true;
        var url="http://localhost:8080/userDisplay";

        $http.get(url).success(function(response)
        {
            $scope.records=response;


        }).error(function(response)
        {
            $scope.records=response;


        });


    }
    $scope.userDisplay();
});

app.controller('userController',function($scope,$http){
    $scope.reset=function()
    {
        $scope.flag=false;
        $scope.mssg="";
        $scope.mssgFlag=false;
    };

//@Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name="userid")
//    private Long userId;
//
//	@Column(name = "username")
//    private String userName;
//
//	@Column(name = "password")
//    private String password;
//
//	@Column(name = "email")
//    private String email;
//
//	@Column(name ="enabled")
//    private int enabled;
//
//	@OneToMany(cascade = CascadeType.ALL,mappedBy ="user" )
////			@JoinColumn(name = "userid")
//    List<UserRole> userRole;
//    public User(){


    $scope.submit=function()
    {   $scope.flag=false;
        var userName=$scope.userName;
        var password=$scope.password;
        var password2=$scope.password2;
        var email=$scope.email;
        var enabled=$scope.enabled;
        var userRole=$scope.userRole;
        var url="http://localhost:8080/userCreate";
        var data ={"userName":userName ,"password": password,"email": email,"enabled": enabled,"userRole": [{"role" : userRole}]    };
        $scope.mssgFlag=true;
        if(password!=password2)
        {
            $scope.mssg="Passwords doesnot match!"
            return;
        }
        $http({
            method: 'POST',
            url :url,
            data: data
        }).success(function(response)
        {


            if(response.userName!=undefined)
            {

                $scope.mssg="Successful";
                $location.path("/Users_Display");
            }
            else {
                $scope.mssg="Failed to enter"


            }
            console.log($scope.mssg+"pass")

        }).error(function(response)
        {
            $scope.mssg=  response;

            console.log($scope.mssg+"fail")
        });

    };
    $scope.delete=function()
    {
        $scope.mssgFlag=false;

        var url="http://localhost:8080/userDelete?userId="+$scope.userId;

        $http.get(url).success(function(response)
        {
            $scope.mssg="Deleted Successfully!";
            console.log($scope.mssg+"pass")

        }).error(function(response)
        {
            $scope.mssg=  "Error! Cannot Delete!";

            console.log($scope.mssg+"fail")
        });

    };
    $scope.update=function()
    {
        $scope.mssgFlag=false;
        var userId=$scope.userId;
        var userName=$scope.userName;
        var password=$scope.password;
        var password2=$scope.password2;
        var email=$scope.email;
        var enabled=$scope.enabled;
        var userRole=$scope.userRole;
        var url="http://localhost:8080/userUpdate";
        var data ={"userName":userName ,"password": password,"email": email,"enabled": enabled,"userRole":  [{"role" : userRole}],"userId":userId};
        if(password!=password2)
        {
            $scope.mssg="Passwords doesnot match!"
            return;
        }
        $http({
            method: 'POST',
            url :url,
            data: data
        }).success(function(response)
        {
            $scope.mssgFlag=true;
            var result =response.userName;
            console.log(result);
            if(result==="successfull")
            {
                $scope.mssg="User with given id updated!";
            }
            else {
                $scope.mssg="User with given id doesnot exist!";
            }
            console.log($scope.mssg+"pass")

        }).error(function(response)
        {
            $scope.mssgFlag=true;
            $scope.mssg=  response;

            console.log($scope.mssg+"fail")
        });

    };
});
app.controller('productionHistoryDisplay',function($scope,$http,$location){

    $scope.records=[];
    $scope.flag=false;

    $scope.productionHDisplay=function()
    {
        $scope.flag=true;
        var url="http://localhost:8080/productionHistoryDisplay?productionProductName="+$scope.productionProductName;
        console.log("i was called!")
        $http.get(url).success(function(response)
        {
            $scope.records=response;


        }).error(function(response)
        {
            $scope.records=response;


        });


    }


})

app.controller('purchaseHistoryDisplay',function($scope,$http,$location){

    $scope.records=[];
    $scope.flag=false;

    $scope.productionHDisplay=function()
    {
        $scope.flag=true;
        var url="http://localhost:8080/purchaseHistoryDisplay?productName="+$scope.productName;
        console.log("i was called!")
        $http.get(url).success(function(response)
        {
            $scope.records=response;


        }).error(function(response)
        {
            $scope.records=response;


        });


    }


})
app.controller('phd',function($scope,$http){



});

app.controller('productionDisplay',function($scope,$http){
    $scope.productionDisplay=function()
    {
        $scope.flag=true;
        var url="http://localhost:8080/productionDisplay";

        $http.get(url).success(function(response)
        {
            $scope.records=response;


        }).error(function(response)
        {
            $scope.records=response;


        });


    }
    $scope.productionDisplay();
});
//-----------------------------------------------------------------------------------------------------------------------------
app.controller('productionOrder',function($scope,$http){
    $scope.showFlag=false;
    $scope.mssgFlag1=false;
    $scope.mssgFlag2=false;

    var currentBalance=[];
    var productName=[];
    var url="http://localhost:8080/display";

    $http.get(url).success(function(response)
        {
            console.log(response);
            $scope.productArray=response;
            for(x=0;x<$scope.productArray.length;x++) {
                currentBalance[x] = $scope.productArray[x].currentBalance;
                productName[x] = $scope.productArray[x].productName;
            }
        })
        .error(function(response)
        {
            $scope.productArray= response;

        });

    var quantity=[];
    var productName=[];
    var count=0;
    $scope.table =[];

    $scope.reset=function()
    {
        quantity=[];
        productName=[];
        count=0;
        $scope.table =[];
        $scope.finalTotalCost=0;
        $scope.mssgFlag1=false;
        $scope.mssgFlag2=false;

        $scope.showFlag=false;
    };

    var flag=0;
    $scope.add=function()
    {
        if($scope.quantity==null||$scope.quantity==undefined||$scope.productionProductName==null||$scope.productionProductName==undefined)
        {
            $scope.ErrorMessage = "Wrong Inputs!Try again!"
            $scope.mssgFlag1=true;
        }else
        {

            if($scope.productionProductName==$scope.productNameSelected)
            {

                $scope.mssgFlag1=true;
                $scope.ErrorMessage = "Cannot add the same inventory which is being produced  ";
            }else {
                $scope.ErrorMessage = "";
                $scope.mssgFlag1=false;


                for (var y = 0; y < count; y++) {
                    if (productName[y] == ($scope.productNameSelected)) {
                        flag = 1;
                        break;
                    }

                }

                if (flag == 1) {
                    $scope.ErrorMessage = "Cannot add, already in the list";
                    $scope.mssgFlag1=true;


                }
                else {
                    $scope.ErrorMessage = "";
                    $scope.mssgFlag1=false;

                    $scope.showFlag=true;

                    var p=0;
                    for(p=0;p<productName.length;p++)
                    {
                        if($scope.productNameSelected==productName[p])
                        {
                            break;
                        }

                    }
                    if($scope.quantity>currentBalance[p])
                    {
                        $scope.ErrorMessage = "Cannot add, item insufficient!";
                        $scope.mssgFlag1=true;
                    }
                    else {
                        quantity[count] = $scope.quantity;
                        productName[count] = $scope.productNameSelected;
                        count++;
                        for (x = 0; x < count; x++) {
                            $scope.table[x] = {"quantity": quantity[x], "productName": productName[x]};
                        }
                    }
                }
            }}

    };
    $scope.submit=function() {

        if ($scope.quantity == null || $scope.quantity == undefined || $scope.productionProductName == null || $scope.productionProductName == undefined) {
            $scope.ErrorMessage = "Wrong Inputs!Try again!"
            $scope.mssgFlag = true;
        }
        else
        {
            if (productName.length == 0) {

                $scope.mssgFlag2 = true;

                $scope.ErrorMessage2 = "No inventory in the raw materials list , please add some then press Submit!"
            }
            else
            {
                $scope.ErrorMessage2 = " "

                $scope.mssgFlag2 = false;


                var table = [];
                for (x = 0; x < count; x++) {
                    table[x] = {"quantity": quantity[x], "productName": productName[x]};
                }
                var productionOrder = {
                    'productionProductName': $scope.productionProductName,
                    'totalManufacturedItem': $scope.totalManufacturedItem,
                    'rawMaterials': table
                }
                console.log(productionOrder);
                var url = "http://localhost:8080/production";
                $http(
                    {
                        url: url,
                        method: 'POST',
                        data: productionOrder,

                    }
                ).then(
                    function success(response) {
                        alert("Successful")

                    },
                    function error(response) {
                        alert("Error")
                    }
                );

            }
        }
    }
});
//-----------------------------------------------------------------------------------------------------------------------------
app.controller('displayController',function($scope){
    $scope.display();
});

//-----------------------------------------------------------------------------------------------------------------------------
app.controller('purchaseOrderDisplay',function($scope,$http){

    $scope.purchaseDisplay=function()
    {
        $scope.flag=true;
        var url="http://localhost:8080/purchaseDisplay";

        $http.get(url).success(function(response)
        {
            $scope.records=response;


        }).error(function(response)
        {
            $scope.records=response;


        });


    }
    $scope.purchaseDisplay();

});

//-----------------------------------------------------------------------------------------------------------------------------
app.controller('purchaseOrderController',function($scope,$http,$location)
{
    $scope.showFlag=false;
    $scope.mssgFlag=false;
    $scope.mssgFlag2=false;
    var url="http://localhost:8080/display";


    $http.get(url).success(function(response)
    {
        console.log(response);

        $scope.productArray=response;

    }).error(function(response)
    {
        $scope.productArray= response;


    });
    var invenrotyId=[];
    var quantity=[];
    var cost=[];
    var productName=[];
    var count=0;
    var finalTotalCost=0;
    var totalCost=[];
    $scope.table =[];

    $scope.netCost=0;
    $scope.finalTotalCost=0;
    $scope.reset=function()
    {
        quantity=[];
        cost=[];
        //invenrotyId=[];
        productName=[];
        totalCost=[];
        count=0;
        finalTotalCost=0;
        $scope.table =[];
        $scope.netCost=0;
        $scope.finalTotalCost=0;
        $scope.showFlag=false;
        $scope.mssgFlag=false;
        $scope.mssgFlag2=false;

    };


    $scope.add=function()
    {
        if($scope.quantity==null||$scope.quantity==undefined||$scope.cost==null||$scope.cost==undefined||$scope.productNameSelected==null||$scope.productNameSelected==undefined)
        {
            $scope.ErrorMessage = "Wrong Inputs!Try again!"
            $scope.mssgFlag=true;
        }else
        {


            var flag=0;
            for(var y=0;y<count;y++)
            {
                if(productName[y]==($scope.productNameSelected))
                {
                    flag=1;
                    break;
                }
            }
            if(flag==1)
            {
                $scope.ErrorMessage="Cannot add, already in the list";
                $scope.mssgFlag=true;

                flag=0;
            }
            else{
                $scope.ErrorMessage=" ";
                $scope.mssgFlag=false;
                $scope.showFlag=true;
                if($scope.quantity<0 ||$scope.cost <0)
                {
                    $scope.ErrorMessage = "Wrong Inputs!Try again!"
                }
                else
                {
                    $scope.ErrorMessage=" ";
                    quantity[count]=$scope.quantity;
                    cost[count]=$scope.cost;
                    totalCost[count]=$scope.cost*$scope.quantity;
                    productName[count]=$scope.productNameSelected;


                    finalTotalCost=finalTotalCost+totalCost[count];
                    count++;

                    $scope.finalTotalCost=finalTotalCost;
                    $scope.netCost=finalTotalCost+(finalTotalCost*($scope.tax/100));;
                    for(x=0;x<count;x++)
                    {
                        $scope.table[x]={"quantity":quantity[x],"cost":cost[x],"totalCost":totalCost[x],"productName":productName[x]};
                    }
                }}

        }

    };



    $scope.submit=function() {

        if($scope.quantity==null||$scope.quantity==undefined||$scope.cost==null||$scope.cost==undefined||$scope.productNameSelected==null||$scope.productNameSelected==undefined)
        {
            $scope.ErrorMessage = "Wrong Inputs!Try again!"
            $scope.mssgFlag=true;
        }else
        {

            if (productName.length == 0) {
                $scope.ErrorMessage2 = "No inventory in the purchase order , please add some then press Submit!"
                $scope.mssgFlag2=true;
            }
            else {
                $scope.ErrorMessage2 = " "
                $scope.mssgFlag2=false;

                var netCost = finalTotalCost + (finalTotalCost * ($scope.tax / 100));
                //  var inventoryItems = [{'quantity': quantity, 'cost': cost, 'productName': productName}];
                var table=[];
                for(x=0;x<count;x++)
                {
                    table[x]={"quantity":quantity[x],"cost":cost[x],"productName":productName[x]};
                }
                var purchaseOrder = {
                    'nameOfVendor': $scope.nameOfVendor,
                    'tax': $scope.tax,
                    'totalCost': finalTotalCost,
                    'netCost': netCost,
                    'inventoryItems': table
                }
                console.log(purchaseOrder);
                var url = "http://localhost:8080/purchase";
                $http(
                    {
                        url: url,
                        method: 'POST',
                        data: purchaseOrder,

                    }
                ).then(
                    function success(response) {
                        alert("Purchase Successful");
                        $location.path("/Purchase_display");

                    },
                    function error(response) {
                        alert("Error")
                        $location.path("purchaseDisplay");
                    }
                );

            }

        }}

});