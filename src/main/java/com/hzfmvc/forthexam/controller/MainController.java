package com.hzfmvc.forthexam.controller;

import com.hzfmvc.forthexam.aop.MyAnnotation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {


    @MyAnnotation("登录成功")
    @GetMapping("/hzfSpringmvcExam")
    public String login(){
        return "index";
    }

    @MyAnnotation("错误请求")
    @GetMapping("/error")
    public String error(){
        return "error";
    }

}
