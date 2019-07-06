package cn.xiaji.domain;
//encoding: utf-8


import javax.persistence.*;
/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: Employee.java
 */
/*

 */
@MappedSuperclass//让其他子类知道是父类
public class BaseDomain {
    @Id//对应表的主键
    @GeneratedValue//自动增长
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}