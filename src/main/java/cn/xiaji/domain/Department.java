package cn.xiaji.domain;
//encoding: utf-8


import cn.afterturn.easypoi.excel.annotation.Excel;

import javax.persistence.*;

/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: Department.java
 */
/*

 */
@Entity//表示一个由jpa管理的持久对象,对应数据库的一个表
@Table(name = "department")//table数据库的表名
public class Department extends BaseDomain {
    @Excel(name = "部门名称")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}