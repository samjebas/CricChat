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
@SequenceGenerator(name="applyjob_sequence", sequenceName = "applyjob_id_seq", initialValue = 1, allocationSize = 1)
public class ApplyJob {
	
	private int jobId;
	@Id
	@GeneratedValue(generator="applyjob_sequence",strategy=GenerationType.SEQUENCE)
	private int appliationId;
	private String loginName;
	private Date appliedDate;
	
	
	
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public int getAppliationId() {
		return appliationId;
	}
	public void setAppliationId(int appliationId) {
		this.appliationId = appliationId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginname(String loginName) {
		this.loginName = loginName;
	}
	public Date getAppliedDate() {
		return appliedDate;
	}
	public void setAppliedDate(Date appliedDate) {
		this.appliedDate = appliedDate;
	}
	

}
