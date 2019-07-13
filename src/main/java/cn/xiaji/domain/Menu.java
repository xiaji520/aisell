package cn.xiaji.domain;
//encoding: utf-8


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: Menu.java
 */
/*

 */
@Entity//表示一个由jpa管理的持久对象,对应数据库的一个表
@Table(name = "menu")//table数据库的表名
public class Menu extends BaseDomain {

    private String name;
    private String url;
    private String icon;

    //通过多方找一方 子菜单找父菜单
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonIgnore //这里生成json的时候要忽略，否则会造成功能相互调用
    private Menu parent;

    //Transient：临时属性(JPA不管这个属性，和数据库没有关系)
    @Transient
    private List<Menu> children = new ArrayList<>();

    //ajax需要
    public String getText() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}