package com.niit.dao;

import com.niit.model.Profile;

public interface ProfileUpdateDAO  {
	
	
	public void saveProfilePic(Profile profile);
	public Profile getProfilePicture(String loginName);

}
