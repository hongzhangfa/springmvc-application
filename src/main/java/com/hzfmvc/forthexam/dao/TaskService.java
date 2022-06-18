package com.hzfmvc.forthexam.dao;

import com.hzfmvc.forthexam.entity.Task;

import java.util.List;

public interface TaskService {

    //增加一条作业记录
    Task addTask(Task task);

    void deleteTask(int id);

    Task updateTask(Task task);

    Task getTask(int id);

    List<Task> getAllTasks();

}
