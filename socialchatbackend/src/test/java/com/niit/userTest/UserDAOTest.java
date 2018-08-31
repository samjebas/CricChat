package com.niit.userTest;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.UserDetailDAO;
import com.niit.model.UserDetail;
import com.thoughtworks.qdox.parser.ParseException;

public class UserDAOTest {

	static UserDetailDAO userDetailDAO;

	@SuppressWarnings("resource")
	@BeforeClass
	public static void initialze() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		userDetailDAO = (UserDetailDAO) context.getBean("UserDAO");
	}

	//@Ignore
	@Test
	public void registerUserTest()  throws ParseException{
		UserDetail userDetail = new UserDetail();
		userDetail.setLoginName("SSJ");
		userDetail.setPassword("ssj");
		userDetail.setRole("ROLE_USER");
		userDetail.setUserName("Samuel");
		userDetail.setAddress("Thane");
		userDetail.setIsOnline("N");
		userDetail.setMobileNo("9489212844");
		userDetail.setEmailid("ssj@gmail.com");
		assertTrue("Problem in insertion", userDetailDAO.registerUser(userDetail));
	System.out.println("<============Successfully registered the User==================>");
	}
	//@Ignore
	@Test
	public void updateInlineStatusTest(){
		UserDetail userDetail=userDetailDAO.getUser("Sam");
		assertTrue("Problem in updating status",userDetailDAO.updateOnlineStatus("Y", userDetail));
		System.out.println("<============Updated the online status============>");
	}
	//@Ignore
	@Test
	public void checkUserTest(){
		UserDetail userDetail=new UserDetail();
		userDetail.setLoginName("Sam");
		userDetail.setPassword("sam");
		assertTrue("Check user failed",userDetailDAO.checkLogin(userDetail));
		System.out.println("<=================Checked user successfully=============>");
	}
	
}
