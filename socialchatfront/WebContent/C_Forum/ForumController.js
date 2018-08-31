myApp.controller("ForumController", function($scope, $http, $location,$rootScope, $window) {
	$scope.forum = {
		"forumId":0,	
		"forumName" : '',
		"forumContent" : '',
		"createDate" : '',
		"loginName" : '',
		"status" : ''
	}
	$rootScope.forum1 = {
			"forumId":0,
			"forumName" : '',
			"forumContent" : '',
			"createDate" : '',
			"loginName" : '',
			"status" : ''
		}
	$scope.forumData;
	$scope.forumComment={"commentId":0,"forumId":'',"commentDate":'',"commentText":'',"loginName":''}
	$scope.forumComments;
	$scope.insertForum = function() {
		console.log('Entered into the insertForum method');
		$scope.forum.loginName=$rootScope.currentUser.loginName;
		$http.post("http://localhost:8078/socialchatmiddle/addForum",
						$scope.forum).then(fetchAllForums(), function(response) {
						$scope.forumData=response.Data;
						console.log('Status text:' + response.statusText);
						 $window.alert("Data inserted successfully");
				});
	};
	function fetchAllForums() {
		console.log('Into Fetch All Forums');
		$http.get("http://localhost:8078/socialchatmiddle/listForums").then(
				function(response) {
					console.log('Status text:' + response.statusText);
					$scope.forumData = response.data;
					console.log(response.data);
				});
	};
	$scope.editForum = function(forumId) {
		console.log('In editForum method');
		$http.get('http://localhost:8078/socialchatmiddle/getForum/' + forumId)
				.then( function(response) {
					console.log('In edit forum');
					console.log(response.data);
					$scope.forum = response.data;
					console.log('Status Text' + response.statusText);
					$location.path('/updateForum');
				});
	};

	$scope.updateForum = function(forumId){
		console.log('Entered into the updateForum method');
		console.log(forumId);
		$http.put('http://localhost:8078/socialchatmiddle/updateForum/'+ forumId, $scope.forum)
		.then(fetchAllForums(), function(response){
			console.log('updated forum'+ forumId+ ' successfully');
			console.log(forumId +" updated successfully");
			 $location.path('/updateForum');
			$window.alert('Forum updated successfully...');
			
		});
		
	};
	$scope.deleteForum = function(forumId){
		console.log('Entered into the deleteForum method');
		$http.delete('http://localhost:8078/socialchatmiddle/deleteForum/'+forumId)
		.then(fetchAllForums(), function(response){
			console.log('Forum deleted '+ forumId);
			console.log('Response Status ' + response.statusText);
			fetchAllForums();
			$window.alert('Forum deleted successfully..');
		});
	};

	$scope.approveForum = function(forumId){
		console.log('Entered into the approveForum method');
		console.log(forumId);
		$http.put('http://localhost:8078/socialchatmiddle/approveForum/'+ forumId)
		.then(fetchAllForums(), function(response){
			console.log('Approved forum'+ forumId+ ' successfully');
			console.log(forumId +" updated successfully");
			$window.alert('Forum approved successfully...');
			fetchAllForums();
			 $location.path("/updateForum"); 
		});
		
	};
	$scope.rejectForum = function(forumId){
		console.log('Entered into the rejectForum method');
		console.log(forumId);
		$http.put('http://localhost:8078/socialchatmiddle/rejectForum/'+ forumId)
		.then(fetchAllForums(), function(response){
			console.log('Rejected forum'+ forumId+ ' successfully');
			console.log(forumId +" rejected successfully");
			$window.alert('Forum rejected successfully...');
			fetchAllForums();
			 $location.path("/updateForum"); 
		});
		
	};
	$rootScope.viewForum = function(forumId) {
		console.log('Entered into the getForum method');
		$http.get('http://localhost:8078/socialchatmiddle/getForum/' + forumId)
				.then( function(response) {
					console.log('In get forum');
					console.log(response.data);
					$scope.forum=response.data;
					$rootScope.singleForumData=response.data;
					console.log($rootScope.singleForumData.forumId);
					console.log('Status Text' + response.statusText);
					$location.path('/SingleForum');					
				});
	};
	$scope.fetchAllForumComments=function(forumId) {
		console.log('Into Fetch All Forum Commments');
		$http.get("http://localhost:8078/socialchatmiddle/listForumComments/"+forumId).then(
				function(response) {
					console.log('Status text:' + response.statusText);
					$scope.forumComments = response.data;
					console.log(response.data);
				});
	};
	$scope.addForumComment = function(forumId) {
		console.log('Entered into the addForumComment method');
		$scope.forumComment.loginName=$rootScope.currentUser.loginName;
		$scope.forumComment.forumId=forumId;
		$http.post("http://localhost:8078/socialchatmiddle/addForumComment",
						$scope.forumComment).then(function(response) {
					console.log('Status text:' + response.statusText);
					 $window.alert("Commented successfully");
					 $window.location.reload();
					 $location.path("/SingleForum"); 
				});
	};

	fetchAllForums();
});