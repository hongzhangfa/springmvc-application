package com.hzfmvc.forthexam.dao.impl;

import com.hzfmvc.forthexam.dao.LoggerRepository;
import com.hzfmvc.forthexam.dao.LoggerService;
import com.hzfmvc.forthexam.entity.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoggerServiceImpl implements LoggerService {

    @Autowired
    private LoggerRepository loggerRepository;

    @Override
    public Logger saveLog(Logger logger) {
        loggerRepository.save(logger);
        return logger;
    }

    @Override
    public List<Logger> getAllLogs() {
        return loggerRepository.findAll();
    }
}
