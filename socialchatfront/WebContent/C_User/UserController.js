myApp.controller("UserController", function($scope, $http, $location,
		$rootScope, $cookieStore) {
	$scope.user = {
		loginName : '',
		password : '',
		role : '',
		userName : '',
		emailId : '',
		mobileNo : '',
		address : '',
		isOnline : ''
	};
	
	$scope.userData;
	$scope.userList;
	$scope.userProfile = {
		loginName : '',
		image : ''
	};
	$rootScope.login = function() {
		console.log("Logging Function");

		$http.post("http://localhost:8078/socialchatmiddle/login", $scope.user)
				.then(function(response) {
					console.log(response.status);
					$scope.user = response.data;
					$rootScope.currentUser = response.data;
					$cookieStore.put('userDetails', response.data);
					console.log($rootScope.currentUser.role);
					if ($rootScope.currentUser.role == "ROLE_ADMIN") {
						console.log('AdminPage');
					} else {
						console.log('UserPage');
					}
					$location.path("/");
				});
	};

	$scope.register = function() {
		console.log('Entered into the Register User method');
		$http.post("http://localhost:8078/socialchatmiddle/register",
				$scope.user).then(function(response) {
			console.log('Status text:' + response.statusText);
			alert('Registered successfully..!!');
			$location.path("/");
		});

	};
	$scope.update = function(loginName) {
		console.log('Entered into the Update  User method');
		$http.post("http://localhost:8078/socialchatmiddle/updateUser/"+loginName,
				$scope.user).then(function(response) {
			console.log('Status text:' + response.statusText);
			alert('updated details successfully..!!');
			$location.path("/ViewProfile");
		});

	};

	
	 /* $scope.upload = function() { console.log('Entered into the upload method');
	  $http.post("http://localhost:8078/socialchatmiddle/doUpload",$scope.userProfile).then(function(response) {
	  console.log('Status text:' + response.statusText); alert('Uploaded successfully..!!'); $location.path("/"); }); };*/
	 
	$scope.fetchUserDetails = function() {
		console.log("Inside fetch user Details function "
				+ $rootScope.currentUser.loginName);
		$http.get(
				"http://localhost:8078/socialchatmiddle/getUser/"
						+ $rootScope.currentUser.loginName).then(
				function(response) {
					console.log("fetched User");
					$scope.userData = response.data;
					$scope.user = response.data;
					$rootScope.currentUser = response.data;
					$cookieStore.put('userDetails', response.data);
				});
	};
	
/*	$rootScope.logout = function() {
		console.log('Entered into the logout function');
		$http.post("http://localhost:8078/socialchatmiddle/logout",
				$rootScope.currentUser).then(function(response) {
			console.log(response.data);
			delete $rootScope.currentUser;
			$cookieStore.remove('userDetails');
			$location.path("/");
		});
	}*/

	$scope.logout = function()
	{
		console.log("Logging Out");
		alert("Logged Out Successfully")
		$location.path("/");
		$rootScope.currentUser = undefined;
		$location.reload();
}
});