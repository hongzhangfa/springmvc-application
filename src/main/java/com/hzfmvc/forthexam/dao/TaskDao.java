package com.hzfmvc.forthexam.dao;

import com.hzfmvc.forthexam.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskDao extends JpaRepository<Task, Integer> {
}
