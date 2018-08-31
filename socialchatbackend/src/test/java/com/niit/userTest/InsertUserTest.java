package com.niit.userTest;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.UserDetailDAO;
import com.niit.model.UserDetail;

public class InsertUserTest {

	private static UserDetailDAO userDao;
	 private UserDetail user;

	@BeforeClass
	public static void initialize(){
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		userDao = (UserDetailDAO) context.getBean("UserDAO");
	}
	//@Ignore
	@Test
	public void insertUserTest() throws ParseException {
		
	    user =new UserDetail();
		user.setUserName("sam");
		user.setEmailid("sam@gmail.com");
		user.setPassword("sam");
		user.setRole("ROLE_USER");
		assertEquals("Successfully added User into the table", true, userDao.registerUser(user));
		
		System.out.println("<-----------Successfully inserted into User-------->");
	}
}
