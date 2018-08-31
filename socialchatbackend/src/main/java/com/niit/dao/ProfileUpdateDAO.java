package com.niit.dao;

import com.niit.model.Profile;

public interface ProfileUpdateDAO  {
	
	
	public void save(Profile profilePicture);
	public Profile getProfilePicture(String loginName);

}
