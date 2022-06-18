package com.hzfmvc.forthexam.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Submissions")
public class Submission {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "taskid")
    private int taskId;

    @Column(name = "sub_time")
    private Date submitTime;

    @Column(name = "stu_name")
    private String studentName;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "iscompleted")
    private int iscompleted;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIscompleted() {
        return iscompleted;
    }

    public void setIscompleted(int iscompleted) {
        this.iscompleted = iscompleted;
    }

    @Override
    public String toString() {
        return "Submission{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", submitTime='" + submitTime + '\'' +
                ", studentName='" + studentName + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", iscompleted=" + iscompleted +
                '}';
    }
}
