package cn.xiaji.web.shiro;
//encoding: utf-8

import cn.xiaji.domain.Employee;
import cn.xiaji.service.IEmployeeService;
import cn.xiaji.service.IPermissionService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: AISellRealm.java
 */
/*

 */
public class AISellRealm extends AuthorizingRealm {

    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IPermissionService permissionService;

    @Override
    public String getName() {
        return "aiSellRealm";
    }

    //进行授权判断(权限)
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("11111111111111111111");
        //1.拿到当前登录的用户
        Employee employee = (Employee) principalCollection.getPrimaryPrincipal();
        //2.根据登录用户拿到角色与权限
        // Set<String> roles = getRoles(employee.getUsername());
        //Set<String> permissions = getPermissions(employee.getUsername());
        Set<String> permissions = permissionService.findPermissionByUser(employee.getId());
        //3.创建返回的权限对象
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //把角色放到权限对象中去
        //authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    //根据用户名拿到角色
    public Set<String> getRoles(String username) {
        Set<String> roles = new HashSet<String>();
        roles.add("admin");
        roles.add("root");
        return roles;
    }

    //根据用户名拿到权限
    public Set<String> getPermissions(String username) {
        Set<String> perms = new HashSet<String>();
        perms.add("employee:*");
        /* perms.add("dept:index");*/
        return perms;
    }

    //登录验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1.拿令牌(拿对应的用户名)
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //2.根据用户名取拿密码
        String username = token.getUsername();
        Employee loginUser = employeeService.findByUsername(username);
        if (loginUser == null) {
            //返回null为:用户名错误
            return null;
        }
        //获取到盐值
        ByteSource salt = ByteSource.Util.bytes("xiaji");
        //getName():只是随便起的名
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(loginUser, loginUser.getPassword(), salt, getName());
        return authenticationInfo;
    }

    public String getByName(String username) {
        if ("admin".equals(username)) {
            //密码xiaji:86c764f2ac206b2060f82c0f06d519f9
            return "86c764f2ac206b2060f82c0f06d519f9";
        } else if ("root".equals(username)) {
            return "123456";
        }
        return null;
    }


}