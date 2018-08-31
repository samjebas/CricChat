package com.niit.forumTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.ForumDAO;
import com.niit.model.Forum;
import com.thoughtworks.qdox.parser.ParseException;

public class ForumTest {
	
	private static ForumDAO forumDao;
	private Forum forum;
	

	@BeforeClass
	public static void initialize() {
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		forumDao = (ForumDAO) context.getBean("forumDAO");
	}

	
	@Test
	public void insertForumTest() throws ParseException {

		forum = new Forum();
		forum.setForumName("Interval Match Analysis");
		forum.setLoginName("Jebas");
		forum.setForumContent("How to Outplay the Opponent Team ");
		forum.setCreateDate(new Date());
		forum.setStatus("A");
		assertEquals("Successfully added Forum into the table", true, forumDao.addForum(forum));

		System.out.println("<-----------Successfully inserted into Forum-------->");
	}

	
	@Test
	public void updateForumTest() throws ParseException {

		forum = forumDao.getForum(1);
		forum.setForumName("MasterBlaster");
		forum.setForumContent("This is the best ..");
		forum.setStatus("AP");
		forum.setCreateDate(new Date());
		forum.setLoginName("ShubhamRDurugkar");
		assertEquals("Successfully updated forum into the table", true, forumDao.updateForum(forum));
		System.out.println("<-----------Successfully updated forum-------->");
	}

	//@Ignore
	@Test
	public void testGetForum() {
		forum = forumDao.getForum(6);
		assertEquals("Successfully fetched a forum details from the table", "Post Match Analysis", forum.getForumName());
		System.out.println("<=========Forum fetched=======>");
		System.out.println("forumID :" + forum.getForumId());
		System.out.println("forumName :" + forum.getForumName());
		System.out.println("forumContent :" + forum.getForumContent());
		System.out.println("Username :" + forum.getLoginName());
		System.out.println("Status :" + forum.getStatus());
		System.out.println("Created Date :" + forum.getCreateDate());
		System.out.println("<-----------Successfully fetched forum-------->");
	}

	//@Ignore
	@Test
	public void testListForums() {
		List<Forum> listForums = forumDao.listForum();
		assertTrue("Successfully fetched all forums from the table", forumDao.listForum().size() > 0);
		System.out.println("<======List of Forum fetched======>");
		for (Forum forum : listForums) {
			System.out.println("forumID :" + forum.getForumId());
			System.out.println("forumName :" + forum.getForumName());
			System.out.println("forumContent :" + forum.getForumContent());
			System.out.println("Username :" + forum.getLoginName());
			System.out.println("Status :" + forum.getStatus());
			System.out.println("Created Date :" + forum.getCreateDate());
		}
		System.out.println("<-----------Successfully retrieved list of forum-------->");
	}

	@Ignore
	@Test
	public void testDeleteForum() {
		forum = forumDao.getForum(2);
		assertEquals("Successfully deleted forum details from the table", true, forumDao.deleteForum(forum));
		System.out.println("<-----------Successfully deleted forum-------->");
	}

	//@Ignore
	@Test
	public void testApproveForum() {
		forum = forumDao.getForum(3);
		String sts = forum.getStatus();
		if (sts.equals("NA")) {
			assertEquals("Successfully approved forum int the table", true, forumDao.approveForum(forum));
			System.out.println("<-----------Successfully approved forum-------->");
		} else {
			System.out.println("not approved");
		}
	}

	//@Ignore
	@Test
	public void testRejectForum() {
		forum = forumDao.getForum(3);
		String sts = forum.getStatus();
		if (sts.equals("A")) {
			assertEquals("Successfully approved forum int the table", true, forumDao.rejectForum(forum));
			System.out.println("<-----------Successfully rejected forum-------->");
		} else {
			System.out.println("not approved");
		}
	}


}
