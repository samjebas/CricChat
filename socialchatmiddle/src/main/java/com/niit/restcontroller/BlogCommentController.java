package com.niit.restcontroller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.BlogCommentDAO;

import com.niit.model.BlogComment;

@RestController
public class BlogCommentController {
	
	@Autowired
	BlogCommentDAO blogCommentDAO;
	
	
	// ---------------- Add BlogComments -----------------------------------

			@PostMapping(value = "/addBlogComment")
			public ResponseEntity<String> addBlogComments(@RequestBody BlogComment blogComment) {
				System.out.println("In AddBlogComments() method");
				blogComment.setCommentDate(new Date());
				blogComment.setBlogId(blogComment.getBlogId());
				blogComment.setCommentDate(new Date());
				blogComment.setCommentText(blogComment.getCommentText());
				blogComment.setUserName(blogComment.getUserName());
				blogComment.setLoginname(blogComment.getLoginName());
				/*BlogComment blog = blogCommentDAO.getBlogComment(1);
				String username = blog.getUserName();
				int blogId = blog.getBlogId();
				blogComment.setBlogId(blogId);
				blogComment.setUserName(username);*/
				if (blogCommentDAO.addBlogComment(blogComment)) {
					return new ResponseEntity<String>("BlogComment Added- Success", HttpStatus.OK);
				} else {
					return new ResponseEntity<String>("BlodComment insert failed", HttpStatus.NOT_FOUND);
				}
			}

			// -------------------------Delete BlogComment	 ---------------------

			@DeleteMapping(value = "/deleteBlogComment/{commentId}")
			public ResponseEntity<String> deleteBlogComment(@PathVariable("commentId") int commentId) {
				System.out.println("Delete blogComment with comment Id: " + commentId);
				BlogComment blogComment = blogCommentDAO.getBlogComment(commentId);
				if (blogComment == null) {
					System.out.println("No blog " + commentId + " found to delete");
					return new ResponseEntity<String>("No blogcomment with comment Id: " + commentId + " found to delete",
							HttpStatus.NOT_FOUND);
				} else {
					blogCommentDAO.deleteBlogComment(blogComment);
					return new ResponseEntity<String>("BlogComment with comment Id " + commentId + " deleted successfully", HttpStatus.OK);
				}

			}
			// -----------------------Get BlogComment ------------------------------------

			@GetMapping(value = "/getBlogComment/{commentId}")
			public ResponseEntity<String> getBlogComment(@PathVariable("commentId") int commentId) {
				System.out.println("Get BlogComment " + commentId);
				BlogComment blogComment = blogCommentDAO.getBlogComment(commentId);
				if (blogComment == null) {
					return new ResponseEntity<String>("Get blogComment failure", HttpStatus.NOT_FOUND);
				} else {
					return new ResponseEntity<String>("Get blogComment Success", HttpStatus.OK);
				}
			}

			// -----------------list Blog Commentss ---------------------------------
			@GetMapping(value = "/listBlogComments/{blogId}")
			public ResponseEntity<List<BlogComment>> listBlogComments(@PathVariable("blogId")int blogId) {
				System.out.println("In listBlogcomments() method");
				List<BlogComment> listBlogComments = blogCommentDAO.listBlogComment(blogId);
				if (listBlogComments.size() != 0) {
					return new ResponseEntity<List<BlogComment>>(listBlogComments, HttpStatus.OK);
				} else {
					return new ResponseEntity<List<BlogComment>>(listBlogComments, HttpStatus.NOT_FOUND);
				}
			}
			


	}


