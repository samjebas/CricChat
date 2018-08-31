package com.niit.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.niit.dao.UserDetailDAO;
import com.niit.model.UserDetail;

@Service
@Repository("UserDAO")
public class UserDetailDAOimpl implements UserDetailDAO {

	@Autowired
	SessionFactory sessionfactory;

	@Transactional
	public boolean registerUser(UserDetail userDetail) {

		try {
			sessionfactory.getCurrentSession().save(userDetail);
			return true;

		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return false;
		}

	}

	@Transactional
	@SuppressWarnings("deprecation")
	public boolean checkLogin(UserDetail userDetail) {

		try {
			Session session = sessionfactory.openSession();
			Query query = session.createQuery("from UserDetail where loginName=:loginName and password=:password");
			query.setParameter("loginName", userDetail.getLoginName());
			query.setParameter("password", userDetail.getPassword());
			UserDetail userDetails = (UserDetail) query.list().get(0);
			session.close();
			if (userDetails == null) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Transactional
	public UserDetail getUser(String loginName) {

		Session session = sessionfactory.openSession();
		UserDetail userDetails =session.get(UserDetail.class,loginName);
		session.close();
		return userDetails;

	}

	@Transactional
	public boolean updateUser(UserDetail user) {
		try {
			sessionfactory.getCurrentSession().saveOrUpdate(user);
			return true;

		} catch (HibernateException e) {

			e.printStackTrace();

			return false;
		}
	}

	@Transactional
	public boolean deleteuser(UserDetail user) {
		try {
			sessionfactory.getCurrentSession().delete(user);

			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public List<UserDetail> listUsers() {
		try {
			Session session = sessionfactory.openSession();
			session.beginTransaction();
			List<UserDetail> userList = new ArrayList<UserDetail>();
			Query query = session.createQuery("FROM UserDetail");
			userList = query.list();
			return userList;
		} catch (Exception e) {
			return null;
		}
	}

	@Transactional
	public boolean updateOnlineStatus(String status, UserDetail userDetail) {
		try {
			userDetail.setIsOnline(status);
			sessionfactory.getCurrentSession().update(userDetail);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
