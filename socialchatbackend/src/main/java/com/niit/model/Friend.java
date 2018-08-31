package com.niit.model;

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
@SequenceGenerator(name = "friendidseq", sequenceName="job_id_sequence", allocationSize = 1)
public class Friend {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "friendidseq")
	private int friendId;

	private String loginName;
	private String friendloginname;
	private String status;

	public int getFriendId() {
		return friendId;
	}

	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getFriendloginname() {
		return friendloginname;
	}

	public void setFriendloginname(String friendloginname) {
		this.friendloginname = friendloginname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
