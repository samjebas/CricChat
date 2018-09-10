myApp.controller("JobController", function($scope,$route, $http, $location,$rootScope, $window) {
	$scope.job = {
			"jobId":0,
		"jobDesignation" : '',
		"company" : '',
		"salary" : 0,
		"location" : '',
		"jobDescription" : '',
		"lastDateToApply" : ''
	}
	$rootScope.job1 = {
			"jobId":0,
			"jobDesignation" : '',
			"company" : '',
			"salary" : 0,
			"location" : '',
			"jobDescription" : '',
			"lastDateToApply" : ''
		}
	$scope.applyJob={'applicationId':'0','company':'','jobDesignation':'','appliedDate':'','jobId':'','loginName':''}
	$scope.jobData;
	$rootScope.listApplyJob
	$rootScope.singleJobData;
	$rootScope.message="Job added successfully";

	$scope.insertJob = function() {
		console.log('Entered into the insertJob method');
		$http.post("http://localhost:8078/socialchatmiddle/addJob",
						$scope.job).then(fetchAllJobs(), function(response) {
					console.log('Status text:' + response.statusText);
					if($scope.job!=null){
					 $window.alert("Data inserted successfully");
					 $window.location.reload();
					}
					 $location.path("/Job"); 
				});
	};
	function fetchAllJobs() {
		console.log('Into Fetch All Jobs');
		$http.get("http://localhost:8078/socialchatmiddle/listJobs").then(
				function(response) {
					console.log('Status text:' + response.statusText);
					$scope.jobData = response.data;
					console.log(response.data);
				});
	};
	$rootScope.viewJob = function(jobId) {
		console.log('Entered into the getJob method');
		$http.get('http://localhost:8078/socialchatmiddle/getJob/' + jobId)
				.then( function(response) {
					console.log('In get job');
					console.log(response.data);
					$scope.job=response.data;
					$rootScope.singleJobData=response.data;
					console.log($rootScope.singleJobData.jobId);
					console.log('Status Text' + response.statusText);
					$location.path('/SingleJob');					
				});
	};
	/*
	 * $rootScope.applyJobClicked=function(job) {
	 * $http.get('http://localhost:8078/socialchatmiddle/getJob/'+job.jobId)
	 * .then(function(response) { $rootScope.jobInfo=job;
	 * $rootScope.applyJobData=response.data; $location.path('/applyJobs'); });
	 *  }
	 */
	$scope.applyJobs=function()
	{
		console.log('Applying for jobs');
		$scope.applyJob.loginName=$rootScope.currentUser.loginName;

		$scope.applyJob.jobId=$rootScope.singleJobData.jobId;
		$scope.applyJob.company=$rootScope.singleJobData.company;
		$scope.applyJob.jobDesignation=$rootScope.singleJobData.jobDesignation;
		
		$http.post('http://localhost:8078/socialchatmiddle/applyJob',$scope.applyJob).then(function(response)
				{
			 	
				console.log('Successful');
				$location.path('/Job');
			});
	}
	function listApplyJob()
	{
		console.log("Applied jobs displaying");
		$http.get('http://localhost:8078/socialchatmiddle/listAppliedJobs')
		.then(function(response)
				{
					$rootScope.listApplyJob = response.data;
					console.log(response.data);
				});
	}
	$scope.editJob = function(jobId) {
		console.log('Entered into the editJob method');
		$http.get('http://localhost:8078/socialchatmiddle/getJob/' + jobId)
				.then( function(response) {
					console.log('In edit job');
					console.log(response.data);
					$scope.job = response.data;
					console.log('Status Text' + response.statusText);
					$location.path('/updateJob');					
				});
	};

	$scope.updateJob = function(jobId){
		console.log('Entered into the updateJob method');
		console.log(jobId);
		$http.put('http://localhost:8078/socialchatmiddle/updateJob/'+ jobId, $scope.job)
		.then(fetchAllJobs(), function(response){
			console.log('updated job'+ jobId+ ' successfully');
			console.log(jobId +" updated successfully");
			$window.alert('Job updated successfully...');
			$location.path("/updateJob"); 
		});
		
	};
	$scope.approveJob = function(jobId){
		console.log('Entered into the approveJob method');
		console.log(jobId);
		$http.put('http://localhost:8078/socialchatmiddle/approveJob/'+ jobId)
		.then(fetchAllJobs(), function(response){
			console.log('Approved job'+ jobId+ ' successfully');
			console.log(jobId +" updated successfully");
			$window.alert('Job approved successfully...');
			fetchAllJobs();
			 $location.path("/updateJob"); 
		});
		
	};
	$scope.rejectJob = function(jobId){
		console.log('Entered into the rejectJob method');
		console.log(jobId);
		$http.put('http://localhost:8078/socialchatmiddle/rejectJob/'+ jobId)
		.then(fetchAllJobs(), function(response){
			console.log('Rejected job'+ jobId+ ' successfully');
			console.log(jobId +" rejected successfully");
			$window.alert('Job rejected successfully...');
			fetchAllJobs();
			 $location.path("/updateJob"); 
		});
		
	};
	$scope.deleteJob = function(jobId){
		console.log('Entered into the deleteJob method');
		$http.delete('http://localhost:8078/socialchatmiddle/deleteJob/'+jobId)
		.then(fetchAllJobs(), function(response){
			console.log('Job deleted '+ jobId);
			console.log('Response Status ' + response.statusText);
			fetchAllJobs();
			alert('Job deleted successfully..');
			$location.path("/Job");
		});
	};
	$scope.incrementLike = function(jobId) {
		console.log('Into like increment');
		$http.post(
				'http://localhost:8078/socialchatmiddle/incrementLikes/'
						+ jobId, $scope.job).then(fetchAllJobs(),
				function(response) {
					console.log('Incremented likes');
					fetchAllJobs();
					$location.path('/viewJob');
					
				});
	}
	
	$scope.fetchAllJobComments=function(jobId) {
		console.log('Into Fetch All Job Commments');
		$http.get("http://localhost:8078/socialchatmiddle/"+jobId).then(
				function(response) {
					console.log('Status text:' + response.statusText);
					$scope.jobComments = response.data;
					console.log(response.data);
				});
	};
	$scope.addJobComment = function(jobId) {
		console.log('Entered into the addJobComment method');
		$scope.jobComment.userName=$rootScope.currentUser.loginName;
		$scope.jobComment.jobId=jobId;
		$http.post("http://localhost:8078/socialchatmiddle/addJobComment",
						$scope.jobComment).then(function(response) {
					console.log('Status text:' + response.statusText);
					 $window.alert("Commented successfully");
					 $window.location.reload();
					 $location.path("/SingleJob"); 
				});
	};
		fetchAllJobs();
		listApplyJob();
});