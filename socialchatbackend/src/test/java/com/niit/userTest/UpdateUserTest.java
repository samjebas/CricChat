package com.niit.userTest;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.UserDetailDAO;
import com.niit.model.UserDetail;

public class UpdateUserTest {

	private static UserDetailDAO userDao;
	private UserDetail user;

	@BeforeClass
	public static void initialize() {
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		userDao = (UserDetailDAO) context.getBean("UserDAO");
	}

	@Test
	public void updateUserTest() throws ParseException {
		
		 user =new UserDetail();
		user = userDao.getUser("Sam");
		 user.setUserName("SamuelJebastin");
		assertEquals("Successfully updated user into the table", true, userDao.updateUser(user));
		System.out.println("<-----------Successfully updated user-------->");
	}
}
