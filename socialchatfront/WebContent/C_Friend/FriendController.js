myApp.controller("FriendController",function($scope,$http,$rootScope,$location)
{
	$scope.user={'loginName':'','password':'','userName':'','emailId':'','mobileNo':'','role':'','address':''};
	
	$scope.friend={'friendId':0,'loginName':'','friendloginname':'','status':''};
	
	$scope.showFriendList;
	
	$scope.showSuggestedFriendList;
	
	$scope.showPendingFriendRequestList;
	
	
	$scope.unfriend=function(friend)
	{
		$http.get('http://localhost:8078/socialchatmiddle/deleteFriendRequest/'+friend.friendId)
		.then(function(response)
		{
			console.log("Friend Deleted");
		});
	}
	
	$scope.acceptfriend=function(friend)
	{
		$http.get('http://localhost:8078/socialchatmiddle/acceptFriendRequest/'+friend.friendId)
		.then(function(response)
		{
			console.log("Accepted Friend Request");
		});
	}
	
	$scope.sendFriendRequest=function(friend)
	{
		$scope.friend.loginName=$rootScope.currentUser.loginName;
		$scope.friend.friendloginname=friend.loginName;
		$scope.friend.status='NA';
		$http.post('http://localhost:8078/socialchatmiddle/sendFriendRequest/',$scope.friend)
		.then(function(response)
		{
			console.log("Friend Request Sent");
		});
	}
	
	function showFriendList()
	{
		$http.get('http://localhost:8078/socialchatmiddle/showAllFriends/'+$rootScope.currentUser.loginName)
		.then(function(response)
		{
			$scope.showFriendList=response.data;
	
		});
	}
	
	function showSuggestedFriendLists()
	{
		$http.get('http://localhost:8078/socialchatmiddle/showSuggestedFriendList/'+$rootScope.currentUser.loginName)
		.then(function(response)
		{
			$scope.showSuggestedFriendList=response.data;
	
		});
	}
	
	function showPendingFriendRequestList()
	{
		$http.get('http://localhost:8078/socialchatmiddle/showPendingFriendRequest/'+$rootScope.currentUser.loginName)
		.then(function(response)
		{
			$scope.showPendingFriendRequestList=response.data;
	
		});
	};
	showFriendList();
	showSuggestedFriendLists();
	showPendingFriendRequestList();
	});