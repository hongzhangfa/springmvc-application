package com.hzfmvc.forthexam.dao.impl;

import com.hzfmvc.forthexam.dao.SubmissionDao;
import com.hzfmvc.forthexam.dao.SubmissionService;
import com.hzfmvc.forthexam.entity.Submission;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class SubmissionServiceImpl implements SubmissionService {

    @Autowired
    private SubmissionDao submissionDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Submission addSubmission(Submission submission) {
//         submissionDao.save(submission);
//         return submission;
        Session session = entityManager.unwrap(Session.class );
        session.save(submission);
        return submission;
    }

    @Override
    public Submission updateSubmission(Submission submission) {
        return submissionDao.saveAndFlush(submission);
    }

    @Override
    public List<Submission> getAllSubmission() {
        return submissionDao.findAll();
    }

    @Override
    public List<Submission> getSubmissionsByTaskId(int id) {
        return submissionDao.getSubmissionsByTaskId(id);
    }

    @Override
    public int getSubmissionCondition(int taskId, String stuName) {
        return submissionDao.getSubmissionCondition(taskId, stuName);
    }

    @Override
    public Submission getSubByTaskIdAndStuName(int taskId, String stuName) {
        return submissionDao.getSubByTaskIdAndStuName(taskId, stuName);
    }

    @Override
    public List<Submission> getSubsByTaskIdAndStuName(int taskId, String stuName) {
        return submissionDao.getSubsByTaskIdAndStuName(taskId, stuName);
    }

    @Override
    public List<Submission> getSubmissionByStu(String stuName) {
        return submissionDao.getSubmissionsByStu(stuName);
    }

    @Override
    public int delSubsByTaskIdAndStuName(int taskId, String stuName) {
        return submissionDao.delSubsByTaskIdAndStuName(taskId, stuName);
    }

    @Override
    public void deleteSubmission(int id) {

    }

}
