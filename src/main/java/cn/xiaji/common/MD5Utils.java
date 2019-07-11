package cn.xiaji.common;
//encoding: utf-8

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: MD5Utils.java
 */
/*

 */
public class MD5Utils {
    //加盐
    public static final String salt = "xiaji";
    //加密次数
    public static final int HASHITERATIONS = 1000;

    public static String createMD5Pwd(String str) {
        SimpleHash hash = new SimpleHash("MD5", str, salt, HASHITERATIONS);
        return hash.toHex();
    }
}