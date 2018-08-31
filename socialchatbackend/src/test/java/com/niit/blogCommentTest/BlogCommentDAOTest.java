package com.niit.blogCommentTest;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.BlogCommentDAO;
import com.niit.dao.BlogDAO;
import com.niit.model.Blog;
import com.niit.model.BlogComment;



public class BlogCommentDAOTest {
	
	private static BlogCommentDAO blogcommentDao;
	
	
	private BlogComment blogComment;
	
	
	@BeforeClass
	public static void intialise() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		blogcommentDao =(BlogCommentDAO) context.getBean("blogCommentDAO");
	}

	

	@Test
	public void test() {
		System.out.println("---------Config Tested-------------");
	}
	
	//@Ignore
	@Test
	public void testAddBlogComment() {
	    blogComment = new BlogComment();
	    blogComment.setLoginname("SSJ");
	    blogComment.setBlogId(15);
		blogComment.setUserName("Samuel");
		blogComment.setCommentDate(new java.util.Date());
		blogComment.setCommentText("|It is League of Many Power Hitters Power");
		assertEquals("Successfully added the blogComment...", true, blogcommentDao.addBlogComment(blogComment));
		System.out.println("<-----------Successfully added blogCommment-------->");
	}

	@Ignore 
	@Test
	public void testGetBlogCommment() {
		blogComment = blogcommentDao.getBlogComment(8);
		assertEquals("Successfully fetched a blogComments from the table", "Samuel", blogComment.getUserName());
		
		System.out.println("<========BlogComment========>");
		System.out.println("blogID :" + blogComment.getBlogId());
		System.out.println("Username :" + blogComment.getUserName());
		System.out.println("Status :" + blogComment.getCommentId());
		System.out.println("Likes :" + blogComment.getCommentText());
		System.out.println("Created Date :" + blogComment.getCommentDate());
		System.out.println("<-----------Successfully fetched blogComment-------->");
	}

	@Ignore
	@Test
	public void testDeleteBlogComment() {
		blogComment = blogcommentDao.getBlogComment(5);
		assertEquals("Successfully deleted blog details from the table", true, blogcommentDao.deleteBlogComment(blogComment));
		System.out.println("<-----------Successfully deleted blogComment-------->");
	}

	 //@Ignore 
	@Test
	public void testListBlogComments() {
		List<BlogComment> listBlogComments = blogcommentDao.listBlogComment(8);
		assertTrue("Successfully fetched all blogs from the table", blogcommentDao.listBlogComment(14).size() > 0);
		System.out.println("<======BlogComments fetched======>");
		for (BlogComment blogComment : listBlogComments) {
			System.out.println("blogID :" + blogComment.getBlogId());
			System.out.println("CommentID :" + blogComment.getCommentId());
			System.out.println("Comment Text :" + blogComment.getCommentText());
			System.out.println("Username :" + blogComment.getUserName());
			System.out.println("Comment Date : " + blogComment.getCommentDate());
		}
		System.out.println("<-----------Successfully retrieved list of blogComments-------->");
	}

}

