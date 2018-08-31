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

import com.niit.dao.ForumCommentDAO;
import com.niit.model.Forum;
import com.niit.model.ForumComment;

@Service
@Repository("forumCommentDAO")
public class ForumCommentDAOimpl implements ForumCommentDAO {

	@Autowired SessionFactory sessionfactory;
	
	@Transactional
	public boolean addForumComment(ForumComment forumComment) {
		try {
			sessionfactory.getCurrentSession().saveOrUpdate(forumComment);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean deleteForumComment(ForumComment forumComment) {
		try {
			sessionfactory.getCurrentSession().delete(forumComment);
			return true;
		} catch (HibernateException e) {
		
			e.printStackTrace();
			return false;
		}
		
	}

	@Transactional
	public ForumComment getForumComment(int commentId) {
		try {
			Session session = sessionfactory.openSession();
			ForumComment forumComment = session.get(ForumComment.class, commentId);
			return forumComment;
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return null;
		}
	}

	@Transactional
	public List<ForumComment> listForumComments(int forumId) {
		try {
			Session session = sessionfactory.openSession();
			Query query =session.createQuery("from ForumComment where forumId=:forumId");
			query.setParameter("forumId", new Integer (forumId));
			List<ForumComment> forumCommentList = query.list();
			return forumCommentList;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	

}
