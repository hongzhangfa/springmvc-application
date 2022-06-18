package com.hzfmvc.forthexam.controller;

import com.hzfmvc.forthexam.aop.MyAnnotation;
import com.hzfmvc.forthexam.dao.SubmissionService;
import com.hzfmvc.forthexam.dao.TaskService;
import com.hzfmvc.forthexam.entity.Submission;
import com.hzfmvc.forthexam.entity.Task;
import com.hzfmvc.forthexam.utils.ArrayListExample;
import com.hzfmvc.forthexam.utils.SecUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/hzfSpringmvcExam/teacher")
@Slf4j
public class TeacherController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private SubmissionService submissionService;


    @MyAnnotation("教师布置作业")
    @PostMapping("/newTask")
    public String addTask(Model model) {
        Task task = new Task();
        model.addAttribute("task", task);
        return "teacher/new_task";
    }

    // 处理新添加数据
    @MyAnnotation("处理新增作业")
    @PostMapping("/newTaskProcess")
    public String ProcessAddTaskForm(@ModelAttribute("task") Task thetask, RedirectAttributes ra) {
//        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
//        String timeStamp = date.format(new Date());
//        System.out.println("Current Time Stamp: "+timeStamp);
//        System.out.println("Current new Date(): "+new Date());
        System.out.println("截止日期==> "+ thetask.getDeadline());
        String currentUsername = SecUtils.getCurrentUsername();
        System.out.println("currentUser: "+ currentUsername);

        thetask.setArranger(currentUsername);
        taskService.addTask(thetask);
        System.out.println("###新增作业id: "+ thetask.getId());

        ra.addFlashAttribute("message", "作业 "+ thetask.getId() +" 已成功保存!");

        return "redirect:/hzfSpringmvcExam/teacher/listTask";
    }

    @MyAnnotation("教师作业列表")
    @RequestMapping("/listTask")
    public String getTaskList(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("taskList", tasks);
        System.out.println("taskList==> "+ tasks);
        return "/teacher/list_task";
    }

    @MyAnnotation("教师编辑作业")
    @GetMapping("/edittask")
    public String showUpdateTaskForm(@RequestParam("taskid") int taskid, Model model) {
        Task thetask = taskService.getTask(taskid);
        System.out.println("编辑### thetask==> "+ thetask);
        model.addAttribute("task", thetask);
        return "/teacher/new_task";
    }

    @MyAnnotation("教师删除作业")
    @GetMapping("/deletetask")
    public String deleteTask(@RequestParam("taskid") int taskid, RedirectAttributes ra) {
        taskService.deleteTask(taskid);
        ra.addFlashAttribute("del", "作业 "+ taskid +" 已成功删除!");
        return "redirect:/hzfSpringmvcExam/teacher/listTask";
    }

    @MyAnnotation("教师查询提交情况")
    @GetMapping("/checktask")
    public String showCheckTaskForm(@RequestParam("taskid") int taskid, Model model) {
        List<Submission> submissions = submissionService.getSubmissionsByTaskId(taskid);
        System.out.println("All submissionList==> "+ submissions);
        List<Submission> submissionList = new ArrayList<>(submissions.size());

        if (submissions.size()>0) {
          List stuNamelist = ArrayListExample.removeDuplicate(submissions);
            for (int i = 0; i<stuNamelist.size(); i++) {
                List<Submission> thesubmission = submissionService.getSubsByTaskIdAndStuName(taskid, (String) stuNamelist.get(i));
                submissionList.add(thesubmission.get(0));
            }
            for (int j = 1; j<=submissionList.size(); j++) {
                         submissionList.get(j-1).setId(j);
            }

            model.addAttribute("submissionList", submissionList);
            System.out.println("去重 submissionList==> "+ submissionList);

        } else {
            model.addAttribute("submissionList", submissions);
        }


        return "teacher/submit_statistics";
    }


}
