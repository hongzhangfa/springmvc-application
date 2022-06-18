package com.hzfmvc.forthexam.dao;

import com.hzfmvc.forthexam.entity.Logger;

import java.util.List;

public interface LoggerService {

    Logger saveLog(Logger logger);

    List<Logger> getAllLogs();
}
