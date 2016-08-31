
//document.getElementById("UploadFile").style.visibility = 'hidden';

var app = angular.module('myApp', []);

app.controller('myController',function($scope,$http){

    $scope.uploadFile = function() {
        console.log("Hiiiiiii")
        $http.get("read.do").then(function (response) {
            console.log(response)
        });

    }


});



/*

app.controller('myController', function ($scope, $http) {

    $scope.uploadFile = function () {
        document.getElementById("UploadFile").style.visibility = 'visible';

        var fd = new FormData();
        fd.append("file",document.getElementById("file"));
        $http.post("/read.do", fd).success(function (result, status, header, config) {
            if(result.equals("success"){
                alert("Uploaded Successfully");
            }else
            {
                alert("Failed. Try again.");
            }
        });
    }
});*/
