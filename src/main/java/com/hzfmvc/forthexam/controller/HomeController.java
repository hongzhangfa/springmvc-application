package com.hzfmvc.forthexam.controller;

import com.hzfmvc.forthexam.aop.MyAnnotation;
import com.hzfmvc.forthexam.dao.LoggerService;
import com.hzfmvc.forthexam.dao.UserDao;
import com.hzfmvc.forthexam.entity.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @Slf4j: 用作日志输出的，一般会在项目每个类的开头加入该注解.
 * 省去 private final Logger logger = LoggerFactory.getLogger(当前类名.class);
 */

@Controller
@RequestMapping("/hzfSpringmvcExam")
public class HomeController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private LoggerService loggerService;

    //   @GetMapping({"/","/index","/index.html"})
    @MyAnnotation("用户登录")
    @RequestMapping("/signin")
    public String IndexController() {
        return "showMyLoginPage";
    }

    @MyAnnotation("查看日志")
    @RequestMapping("/admin/showLogs")
    public String getLoggerList(Model model) {
        List<Logger> loggerList = loggerService.getAllLogs();
        model.addAttribute("loggerList", loggerList);
        return "/admin/logger";
    }

    @MyAnnotation("更新密码")
    @RequestMapping(value = "/updatepwd", method = RequestMethod.POST)
    public String updatePassword() {
        return "update_pwd";
    }

    @MyAnnotation("处理更新密码请求")
    @RequestMapping(value = "/updatepwdProcess", method = RequestMethod.POST)
    public String changePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newpassword, Model model, RedirectAttributes re) {
//        String currentUsername = SecUtils.getCurrentUsername();
        PasswordEncoder pwEncoder = new BCryptPasswordEncoder();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String currentUsername = userDetails.getUsername();
        System.out.println("当前用户: "+ currentUsername);

        String currentUserPwd = userDetails.getPassword();
        System.out.println("当前用户密码: "+ currentUserPwd);
        System.out.println("新密码: "+ newpassword);
        System.out.println("旧密码加密: "+ pwEncoder.encode(oldPassword));

        String encodePwd = "";
        if(pwEncoder.matches(oldPassword, currentUserPwd)) {
            encodePwd = pwEncoder.encode(newpassword);
            System.out.println("Bcrypt新密码: "+ encodePwd);
            // Modifying queries can only use void or int/Integer as return type!
            int modifyCount = userDao.updatePwdByName(encodePwd, currentUsername);
            System.out.println("更改数据条数: "+ modifyCount);
            re.addFlashAttribute("success","密码修改成功密码!");

        } else {
            re.addFlashAttribute("msg","您输入的原始密码错误!");
        }
        // SecurityContextHolder.clearContext();//终止当前认证用户
        return "redirect:/";
    }

    /**
     *
     * @Data：注解在类上；提供类所有属性的 getting 和 setting 方法，此外还提供了equals、canEqual、hashCode、toString 方法
     * @Setter：注解在属性上；为属性提供 setting 方法
     * @Getter：注解在属性上；为属性提供 getting 方法
     * @SneakyThrows：无需在签名处显式抛出异常
     * @Log4j：注解在类上；为类提供一个 属性名为log 的 log4j 日志对象
     * @Slf4j: 同上
     * @NoArgsConstructor：注解在类上；为类提供一个无参的构造方法
     * @AllArgsConstructor：注解在类上；为类提供一个全参的构造方法
     * https://blog.51cto.com/u_3664660/3215111
     *
     */

}
