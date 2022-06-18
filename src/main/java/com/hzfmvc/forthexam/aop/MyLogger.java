package com.hzfmvc.forthexam.aop;


import com.google.gson.Gson;
import com.hzfmvc.forthexam.dao.LoggerService;
import com.hzfmvc.forthexam.entity.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class MyLogger {

    @Autowired
    private LoggerService loggerService;


    /**
     * 设置操作日志切入点 记录操作日志 在注解的位置切入代码
     */
    @Pointcut("@annotation(com.hzfmvc.forthexam.aop.MyAnnotation)")
    public void loggerPointCut() {}

    /**
     * 配置环绕通知,使用在方法logPointcut()上注册的切入点
     *
     * @param point join point for advice
     */
    @Around("loggerPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object result = point.proceed();
        long time =  System.currentTimeMillis() - beginTime;
        try {
            saveLog(point, time);
        } catch (Exception ignored) {
        }
        return result;
    }
    /**
     * 保存日志
     */
    private void saveLog(ProceedingJoinPoint joinPoint, long time) {
        Logger mylogger = new Logger();
        //获取请求url,remark
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String uri = request.getRequestURI();
        System.out.println("url==> "+ uri); // /api/signin
        mylogger.setReqURL(url);


        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        mylogger.setSpendTime(time);
        MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
        if(annotation != null){
            //注解上的描述
            mylogger.setOperation(annotation.value());
        }

        //请求的参数
        Object[] args = joinPoint.getArgs();
        try{
            List<String> list = new ArrayList<>();
            for (Object o : args) {
                list.add(new Gson().toJson(o));
            }

        }catch (Exception e){
            // get StackTraceElements
            // using getStackTrace()
            StackTraceElement[] stktrace
                    = e.getStackTrace();

            // print element of stktrace
            for (int i = 0; i < stktrace.length; i++) {

                System.out.println("Index " + i
                        + " of stack trace"
                        + " array conatins = "
                        + stktrace[i].toString());
            }

        }
        loggerService.saveLog(mylogger);
    }

}
