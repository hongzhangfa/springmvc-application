package com.hzfmvc.forthexam.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Task")
public class Task {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "starttime")
    private Date startTime;

    //在date类型上加入注解，同时指定接收到的日期格式样式
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "deadline")
    private Date deadline;

    @Column(name = "arranger")
    private String arranger;

    @Column(name = "people_num")
    private int peopleNum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getArranger() {
        return arranger;
    }

    public void setArranger(String arranger) {
        this.arranger = arranger;
    }

    public int getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(int peopleNum) {
        this.peopleNum = peopleNum;
    }

    @Override
    public String toString() {
        return "Task [id=" + id + ", title=" + title + ", startTime=" + startTime + ", deadline=" + deadline
                + ", arranger=" + arranger + ", peopleNum=" + peopleNum + "]";
    }


}

