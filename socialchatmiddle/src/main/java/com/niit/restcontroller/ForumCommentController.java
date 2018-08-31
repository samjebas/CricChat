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

import com.niit.dao.ForumCommentDAO;
import com.niit.model.ForumComment;

@RestController
public class ForumCommentController {
	
	@Autowired
	ForumCommentDAO forumCommentDAO;
	
	// ---------------- Add ForumComments -----------------------------------

			@PostMapping(value = "/addForumComment")
			public ResponseEntity<String> addForumComments(@RequestBody ForumComment forumComment) {
				
				System.out.println("In AddForumComment() method");
				forumComment.setForumId(forumComment.getForumId());
				forumComment.setCommentDate(new java.util.Date());
				forumComment.setCommentText(forumComment.getCommentText());
				forumComment.setLoginname(forumComment.getLoginName());
			
				
				
				if (forumCommentDAO.addForumComment(forumComment)) {
					return new ResponseEntity<String>("ForumComment Added- Success", HttpStatus.OK);
				} else {
					return new ResponseEntity<String>("ForumComment insert failed", HttpStatus.NOT_FOUND);
				}
			}

			// -------------------------Delete ForumComment	 ---------------------

			@DeleteMapping(value = "/deleteForumComment/{commentId}")
			public ResponseEntity<String> deleteForumComment(@PathVariable("commentId") int commentId) {
				System.out.println("Delete forumComment with comment Id: " + commentId);
				ForumComment forumComment = forumCommentDAO.getForumComment(commentId);
				if (forumComment == null) {
					System.out.println("No forum " + commentId + " found to delete");
					return new ResponseEntity<String>("No forumcomment with comment Id: " + commentId + " found to delete",
							HttpStatus.NOT_FOUND);
				} else {
					forumCommentDAO.deleteForumComment(forumComment);
					return new ResponseEntity<String>("ForumComment with comment Id " + commentId + " deleted successfully", HttpStatus.OK);
				}

			}
			// -----------------------Get ForumComment ------------------------------------

			@GetMapping(value = "/getForumComment/{commentId}")
			public ResponseEntity<ForumComment> getForumComment(@PathVariable("commentId") int commentId) {
				System.out.println("Get ForumComment " + commentId);
				ForumComment forumComment = forumCommentDAO.getForumComment(commentId);
				if (forumComment == null) {
					return new ResponseEntity<ForumComment>(forumComment, HttpStatus.NOT_FOUND);
				} else {
					return new ResponseEntity<ForumComment>(forumComment, HttpStatus.OK);
				}
			}

			// -----------------list Forums ---------------------------------
			@GetMapping(value = "/listForumComments/{forumId}")
			public ResponseEntity<List<ForumComment>> listForumComments(@PathVariable("forumId")int forumId) {
				System.out.println("In listForumComments() method");
				List<ForumComment> listForumComments = forumCommentDAO.listForumComments(forumId);
				if (listForumComments.size() != 0) {
					return new ResponseEntity<List<ForumComment>>(listForumComments, HttpStatus.OK);
				} else {
					return new ResponseEntity<List<ForumComment>>(listForumComments, HttpStatus.NOT_FOUND);
				}
			}
			

	}


