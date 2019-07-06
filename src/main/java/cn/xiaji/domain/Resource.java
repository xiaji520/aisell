package cn.xiaji.domain;
//encoding: utf-8


import javax.persistence.*;

/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: Resource.java
 */
/*

 */
@Entity//表示一个由jpa管理的持久对象,对应数据库的一个表
@Table(name = "resource")//table数据库的表名
public class Resource extends BaseDomain {

    @Column(length = 50)
    private String name;
    @Column(length = 50)
    private String url;
    private String descs;



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

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", descs='" + descs + '\'' +
                '}';
    }
}