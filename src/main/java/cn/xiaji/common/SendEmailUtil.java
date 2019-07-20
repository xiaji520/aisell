package cn.xiaji.common;
//encoding: utf-8

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.util.Date;

/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: SendEmailUtil.java
 */
/*

 */
public class SendEmailUtil {


    public static void sendEmail(String url, String email) throws EmailException {
        HtmlEmail send = new HtmlEmail();
        send.setHostName("smtp.qq.com");
        send.setCharset("utf-8");
        //收件人
        send.addTo(email);
        //发送人的邮箱为自己的，用户名可以随便填
        send.setFrom("my_captcha@qq.com", "captcha");
        //xpbdezjghjrwfgbi  设置发送人到的邮箱和用户名和授权码(授权码是自己设)
        send.setAuthentication("my_captcha@qq.com", "xpbdezjghjrwfgbi");
        //设置发送主题
        send.setSubject("验证码");
        //设置发送内容
        send.setMsg("这是您找回密码的连接: " + url + "\n请勿泄露!----" + "时间:" + new Date());
        //进行发送
        send.send();
    }

}