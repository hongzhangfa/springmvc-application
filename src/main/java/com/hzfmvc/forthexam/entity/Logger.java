package com.hzfmvc.forthexam.entity;

import javax.persistence.*;

@Entity
@Table(name = "Logger")
public class Logger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "operation")
    private String operation;

    @Column(name = "reqURL")
    private String reqURL;

    @Column(name = "spendtime")
    private long spendTime;

    public Logger(String operation, String reqURL, int spendTime) {
        this.operation = operation;
        this.reqURL = reqURL;
        this.spendTime = spendTime;
    }

    public Logger() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getReqURL() {
        return reqURL;
    }

    public void setReqURL(String reqURL) {
        this.reqURL = reqURL;
    }

    public long getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(Long spendTime) {
        this.spendTime = spendTime;
    }

    @Override
    public String toString() {
        return "Logger{" +
                "id=" + id +
                ", operation='" + operation + '\'' +
                ", reqURL='" + reqURL + '\'' +
                ", spendTime=" + spendTime +
                '}';
    }
}
