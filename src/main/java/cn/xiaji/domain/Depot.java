package cn.xiaji.domain;
//encoding: utf-8


import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: Depot.java
 */
/*

 */
@Entity//表示一个由jpa管理的持久对象,对应数据库的一个表
@Table(name = "depot")//table数据库的表名
public class Depot extends BaseDomain {

    private String name;
    @Column(columnDefinition = "decimal(19,2)")
    private BigDecimal maxCapacity;
    @Column(columnDefinition = "decimal(19,2)")
    private BigDecimal currentCapacity;
    @Column(columnDefinition = "decimal(19,2)")
    private BigDecimal totalAmount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(BigDecimal maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public BigDecimal getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(BigDecimal currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Depot{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maxCapacity=" + maxCapacity +
                ", currentCapacity=" + currentCapacity +
                ", totalAmount=" + totalAmount +
                '}';
    }
}