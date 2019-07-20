package cn.xiaji.web.controller;
//encoding: utf-8

import cn.xiaji.common.JsonResult;
import cn.xiaji.common.MD5Utils;
import cn.xiaji.common.UserContext;
import cn.xiaji.domain.Employee;
import cn.xiaji.repository.EmployeeRepository;
import cn.xiaji.service.IEmployeeService;
import com.wf.captcha.Captcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.Random;

/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: LoginController.java
 */
/*

 */
@Controller
public class RegisterController {
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String index() {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult register(HttpServletRequest request, String username, String password, String repassword, String captcha) {
        // 获取session中的验证码
        String code = (String) request.getSession().getAttribute("captcha");
        //判断验证码是否正确
        if (code.equals(captcha.toLowerCase())) {
            System.out.println("register验证码成功!");
            //判断两次密码是否一致
            if (!password.equals(repassword)) {
                return new JsonResult(false, "两次密码不一致!");
            } else if (password.isEmpty() || username.isEmpty()) {
                //判断账号和密码是否为空
                return new JsonResult(false, "账号或密码不能为空!");
            } else if (!employeeService.checkUsername(username)) {
                //判断用户名是否存在
                return new JsonResult(false, "用户名已存在!");
            } else {
                Employee employee = new Employee();
                employee.setUsername(username);
                //密码加盐
                String pwd = MD5Utils.createMD5Pwd(password);
                employee.setPassword(pwd);
                employee.setIsdelete(1);
                Employee save = employeeRepository.save(employee);
            }
            return new JsonResult();
        }
        return new JsonResult(false, "验证码错误!");
    }

}