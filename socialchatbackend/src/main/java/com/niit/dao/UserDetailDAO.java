package com.niit.dao;

import com.niit.model.UserDetail;
import java.util.List;

public interface UserDetailDAO {

	public boolean registerUser(UserDetail userDetail);
	public boolean checkLogin(UserDetail userDetail);
	public UserDetail getUser(String loginName);
	public boolean updateOnlineStatus(String status,UserDetail userDetail);
	public boolean updateUser(UserDetail user);
	public boolean deleteuser(UserDetail user);
	public List<UserDetail> listUsers();
	
	
	
	
}
