package com.hzfmvc.forthexam.dao;

import com.hzfmvc.forthexam.entity.Submission;
import com.hzfmvc.forthexam.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubmissionService {

    // 添加
    Submission addSubmission(Submission submission);

    // 更新
    Submission updateSubmission(Submission submission);
    //查询所有的提交记录
    List<Submission> getAllSubmission();

    // 根据taskID查询
    List<Submission> getSubmissionsByTaskId(int id);

    // 查询某人某个作业是否提交
    int getSubmissionCondition(int taskId, String stuName);
    // 获取某人指定task提交记录
    Submission getSubByTaskIdAndStuName(int taskId, String stuName);

    List<Submission> getSubsByTaskIdAndStuName(int taskId, String stuName);
    // 获取某人提交次数
    List<Submission> getSubmissionByStu(String stuName);


    int delSubsByTaskIdAndStuName(int taskId, String stuName);
    void deleteSubmission(int id);

}
