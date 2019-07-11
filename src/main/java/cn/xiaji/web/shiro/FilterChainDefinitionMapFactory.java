package cn.xiaji.web.shiro;
//encoding: utf-8

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: FilterChainDefinitionMapFactory.java
 */
/*

 */
public class FilterChainDefinitionMapFactory {
    public Map<String, String> builderFilterChainDefinitionMap() {
        Map<String, String> maps = new LinkedHashMap<>();
        //maps中加数据
        //设置放行
        maps.put("/s/*", "anon");
        maps.put("/login", "anon");
        maps.put("/images/captcha", "anon");
        //设置权限拦截
        maps.put("/employee/index", "perms[employee:index]");
        maps.put("/dept/index", "perms[dept:index]");
        //设置拦截所有
        maps.put("/**", "authc");
        return maps;
    }
}