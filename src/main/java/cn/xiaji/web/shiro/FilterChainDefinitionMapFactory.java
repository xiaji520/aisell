package cn.xiaji.web.shiro;
//encoding: utf-8

import cn.xiaji.domain.Permission;
import cn.xiaji.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: FilterChainDefinitionMapFactory.java
 */
/*

 */
public class FilterChainDefinitionMapFactory {
    @Autowired
    private IPermissionService permissionService;

    public Map<String, String> builderFilterChainDefinitionMap() {
        Map<String, String> maps = new LinkedHashMap<>();
        //maps中加数据
        //设置放行
        maps.put("/s/*", "anon");
        maps.put("/login", "anon");
        maps.put("/register", "anon");
        maps.put("/forgetPwd", "anon");
        maps.put("/setPwd", "anon");
        maps.put("/easyui/**", "anon");
        maps.put("/js/**", "anon");
        maps.put("/json/**", "anon");
        maps.put("/css/**", "anon");
        maps.put("*.js", "anon");
        maps.put("*.css", "anon");
        maps.put("/images/**", "anon");
        //设置权限拦截
        /* maps.put("/employee/index", "perms[employee:index]");
        maps.put("/dept/index", "perms[dept:index]");*/
        //从数据库拿到数据，放到Map中
        List<Permission> permissions = permissionService.findAll();
        for (Permission permission : permissions) {
            //资源
            String url = permission.getUrl();
            //权限
            String sn = permission.getSn();
            //把路径与资源放到拦截中去
            //maps.put(url, "perms[" + sn + "]");
            //改为自定义的
            maps.put(url, "aiSellPerms[" + sn + "]");
        }
        //设置拦截所有
        maps.put("/**", "authc");
        return maps;
    }
}