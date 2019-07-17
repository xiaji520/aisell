package cn.xiaji.domain;
//encoding: utf-8


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: Purchasebillitem.java
 */
/*

 */
@Entity//表示一个由jpa管理的持久对象,对应数据库的一个表
@Table(name = "purchasebillitem")//table数据库的表名
public class Purchasebillitem  extends BaseDomain{

    @Column(columnDefinition = "decimal(19,2)")
    private BigDecimal price;

    @Column(columnDefinition = "decimal(19,2)")
    private BigDecimal num;

    @Column(columnDefinition = "decimal(19,2)")
    private BigDecimal amount;

    private String descs;

    //组合关系,非空
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bill_id")
    @JsonIgnore //生成json的时候忽略这个属性
    private Purchasebill bill;

    //多对一,非空,产品
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    private Product product;


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    public Purchasebill getBill() {
        return bill;
    }

    public void setBill(Purchasebill bill) {
        this.bill = bill;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Purchasebillitem{" +
                "id=" + id +
                ", price=" + price +
                ", num=" + num +
                ", amount=" + amount +
                ", descs='" + descs + '\'' +
                '}';
    }
}