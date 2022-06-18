package com.hzfmvc.forthexam.controller;

import com.hzfmvc.forthexam.aop.MyAnnotation;
import com.hzfmvc.forthexam.dao.SubmissionService;
import com.hzfmvc.forthexam.dao.TaskService;
import com.hzfmvc.forthexam.entity.Submission;
import com.hzfmvc.forthexam.entity.Task;
import com.hzfmvc.forthexam.utils.ArrayListExample;
import com.hzfmvc.forthexam.utils.SecUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/hzfSpringmvcExam/student")
public class StudentController {

    @Autowired
    TaskService taskService;

    @Autowired
    SubmissionService submissionService;

    @MyAnnotation("学生查询作业列表")
    @RequestMapping("/submitTask")
    public String getTaskList(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("taskList", tasks);

        /* 获取当前学生提交记录/判断是否已提交 */
        String currentUsername = SecUtils.getCurrentUsername();
        List<Submission> submissions = submissionService.getSubmissionByStu(currentUsername);
        if (submissions.size()>0) {
            int[] myList = new int[submissions.size()];
            String tempStr = "";
            for (int i = 0; i < submissions.size(); i++) {
                myList[i] = submissions.get(i).getTaskId();
                tempStr += submissions.get(i).getTaskId();
                System.out.println("已提交作业ID==> "+ submissions.get(i).getTaskId());
            }
            System.out.println("###: "+ Arrays.toString(myList)+ "###: "+ tempStr);
            model.addAttribute("submissionId", tempStr);
        }

        /*  统计各个作业提交人数 */

        int[] taskId = new int[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            taskId[i] = tasks.get(i).getId();
        }
        System.out.println("AlltaskId: "+ Arrays.toString(taskId)); // 获取所有作业id集合

        List<Integer> taskIdList = new ArrayList<>();
        for (int i=0; i<tasks.size(); i++) {
            taskIdList.add(tasks.get(i).getId());
        }
        System.out.println(">>>All所有作业id: "+ taskIdList);

        List<Submission> allSubmission = submissionService.getAllSubmission();
        if (allSubmission.size()>0) {
            int[] subTaskId = new int[allSubmission.size()];
            for (int i = 0; i < allSubmission.size(); i++) {
                subTaskId[i] = allSubmission.get(i).getTaskId();
            }
            // 对数组进行排序
            Arrays.sort(subTaskId);
            System.out.println("All已提交作业ID## 排序==> "+ Arrays.toString(subTaskId)); // 获取已提交作业id集合

            List<Integer> subIdList = new ArrayList<>();
            subIdList.add(subTaskId[0]);
            for(int i=1;i<subTaskId.length;i++){
                if(subTaskId[i] != (subIdList.get(subIdList.size()-1))){
                    subIdList.add(subTaskId[i]);
                }
            }
            System.out.println("去重 ### subIdList: "+ subIdList);

            /* taskid 删除，相应的submissions也要删除 */
            for (int i=0; i<subIdList.size(); i++){
                int task_id = subIdList.get(i);
                if ( taskIdList.contains(task_id)){
                     System.out.println("包含存在taskid提交: "+ task_id);
                     //计算各个task提交人数...

                    // for (int j = 0; j < subIdList.size(); j++) {
                       //  Integer task_id = subIdList.get(j);

                         List<Submission> submitedCounts = submissionService.getSubmissionsByTaskId(task_id);
                         System.out.println("submitedCounts: "+ submitedCounts);

                         List result = ArrayListExample.removeDuplicate(submitedCounts);
                         System.out.println("去重>>> submitedCounts: "+ result);

                         System.out.println("===》》作业id: "+ task_id + " 已提交人数: "+ result.size());
                         Task thetask = taskService.getTask(task_id);
                         thetask.setPeopleNum(result.size());
                         taskService.addTask(thetask);

                 } else {
                     System.out.println("不存在的taskid提交: "+ task_id);
                     submissionService.deleteSubmission(task_id);
                 }
            }

        } else {
            for (int i = 0; i < tasks.size(); i++) {
                Task thetask = taskService.addTask(tasks.get(i));
                thetask.setPeopleNum(0);
                taskService.addTask(thetask);
            }
        }
        return "/student/list_submission";
    }

    @MyAnnotation("学生提交作业")
    @GetMapping("/newsubmit")
    public String submitTask(@RequestParam(value = "taskid", required = true) int taskid, Model model) {
        Submission submission = new Submission();
        model.addAttribute("submission", submission);
        Task task = taskService.getTask(taskid);
        model.addAttribute("task", task);
        return "student/submit_task";
    }

    @MyAnnotation("处理提交请求")
    @PostMapping("/addSubmitProcess")
    public String ProcessAddTaskForm(@ModelAttribute("task") Task task, @ModelAttribute("submission") Submission submission, RedirectAttributes redirectAttributes) throws ParseException {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String timeStamp = simpleDateFormat.format(new Date());
//        Date date = simpleDateFormat.parse(timeStamp);
//        System.out.println("Current Time Stamp: "+timeStamp + "###: "+ date);
//        submission.setSubmitTime(dateTime);  // mysql自动生成
        int tid = task.getId();
        submission.setTaskId(tid);
        submission.setTitle(task.getTitle());
        submission.setIscompleted(1);
        System.out.println(">>>提交内容: "+ submission.getContent());
        String currentUsername = SecUtils.getCurrentUsername();
        System.out.println("currentUser: "+ currentUsername);
        submission.setStudentName(currentUsername);
        System.out.println(">>>>>>>>>新增提交记录 submission###: "+ submission);

        submissionService.addSubmission(submission);
        // 重定向传参
        redirectAttributes.addAttribute("taskid", tid);
        redirectAttributes.addFlashAttribute("msg", "作业 "+ tid +" 已成功提交!");

        return "redirect:/hzfSpringmvcExam/student/submitTask";
    }

    @MyAnnotation("学生更新提交记录")
    @GetMapping("/editsubmit")
    public String showUpdateSubmitForm(@RequestParam("taskid") int taskid,@ModelAttribute("task") Task task, @ModelAttribute("submission") Submission submission, Model model) {
        Task thetask = taskService.getTask(taskid);
        model.addAttribute("task", thetask);
        String currentUsername = SecUtils.getCurrentUsername();
        List<Submission> thesubmission = submissionService.getSubsByTaskIdAndStuName(taskid, currentUsername);
        if (thesubmission.size()>0) {
            System.out.println("最近更新提交>>> "+ thesubmission.get(0));
            model.addAttribute("submission", thesubmission.get(0));
            return "/student/submit_task";
        } else {
            return "/student/edit_error";
        }
    }


    /* 删除什么？ 学生提交记录 */
    @MyAnnotation("学生删除提交记录")
    @GetMapping("/deletesubmit")
    public String deleteSubmission(@RequestParam("taskid") int taskid, RedirectAttributes ra) {
        String currentUsername = SecUtils.getCurrentUsername();
        List<Submission> thesubmissions = submissionService.getSubsByTaskIdAndStuName(taskid, currentUsername);
        if (thesubmissions.size()>0) {
            submissionService.delSubsByTaskIdAndStuName(taskid, currentUsername);
            ra.addFlashAttribute("del", "作业-"+ taskid +", 已成功删除!");
            return "redirect:/hzfSpringmvcExam/student/submitTask";
        } else {
            return "/student/edit_error";
        }
    }

}
