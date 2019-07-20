package cn.xiaji.web.controller;
//encoding: utf-8

import cn.xiaji.common.JsonResult;
import cn.xiaji.common.MD5Utils;
import cn.xiaji.common.SendEmailUtil;
import cn.xiaji.domain.Employee;
import cn.xiaji.repository.EmployeeRepository;
import cn.xiaji.service.IEmployeeService;
import com.wf.captcha.SpecCaptcha;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Base64;


/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: LoginController.java
 */
/*

 */
@Controller
public class ForgetPwdController {
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(value = "/forgetPwd", method = RequestMethod.GET)
    public String index() {
        return "forgetPwd";
    }

    @RequestMapping(value = "/forgetPwd", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult register(HttpServletRequest request, String email, String captcha) throws UnsupportedEncodingException {
        // 获取session中的验证码
        String code = (String) request.getSession().getAttribute("captcha");
        //判断验证码是否正确
        if (code.equals(captcha.toLowerCase())) {
            System.out.println("forgetPwd验证码成功!");
            //判断邮箱是否为空
            if (email.isEmpty()) {
                return new JsonResult(false, "邮箱不能为空!");
            } else if (employeeService.checkEmail(email)) {
                //判断邮箱否存在
                return new JsonResult(false, "邮箱不存在!");
            } else {
                //获取username
                Employee email1 = employeeRepository.queryByEmail(email);
                String name = email1.getUsername();
                //编码
                byte[] bytes = name.getBytes("UTF-8");
                Base64.Encoder encoder = Base64.getEncoder();
                String encodedText = encoder.encodeToString(bytes);
                byte[] bytes1 = encodedText.getBytes("UTF-8");
                String string = encoder.encodeToString(bytes1);
                System.out.println("string------" + string);
                //拼接url
                String url = "http://localhost/setPwd?username=" + string;
               /* //解码
                Base64.Decoder decoder = Base64.getDecoder();
                System.out.println(new String(decoder.decode(encodedText), "UTF-8"));*/
                //邮箱验证
                try {
                    SendEmailUtil.sendEmail(url, email);
                } catch (EmailException e) {
                    e.printStackTrace();
                    return new JsonResult(false, e.getMessage());
                }
                return new JsonResult();
            }
        }
        return new JsonResult(false, "验证码错误!");
    }


    @RequestMapping(value = "/setPwd", method = RequestMethod.GET)
    public String setPwd(String username, HttpServletRequest request) throws Exception {
        request.getSession().setAttribute("username", username);
        return "setPwd";
    }

    @RequestMapping(value = "/setPwd", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult setPwd(HttpServletRequest request, String username, String password, String repassword, String captcha) throws UnsupportedEncodingException {
        //解码 http://localhost/setPwd?username=YWRtaW4=
        Base64.Decoder decoder = Base64.getDecoder();
        String user = new String(decoder.decode(username), "UTF-8");
        String s = new String(decoder.decode(user), "UTF-8");
        System.out.println("s------" + s);
        // 获取session中的验证码
        String code = (String) request.getSession().getAttribute("captcha");
        //判断验证码是否正确
        if (code.equals(captcha.toLowerCase())) {
            System.out.println("验证码正确!");
            if (!password.equals(repassword)) {
                return new JsonResult(false, "两次密码不一致!");
            } else if (password.isEmpty()) {
                //判断密码是否为空
                return new JsonResult(false, "密码不能为空!");
            } else if (employeeService.checkUsername(s)) {
                return new JsonResult(false, "用户不存在!");
            } else {
                Employee employee = employeeService.findByUsername(s);
                //密码加盐
                String pwd = MD5Utils.createMD5Pwd(password);
                employee.setPassword(pwd);
                employeeRepository.save(employee);
                return new JsonResult();
            }
        }
        return new JsonResult(false, "验证码错误!");
    }
}