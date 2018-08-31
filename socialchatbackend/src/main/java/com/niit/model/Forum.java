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
@SequenceGenerator(name = "forumidseq", sequenceName = "forum_id_seq", allocationSize = 1)
public class Forum {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "forumidseq")
	private int forumId;
	private String forumName;
	private String forumContent;
	private Date createDate;
	private String loginName;
	private String status;

	public int getForumId() {
		return forumId;
	}

	public void setForumId(int forumId) {
		this.forumId = forumId;
	}

	public String getForumName() {
		return forumName;
	}

	public void setForumName(String forumName) {
		this.forumName = forumName;
	}

	public String getForumContent() {
		return forumContent;
	}

	public void setForumContent(String forumContent) {
		this.forumContent = forumContent;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginname) {
		this.loginName = loginname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
