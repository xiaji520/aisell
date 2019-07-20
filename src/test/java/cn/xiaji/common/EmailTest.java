package cn.xiaji.common;


import org.apache.commons.mail.HtmlEmail;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class EmailTest {
    @Test
    public void test() throws Exception {
        HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.qq.com");
        email.setCharset("utf-8");
        //收件人
        email.addTo("83954439@qq.com");
        //发送人的邮箱为自己的，用户名可以随便填
        email.setFrom("my_captcha@qq.com", "captcha");
        //xpbdezjghjrwfgbi  设置发送人到的邮箱和用户名和授权码(授权码是自己设)
        email.setAuthentication("my_captcha@qq.com", "xpbdezjghjrwfgbi");
        //设置发送主题
        email.setSubject("验证码");
        //设置发送内容
        //email.setMsg("这是您找回密码的验证码:" + 1234 + ",请勿泄露!");
        email.setMsg("这是您找回密码的验证码:" + "杨许" + ",请勿泄露!");
        //进行发送
        email.send();
    }
}