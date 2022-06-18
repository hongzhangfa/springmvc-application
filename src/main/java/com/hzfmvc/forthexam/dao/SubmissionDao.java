package com.hzfmvc.forthexam.dao;

import com.hzfmvc.forthexam.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SubmissionDao extends JpaRepository<Submission, Integer> {

    @Query(value = "SELECT s FROM Submission s WHERE s.taskId = :taskId")
    List<Submission> getSubmissionsByTaskId(@Param("taskId")int taskId);

    @Query(value = "SELECT s.iscompleted FROM Submission s WHERE s.taskId = :taskId and s.studentName = :stuName")
    int getSubmissionCondition(@Param("taskId")int taskId, @Param("stuName")String stuName);


    @Query(value = "SELECT s FROM Submission s WHERE s.studentName = :stuName")
    List<Submission> getSubmissionsByStu(@Param("stuName")String stuName);

    @Query(value = "SELECT s FROM Submission s WHERE s.taskId = :taskId and s.studentName = :stuName")
    Submission getSubByTaskIdAndStuName(@Param("taskId")int taskId, @Param("stuName")String stuName);

    // 更新最近一条提交记录
    @Query(value = "SELECT s FROM Submission s WHERE s.taskId = :taskId and s.studentName = :stuName order by s.submitTime desc ")
    List<Submission> getSubsByTaskIdAndStuName(@Param("taskId")int taskId, @Param("stuName")String stuName);

    @Modifying
    @Transactional
    @Query(value = "delete FROM Submission s WHERE s.taskId = :taskId and s.studentName = :stuName")
    int delSubsByTaskIdAndStuName(@Param("taskId")int taskId, @Param("stuName")String stuName);


}
