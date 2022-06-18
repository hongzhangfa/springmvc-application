package com.hzfmvc.forthexam.dao.impl;

import com.hzfmvc.forthexam.dao.TaskDao;
import com.hzfmvc.forthexam.dao.TaskService;
import com.hzfmvc.forthexam.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDao taskDao;


    @Override
    public Task addTask(Task task) {
       taskDao.save(task);
       return task;
    }

    @Override
    public void deleteTask(int id) {
        taskDao.deleteById(id);
    }

    @Override
    public Task updateTask(Task task) {
        return taskDao.saveAndFlush(task);
    }

    @Override
    public Task getTask(int id) {
        return taskDao.getById(id);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskDao.findAll();
    }
}
