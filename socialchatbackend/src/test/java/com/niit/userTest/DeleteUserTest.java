package com.niit.userTest;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.UserDetailDAO;
import com.niit.model.UserDetail;

public class DeleteUserTest {

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
	public void testDeleteUser() {
		user = userDao.getUser("Shubham");
		assertEquals("Successfully deleted user details from the table", true, userDao.deleteuser(user));
		System.out.println("<-----------Successfully deleted user-------->");
	}
}
