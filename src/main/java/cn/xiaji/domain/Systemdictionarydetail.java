package cn.xiaji.domain;
//encoding: utf-8


import javax.persistence.*;

/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: Systemdictionarydetail.java
 */
/*

 */
@Entity//表示一个由jpa管理的持久对象,对应数据库的一个表
@Table(name = "systemdictionarydetail")//table数据库的表名
public class Systemdictionarydetail extends BaseDomain{

    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "types_id")
    private Systemdictionarytype types;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Systemdictionarytype getTypes() {
        return types;
    }

    public void setTypes(Systemdictionarytype types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return "Systemdictionarydetail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}