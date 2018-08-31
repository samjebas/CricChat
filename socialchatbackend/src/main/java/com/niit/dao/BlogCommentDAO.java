package com.niit.dao;

import java.util.List;

import com.niit.model.BlogComment;

public interface BlogCommentDAO {
	
	public boolean addBlogComment(BlogComment blogComment );
	public boolean deleteBlogComment(BlogComment blogComment);
	public BlogComment getBlogComment(int commentId);
	public List<BlogComment> listBlogComment(int blogId);

}
