package cn.xiaji.web.controller;
//encoding: utf-8

import com.wf.captcha.Captcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.Random;

/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: MainController.java
 */
/*

 */
@Controller
public class MainController extends BaseController {

    @RequestMapping("/main")
    public String Main() {
        return "main";
    }

}