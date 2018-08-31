package com.niit.dao;

import java.util.List;

import com.niit.model.Forum;
import com.niit.model.ForumComment;

public interface ForumCommentDAO {
	
	
	
	public boolean addForumComment(ForumComment forumComment);
	public boolean deleteForumComment(ForumComment forumComment);
	public ForumComment getForumComment(int commentId);
	public List<ForumComment> listForumComments(int forumId);

}
