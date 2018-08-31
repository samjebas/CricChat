package com.niit.model;

import java.util.Date;

public class OutputMessage extends Chat {
	
	private Date time;
	

	public OutputMessage(Chat message, Date msgtime) {
		this.setId(message.getId());
		this.setMessage(message.getMessage());
		time = msgtime;
		
		
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
