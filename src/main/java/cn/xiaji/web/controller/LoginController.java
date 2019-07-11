package cn.xiaji.web.controller;
//encoding: utf-8

import cn.xiaji.common.JsonResult;
import com.wf.captcha.Captcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.security.util.Password;

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
public class LoginController {

    private Font getFont() {
        Random random = new Random();
        Font font[] = new Font[6];
        font[0] = new Font("Ravie", Font.PLAIN, 32);
        font[1] = new Font("Antique Olive Compact", Font.PLAIN, 32);
        font[2] = new Font("Forte", Font.PLAIN, 32);
        font[3] = new Font("Wide Latin", Font.PLAIN, 32);
        font[4] = new Font("Gill Sans Ultra Bold", Font.PLAIN, 32);
        font[5] = new Font("Verdana", Font.PLAIN, 32);
        return font[random.nextInt(6)];
    }

    //验证码
    @RequestMapping("/images/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        /*
        https://github.com/whvcse/EasyCaptcha
        */
        // 设置请求头为输出图片类型
        CaptchaUtil.setHeader(response);

        // 三个参数分别为宽、高、位数
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 6);

        // 设置字体
        specCaptcha.setFont(getFont());

        // 设置类型，纯数字、纯字母、字母数字混合
        specCaptcha.setCharType(Captcha.TYPE_DEFAULT);

        // 验证码存入session
        request.getSession().setAttribute("captcha", specCaptcha.text().toLowerCase());

        // 生成的验证码
        String code = specCaptcha.text();
        System.out.println(code);

        // 输出图片流
        specCaptcha.out(response.getOutputStream());

    }

    /*@PostMapping("/login")
    public JsonResult login(String username, String password, String code){
        // 获取session中的验证码
        String sessionCode = request.getSession().getAttribute("captcha");
        // 判断验证码
        if (code==null || !sessionCode.equals(code.trim().toLowerCase())) {
            return JsonResult.error("验证码不正确");
        }
    }*/


    @RequestMapping("/login")
    public String login(HttpServletRequest request, String username, String password, String captcha) {
        // 获取session中的验证码
        String code = (String) request.getSession().getAttribute("captcha");
        //判断验证码是否正确
        if (code.equals(captcha.toLowerCase())) {
            System.out.println("验证码成功!"+username+"-----"+password);
            //1.拿到当前用户
            Subject subject = SecurityUtils.getSubject();
            //2.如果没有登录就让它登录
            if (!subject.isAuthenticated()) {
                UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                try {
                    subject.login(token);
                    return "redirect:/s/main.jsp";
                } catch (UnknownAccountException e) {
                    System.out.println("用户名错误!");
                    e.printStackTrace();
                } catch (IncorrectCredentialsException e) {
                    System.out.println("用户名或者密码错误!");
                    e.printStackTrace();
                } catch (AuthenticationException e) {
                    System.out.println("未知错误!");
                    e.printStackTrace();
                }
            }
            return "redirect:/s/login.jsp";
        } else {
            return "redirect:/s/login.jsp";
        }
    }

}