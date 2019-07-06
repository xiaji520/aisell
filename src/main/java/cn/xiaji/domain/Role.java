package cn.xiaji.domain;
//encoding: utf-8


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: Role.java
 */
/*

 */
@Entity//表示一个由jpa管理的持久对象,对应数据库的一个表
@Table(name = "role")//table数据库的表名
public class Role extends BaseDomain {

    private String name;
    private String sn;

    @ManyToMany
    @JoinTable(
            name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private List<Permission> permissions = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "role_menu",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id")
    )
    private List<Menu> menus = new ArrayList<>();



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sn='" + sn + '\'' +
                '}';
    }
}