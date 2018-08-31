package com.niit.daoimpl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.niit.dao.ProfileUpdateDAO;
import com.niit.model.Profile;



@Service
@Repository("profileUpdateDAO")
public class ProfileUpdateDAOimpl implements ProfileUpdateDAO {

	@Autowired SessionFactory sessionfactory;
	
	@Autowired
	public  ProfileUpdateDAOimpl(SessionFactory sf) {
		super();
		this.sessionfactory = sf;
		
	}

	@Override
	@Transactional
	public void save(Profile profilePicture) {
		System.out.println("Profile Loginname : " + profilePicture.getLoginName());
		Session session = sessionfactory.openSession();
		session.saveOrUpdate(profilePicture);

		session.close();
		
	}

	@Override
	@Transactional
	public Profile getProfilePicture(String loginName) {
		Session session = sessionfactory.openSession();
		Profile profilePicture = (Profile) session.get(Profile.class, loginName);
		session.close();
		return profilePicture;
		
	
	}
	


}
