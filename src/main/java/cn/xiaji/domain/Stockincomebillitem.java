package cn.xiaji.domain;
//encoding: utf-8


import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: Stockincomebillitem.java
 */
/*

 */
@Entity//表示一个由jpa管理的持久对象,对应数据库的一个表
@Table(name = "stockincomebillitem")//table数据库的表名
public class Stockincomebillitem extends BaseDomain{

    @Column(columnDefinition = "decimal(19,2)")
    private BigDecimal price;
    @Column(columnDefinition = "decimal(19,2)")
    private BigDecimal num;
    @Column(columnDefinition = "decimal(19,2)")
    private BigDecimal amount;
    private String descs;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id")
    private Stockincomebill bill;


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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Stockincomebill getBill() {
        return bill;
    }

    public void setBill(Stockincomebill bill) {
        this.bill = bill;
    }

    @Override
    public String toString() {
        return "Stockincomebillitem{" +
                "id=" + id +
                ", price=" + price +
                ", num=" + num +
                ", amount=" + amount +
                ", descs='" + descs + '\'' +
                '}';
    }
}