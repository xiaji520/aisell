package cn.xiaji.realm;
//encoding: utf-8

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: MyRealm.java
 */
/*
自定义realm
 */
public class MyRealm extends AuthorizingRealm {

    //获取到这个Realm的名称(随便取)
    @Override
    public String getName() {
        return "myRealm";
    }

    //根据用户名拿到角色
    public Set<String> getRoles(String sername) {
        Set<String> roles = new HashSet<String>();
        roles.add("admin");
        roles.add("root");
        return roles;
    }

    //根据用户名拿到权限
    public Set<String> getPermissions(String sername) {
        Set<String> perms = new HashSet<String>();
        perms.add("employee:*");
        //perms.add("employee:update");
        return perms;
    }

    //进行授权判断(权限)
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //1.拿到当前登录的用户
        String username = (String) principalCollection.getPrimaryPrincipal();
        //2.根据登录用户拿到角色与权限
        Set<String> roles = getRoles(username);
        Set<String> permissions = getPermissions(username);
        //3.创建返回的权限对象
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //把角色放到权限对象中去
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }


    //登录验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //AuthorizationInfo会自动判断密码是否错误
        //1.拿令牌(拿对应的用户名)
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        //2.根据用户名取拿密码
        String password = getByName(username);
        if (password == null) {
            //返回null为:用户名错误
            return null;
        }
        //获取到盐值
        ByteSource salt = ByteSource.Util.bytes("xiaji");
        //getName():只是随便起的名
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, salt, getName());
        return authenticationInfo;
    }

    public String getByName(String username) {
        if ("admin".equals(username)) {
            return "b38a1c1bd230c80ed8974ab2a3e18d6b";
        } else if ("root".equals(username)) {
            return "123456";
        }
        return null;
    }
}