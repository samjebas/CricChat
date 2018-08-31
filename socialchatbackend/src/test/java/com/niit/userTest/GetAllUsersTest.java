package com.niit.userTest;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.UserDetailDAO;

public class GetAllUsersTest {

	private static UserDetailDAO userDao;

	@BeforeClass
	public static void initialize() {
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		userDao = (UserDetailDAO) context.getBean("UserDAO");
	}

	@Test
	public void testListUser() {
		assertEquals("Successfully fetched all users from the table", 3,
				userDao.listUsers().size());

		System.out.println("<-----------Successfully retrieved list of user-------->");
	}
}
