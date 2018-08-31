package com.niit.blogTest;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.BlogDAO;
import com.niit.model.Blog;
import com.thoughtworks.qdox.parser.ParseException;

public class BlogDAOTest {

	private static BlogDAO blogDao;
	private Blog blog;

	@BeforeClass
	public static void intialise() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		blogDao = (BlogDAO) context.getBean("blogDAO");
	}

	@Test
	public void test() {
		System.out.println("---------Config Tested-------------");

	}
	
@Test
	public void insertBlogTest() throws ParseException {
		blog = new Blog();
		blog.setBlogName(" New Premier League");
		blog.setLoginName("jSSJ");
		blog.setBlogContent(" Bowling League");
		blog.setStatus("A");
		blog.setLikes(10);
		blog.setDislikes(2);
		blog.setCreatedDate((java.util.Date) new Date());
		assertEquals("Successfully added Blog ", true, blogDao.addBlog(blog));
	}

	
	@Test
	public void updateBlogTest() throws ParseException {
		blog = new Blog();
		blog = blogDao.getBlog(15);
		blog.setBlogName("Pakistan Premier League PSL");
		blog.setBlogContent("Welcome To Pakistan T20 ");
		assertEquals("Successfully updated blog name & content into the Table", true, blogDao.updateBlog(blog));
		System.out.println("<-----------Successfully updated blog-------->");

	}

	//@Ignore
	@Test
	public void getBlogTest() {
		blog = blogDao.getBlog(15);
		
		System.out.println("<=========Blog fetched=======>");
		System.out.println("blogID :" + blog.getBlogId());
		System.out.println("blogName :" + blog.getBlogName());
		System.out.println("blogContent :" + blog.getBlogContent());
		System.out.println("Username :" + blog.getLoginName());
		System.out.println("Status :" + blog.getStatus());
		System.out.println("Likes :" + blog.getLikes());
		System.out.println("Created Date :" + blog.getCreatedDate());
		System.out.println("<-----------Successfully fetched blog-------->");

	}

	//@Ignore
	@Test
	public void listBlogTest() {
		List<Blog> listBlog = blogDao.listBlog();
		assertEquals("Successfully listed the blog details from the table", blogDao.listBlog().size()>0 );
		
		System.out.println("<======List of Blog fetched======>");
		for (Blog blog : listBlog) {
			System.out.println("blogID :" + blog.getBlogId());
			System.out.println("blogName :" + blog.getBlogName());
			System.out.println("blogContent :" + blog.getBlogContent());
			System.out.println("Username :" + blog.getLoginName());
			System.out.println("Status :" + blog.getStatus());
			System.out.println("Likes :" + blog.getLikes());
			System.out.println("Created Date :" + blog.getCreatedDate());
		}
		System.out.println("<-----------Successfully retrieved list of blog-------->");
	}

	//@Ignore
	@Test
	public void approveBlogTest() {

		blog = blogDao.getBlog(15);
		String status = blog.getStatus();
		if (status.equals("NA")) {
			assertEquals("Successfully approved blog int the table", true, blogDao.approveBlog(blog));
			System.out.println("<-----------Successfully approved blog-------->");
		} else {
			System.out.println("Blog not approved");
		}
	}

	//@Ignore
	@Test
	public void rejectBlogTest() {

		blog = blogDao.getBlog(13);
		String status = blog.getStatus();
		if (status.equals("A")) {

			assertEquals("Successfully approved blog int the table", true, blogDao.approveBlog(blog));
			System.out.println("<-----------Successfully approved blog-------->");

		} else {
			System.out.println("approved");
		}
	}

	@Ignore
	@Test
	public void deleteBlogTest() {
		blog = blogDao.getBlog(2);
		assertEquals("Successfully approved blog int the table", true, blogDao.deleteBlog(blog));
		System.out.println("<-----------Successfully deleted blog-------->");

	}
	
   // @Ignore
	@Test
	public void incrementLikesTest() {
		blog = blogDao.getBlog(12);
		assertEquals("Successfully incremented likes to the table", true, blogDao.incrementLikes(blog));
		System.out.println("<=========Likes=========>");
		System.out.println("Likes After incrementing :" + blog.getLikes());
		System.out.println("<-----------Successfully incremented blog likes-------->");
	}
    
   // @Ignore
  	@Test
  	public void disLikesTest() {
  		blog = blogDao.getBlog(14);
  		assertEquals("Successfully decremented likes to the table", true, blogDao.disLikes(blog));
  		System.out.println("<=========DisLikes=========>");
  		System.out.println("Dislikes After incrementing :" + blog.getDislikes());
  		System.out.println("<-----------Successfully  blog is updated with dislikes-------->");
  	}
    
    
    
    
}