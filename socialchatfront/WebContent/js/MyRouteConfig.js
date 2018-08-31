/**
 * myApp to navigate between all pages (SPA)
 */
var myApp = angular.module("myApp", [ 'ngRoute', , 'ngCookies' ]);
myApp.config(function($routeProvider) {
	console.log('I am in routeProvider');
	$routeProvider.when("/", {
		templateUrl : "home.html"
	}).when("/Login", {
		templateUrl : "C_User/Login.html"
	}).when("/UpdateProfilePicture", {
		templateUrl : "C_User/UpdateProfile.html"
	}).when("/UpdateProfileDetails", {
		templateUrl : "C_User/UpdateProfileDetails.html"
	}).when("/ViewProfile", {
		templateUrl : "C_User/ViewProfile.html"
	}).when("/userRequests", {
		templateUrl : "C_User/userRequests.html"
	}).when("/suggestedFriends", {
		templateUrl : "C_Friend/suggestedFriends.html"
	}).when("/Register", {
		templateUrl : "C_User/Register.html"
	}).when("/ContactUs", {
		templateUrl : "template/ContactUs.html"
	}).when("/AboutUs", {
		templateUrl : "template/AboutUs.html"
	}).when("/Blog", {
		templateUrl : "C_Blog/Blog.html"
	}).when("/ViewBlog", {
		templateUrl : "C_Blog/viewBlog.html"
	}).when("/SingleBlog", {
		templateUrl : "C_Blog/SingleBlog.html"
	}).when("/updateBlog", {
		templateUrl : "C_Blog/updateBlog.html"
	}).when("/createForum", {
		templateUrl : "C_Forum/Forum.html"
	}).when("/updateForum", {
		templateUrl : "C_Forum/updateForum.html"
	}).when("/ViewForums", {
		templateUrl : "C_Forum/ViewForums.html"
	}).when("/SingleForum", {
		templateUrl : "C_Forum/SingleForum.html"
	}).when("/Chat", {
		templateUrl : "C_Chat/chat.html"
	}).when("/Friends", {
		templateUrl : "C_Friend/myAccFrnds.html"
	}).when("/frndReqRcvd", {
		templateUrl : "C_Friend/frndReqRcvd.html"
	}).when("/Job", {
		templateUrl : "C_Job/addJob.html"
	}).when("/ViewJobs", {
		templateUrl : "C_Job/ViewJobs.html"
	}).when("/SingleJob", {
		templateUrl : "C_Job/SingleJob.html"
	});
});

myApp.run(function($rootScope, $cookieStore) {
	console.log('I am in run function');
	console.log($rootScope.currentUser);

	if ($rootScope.currentUser == undefined) {
		$rootScope.currentUser = $cookieStore.get('userDetails');
	} else {
		console.log($rootScope.currentUser.userName);
		console.log($rootScope.currentUser.role);
	}
});