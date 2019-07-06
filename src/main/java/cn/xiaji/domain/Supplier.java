package cn.xiaji.domain;
//encoding: utf-8


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: Supplier.java
 */
/*

 */
@Entity//表示一个由jpa管理的持久对象,对应数据库的一个表
@Table(name = "supplier")//table数据库的表名
public class Supplier extends BaseDomain{

    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}