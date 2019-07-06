package cn.xiaji.domain;
//encoding: utf-8


import javax.persistence.*;

/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: Producttype.java
 */
/*

 */
@Entity//表示一个由jpa管理的持久对象,对应数据库的一个表
@Table(name = "producttype")//table数据库的表名
public class Producttype extends BaseDomain {

    private String name;
    private String descs;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Producttype parent;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    public Producttype getParent() {
        return parent;
    }

    public void setParent(Producttype parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Producttype{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", descs='" + descs + '\'' +
                '}';
    }
}