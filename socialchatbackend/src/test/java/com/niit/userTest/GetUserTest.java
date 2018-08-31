package com.niit.userTest;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.UserDetailDAO;
import com.niit.model.UserDetail;

public class GetUserTest {

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
	public void testGetUser() {
		user = userDao.getUser("Sam");
		assertEquals("Successfully fetched a user details from the table", "Jebastin", user.getUserName());
		System.out.println("<-----------Successfully fetched user-------->");
	}
}
