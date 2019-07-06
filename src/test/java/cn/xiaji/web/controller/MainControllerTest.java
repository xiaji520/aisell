package cn.xiaji.web.controller;

import com.wf.captcha.Captcha;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Random;

import static org.junit.Assert.*;

public class MainControllerTest {
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

    @Test
    public void test() throws Exception {
        OutputStream outputStream = new FileOutputStream(new File("D:/Java_Web/aisell/src/main/webapp/images/captcha/" + new Date().getTime() + ".png"));

        // 三个参数分别为宽、高、位数
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 6);

        // 设置字体
        specCaptcha.setFont(getFont());

        // 设置类型，纯数字、纯字母、字母数字混合
        specCaptcha.setCharType(Captcha.TYPE_DEFAULT);

        // 生成的验证码
        String code = specCaptcha.text();
        System.out.println(code);

        // 输出图片流
        specCaptcha.out(outputStream);

    }

}
