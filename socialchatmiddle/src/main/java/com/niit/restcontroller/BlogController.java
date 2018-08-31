package com.niit.restcontroller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.BlogDAO;
import com.niit.model.Blog;

@RestController
public class BlogController {
	@Autowired
	BlogDAO blogDAO;

	// ------------------- Demo -----------------------------------
	@GetMapping(value = "/demo")
	public ResponseEntity<String> testDemo() {
		return new ResponseEntity<String>("Demo Rest Controller- Success", HttpStatus.OK);
	}

	// ---------------- Add Blog -----------------------------------

	@PostMapping(value = "/addBlog")
	public ResponseEntity<String> addBlog(@RequestBody Blog blog, HttpSession session) {

		System.out.println("=====>In addBlog restcontroller");
		blog.setCreatedDate(new java.util.Date());
		blog.setLikes(0);
		blog.setStatus("NA");
		// blog.setLoginName((String) session.getAttribute("loginName"));
		if (blogDAO.addBlog(blog)) {
			return new ResponseEntity<String>("Blog Added- Success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Blod insert failed", HttpStatus.NOT_FOUND);
		}
	}

	// -----------------list Blogs ---------------------------------

	@GetMapping(value = "/listBlogs")
	public ResponseEntity<List<Blog>> listBlog(HttpSession session) {
		List<Blog> listBlogs = blogDAO.listBlog();
		// List<Blog> listAllBlogs = blogDAO.listBlog((String)
		// session.getAttribute("adminlogin"));
		if (listBlogs.size() != 0) {
			return new ResponseEntity<List<Blog>>(listBlogs, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Blog>>(listBlogs, HttpStatus.NOT_FOUND);
		}
	}

	// ------------------Update Blog -----------------------------------

	@PutMapping(value = "/updateBlog/{blogId}")
	public ResponseEntity<String> updateBlog(@PathVariable("blogId") int blogId, @RequestBody Blog blog) {
		System.out.println("Updating Blog " + blogId);
		Blog mBlog = blogDAO.getBlog(blogId);
		if (mBlog == null) {
			System.out.println("Blog with blogId " + blogId + " Not Found");
			return new ResponseEntity<String>("Update Blog Failue", HttpStatus.NOT_FOUND);
		}

		mBlog.setBlogContent(blog.getBlogContent());
		mBlog.setBlogName(blog.getBlogName());
		mBlog.setCreatedDate(new Date());
		mBlog.setLikes(blog.getLikes());
		mBlog.setStatus(blog.getStatus());
		mBlog.setLoginName(blog.getLoginName());

		blogDAO.updateBlog(mBlog);
		return new ResponseEntity<String>("Update Blog Success", HttpStatus.OK);
	}

	// -----------------------Get Blog ------------------------------------

	@GetMapping(value = "/getBlog/{blogId}")
	public ResponseEntity<Blog> getBlog(@PathVariable("blogId") int blogId) {
		System.out.println("Get Blog " + blogId);
		Blog blog = blogDAO.getBlog(blogId);
		if (blog == null) {
			return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Blog>(blog, HttpStatus.OK);
		}
	}

	// -----------------------Approve Blog ----------------------------------

	@PutMapping(value = "/approveBlog/{blogId}")
	public ResponseEntity<String> approveBlog(@PathVariable("blogId") int blogId) {
		System.out.println("Approve Blog with Blog ID: " + blogId);
		Blog blog = blogDAO.getBlog(blogId);
		if (blog == null) {
			System.out.println("Not blog with blog Id: " + blogId + " found for Approval");
			return new ResponseEntity<String>("No Blog found for Approval", HttpStatus.NOT_FOUND);
		} else {
			blog.setStatus("A");
			blogDAO.approveBlog(blog);
			return new ResponseEntity<String>("Blog " + blogId + " Approved Successfully", HttpStatus.OK);
		}
	}

	// --------------------Reject Blog ----------------------------------

	@PutMapping(value = "/rejectBlog/{blogId}")
	public ResponseEntity<String> rejectBlog(@PathVariable("blogId") int blogId) {
		System.out.println("Disapprove Blog with Blog ID: " + blogId);
		Blog blog = blogDAO.getBlog(blogId);
		if (blog == null) {
			System.out.println("Not blog with blog Id: " + blogId + " found for Approval");
			return new ResponseEntity<String>("No Blog with Blog Id " + blogId + " found for Disapproval",
					HttpStatus.NOT_FOUND);
		} else {
			blog.setStatus("NA");
			blogDAO.rejectBlog(blog);
			return new ResponseEntity<String>("Blog " + blogId + " Disapproved Successfully", HttpStatus.OK);
		}
	}

	// -------------------------Delete Blog ---------------------

	@DeleteMapping(value = "/deleteBlog/{blogId}")
	public ResponseEntity<String> deleteBlog(@PathVariable("blogId") int blogId) {
		System.out.println("Delete blog with blog Id: " + blogId);
		Blog blog = blogDAO.getBlog(blogId);
		if (blog == null) {
			System.out.println("No blog " + blogId + " found to delete");
			return new ResponseEntity<String>("No blog with blog Id: " + blogId + " found to delete",
					HttpStatus.NOT_FOUND);
		} else {
			blogDAO.deleteBlog(blog);
			return new ResponseEntity<String>("Blog with Blog Id " + blogId + " deleted successfully", HttpStatus.OK);
		}

	}

	// -----------------increment likes ---------------------------------
	@PostMapping(value = "/incrementLikes/{blogId}")
	public ResponseEntity<String> incrementLikes(@PathVariable("blogId") int blogId) {
		System.out.println("Increment likes for BlogId " + blogId);
		Blog blog = blogDAO.getBlog(blogId);
		if (blogDAO.incrementLikes(blog)) {
			return new ResponseEntity<String>("Successfully incremented..", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Failed incremeneting likes..", HttpStatus.NOT_FOUND);
		}
	}

}
