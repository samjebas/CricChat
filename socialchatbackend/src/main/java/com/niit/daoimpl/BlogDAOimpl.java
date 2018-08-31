package com.niit.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.niit.dao.BlogDAO;
import com.niit.model.Blog;
import com.niit.model.BlogComment;


@Service
@Repository("blogDAO")
public class BlogDAOimpl implements BlogDAO {
	
	
	@Autowired
	SessionFactory sessionfactory;

	@Transactional
	public boolean addBlog(Blog blog) {
		try {
			sessionfactory.getCurrentSession().saveOrUpdate(blog);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	

	@Transactional
	public boolean deleteBlog(Blog blog) {
		try {
			sessionfactory.getCurrentSession().delete(blog);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Transactional
	public boolean updateBlog(Blog blog) {
		try {
			sessionfactory.getCurrentSession().update(blog);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Transactional
	public boolean approveBlog(Blog blog) {
		try {
			blog.setStatus("A");
			sessionfactory.getCurrentSession().update(blog);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Transactional
	public boolean rejectBlog(Blog blog) {
		try {
			blog.setStatus("NA");
			sessionfactory.getCurrentSession().update(blog);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	

	@Transactional
	public boolean incrementLikes(Blog blog) {
		 try{
			 int likes=blog.getLikes();
			 likes++;
			 blog.setLikes(likes);
			 sessionfactory.getCurrentSession().update(blog);
			 return true;
		 }catch(Exception e)
		 {
			 return false;
		 }
	}

	@Transactional
	public boolean disLikes(Blog blog) {
		try{
			 int likes=blog.getDislikes();
			 likes++;
			 blog.setDislikes(likes);
			 sessionfactory.getCurrentSession().update(blog);
			 return true;
		 }catch(Exception e)
		 {
			 return false;
		 }
	}


	@Transactional
	public Blog getBlog(int blogId) {
		try {
			Session session = sessionfactory.openSession();
			Blog blog = session.get(Blog.class,blogId);
			return blog;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	@Transactional
	public List<Blog> listBlog() {
		try {
		Session session = sessionfactory.openSession();
		org.hibernate.query.Query querry =	session.createQuery("FROM Blog");
		List<Blog> bloglist = querry.list();
			return bloglist;
	}
		catch(Exception e) {
			return null;
		}
		}
}
