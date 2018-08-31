package com.niit.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.niit.dao.JobDetailDAO;
import com.niit.model.ApplyJob;
import com.niit.model.JobDetail;

@Service
@Repository("jobDAO")
public class JobDetailDAOimpl implements JobDetailDAO {
	@Autowired
	SessionFactory sessionfactory;

	@Transactional
	public boolean addJob(JobDetail job) {
		try {
			sessionfactory.getCurrentSession().save(job);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	@Transactional
	public boolean deleteJob(JobDetail job) {
		try {
			sessionfactory.getCurrentSession().delete(job);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Transactional
	public boolean updateJob(JobDetail job) {
		try {
			sessionfactory.getCurrentSession().update(job);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Transactional
	public JobDetail getJob(int jobId) {
		try {
			Session session = sessionfactory.openSession();
			JobDetail job = session.get(JobDetail.class, jobId);
			return job;
		} catch (Exception e) {
			return null;
		}
	}

	@Transactional
	public List<JobDetail> listAllJobs() {
		try {
			Session session = sessionfactory.openSession();
			session.beginTransaction();
			List<JobDetail> jobList = new ArrayList<JobDetail>();
			Query query = session.createQuery("FROM JobDetail");
			jobList = query.list();
			return jobList;
		} catch (Exception e) {
			return null;
		}
	}

	@Transactional
	public boolean applyJob(ApplyJob applyJob) {
		try {
			sessionfactory.getCurrentSession().save(applyJob);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Transactional
	public List<ApplyJob> getAllAppliedJobDetails() {
		try {
			Session session = sessionfactory.openSession();
			session.beginTransaction();
			List<ApplyJob> appliedjobList = new ArrayList<ApplyJob>();
			Query query = session.createQuery("FROM ApplyJob");
			appliedjobList = query.list();
			return appliedjobList;
		} catch (Exception e) {
			return null;
		}
	}

}