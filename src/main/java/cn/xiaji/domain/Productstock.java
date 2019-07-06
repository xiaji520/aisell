package cn.xiaji.domain;
//encoding: utf-8


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: Productstock.java
 */
/*

 */
@Entity//表示一个由jpa管理的持久对象,对应数据库的一个表
@Table(name = "productstock")//table数据库的表名
public class Productstock extends BaseDomain{

    @Column(columnDefinition = "decimal(19,2)")
    private BigDecimal num;
    @Column(columnDefinition = "decimal(19,2)")
    private BigDecimal amount;
    @Column(columnDefinition = "decimal(19,2)")
    private BigDecimal price;
    private Date incomeDate;
    private Boolean warning;
    @Column(columnDefinition = "decimal(19,2)")
    private BigDecimal topNum;
    @Column(columnDefinition = "decimal(19,2)")
    private BigDecimal bottomNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "depot_id")
    private Depot depot;


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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(Date incomeDate) {
        this.incomeDate = incomeDate;
    }

    public Boolean getWarning() {
        return warning;
    }

    public void setWarning(Boolean warning) {
        this.warning = warning;
    }

    public BigDecimal getTopNum() {
        return topNum;
    }

    public void setTopNum(BigDecimal topNum) {
        this.topNum = topNum;
    }

    public BigDecimal getBottomNum() {
        return bottomNum;
    }

    public void setBottomNum(BigDecimal bottomNum) {
        this.bottomNum = bottomNum;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    @Override
    public String toString() {
        return "Productstock{" +
                "id=" + id +
                ", num=" + num +
                ", amount=" + amount +
                ", price=" + price +
                ", incomeDate=" + incomeDate +
                ", warning=" + warning +
                ", topNum=" + topNum +
                ", bottomNum=" + bottomNum +
                '}';
    }
}