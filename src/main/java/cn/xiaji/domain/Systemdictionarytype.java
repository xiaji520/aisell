package cn.xiaji.domain;
//encoding: utf-8


import javax.persistence.*;

/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: Systemdictionarytype.java
 */
/*

 */
@Entity//表示一个由jpa管理的持久对象,对应数据库的一个表
@Table(name = "systemdictionarytype")//table数据库的表名
public class Systemdictionarytype extends BaseDomain {

    public static final String PRODUCT_BRAND = "productBrand";//品牌sn
    public static final String PRODUCT_UNIT = "productUnit";//单位sn

    /*@JoinColumn(unique = true)*/
    private String sn;
    private String name;


    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Systemdictionarytype{" +
                "id=" + id +
                ", sn='" + sn + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}