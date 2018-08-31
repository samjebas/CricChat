/**
 * JS for modal routing 
 *
 */

$(document).ready(function(){
	console.log('inside jqry');
	$("#btnShowEditForm").click(function(){
		$("#editBlogForm").modal('show');
	});
	
	$("#btnUpdateBlog").click(function(){
		$("#editBlogForm").modal('hide');
	});
	
	$("#btnShowEditForumForm").click(function(){
		$('#editForumForm').modal('show');
	});
	
	$("#btnUpdateForum").click(function(){
		console.log('closing editForumForm');
		$('#editForumForm').modal('hide');
	});
	$("#myBtn").click(function() {
			$("#myModal").modal('show');
		})
	
	
});