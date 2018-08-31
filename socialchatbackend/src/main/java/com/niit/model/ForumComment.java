package com.niit.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table
@SequenceGenerator(name = "forumcommentidseq", sequenceName="forumcomment_id_seq", allocationSize = 1)
public class ForumComment {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "forumcommentidseq")
	private int commentId;
	private int forumId;
	private String commentText;
	private Date commentDate;
	private String loginName;

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getForumId() {
		return forumId;
	}

	public void setForumId(int forumId) {
		this.forumId = forumId;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginname(String loginName) {
		this.loginName = loginName;
	}

}
