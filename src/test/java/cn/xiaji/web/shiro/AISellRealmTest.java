package cn.xiaji.web.shiro;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

import static org.junit.Assert.*;

public class AISellRealmTest {
    @Test
    public void test() throws Exception {
        //密码xiaji:86c764f2ac206b2060f82c0f06d519f9
        SimpleHash hash = new SimpleHash("MD5", "xiaji", "xiaji", 1000);
        System.out.println(hash);
    }

}