package com.niit.dao;

import java.util.List;

import com.niit.model.ApplyJob;
import com.niit.model.JobDetail;

public interface JobDetailDAO {
	public boolean addJob(JobDetail job);
	public boolean deleteJob(JobDetail job);
	public boolean updateJob(JobDetail job);
	public JobDetail getJob(int jobId);
	public List<JobDetail> listAllJobs();
	public boolean applyJob(ApplyJob applyJob);
	public List<ApplyJob> getAllAppliedJobDetails();
}
