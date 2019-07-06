package cn.xiaji.domain;
//encoding: utf-8


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: Department.java
 */
/*

 */
@Entity//表示一个由jpa管理的持久对象,对应数据库的一个表
@Table(name = "purchasebill")//table数据库的表名
public class Purchasebill extends BaseDomain{

    private Date vdate;
    @Column(columnDefinition = "decimal(19,2)")
    private BigDecimal totalAmount;
    @Column(columnDefinition = "decimal(19,2)")
    private BigDecimal totalNum;
    private Date inputTime;
    private Date auditorTime;
    @Column(length = 11)
    private Integer status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auditor_id")
    private Employee auditor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inputUser_id")
    private Employee inputUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id")
    private Employee buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;



    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(BigDecimal totalNum) {
        this.totalNum = totalNum;
    }

    public Date getInputTime() {
        return inputTime;
    }

    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
    }

    public Date getAuditorTime() {
        return auditorTime;
    }

    public void setAuditorTime(Date auditorTime) {
        this.auditorTime = auditorTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Employee getAuditor() {
        return auditor;
    }

    public void setAuditor(Employee auditor) {
        this.auditor = auditor;
    }

    public Employee getInputUser() {
        return inputUser;
    }

    public void setInputUser(Employee inputUser) {
        this.inputUser = inputUser;
    }

    public Employee getBuyer() {
        return buyer;
    }

    public void setBuyer(Employee buyer) {
        this.buyer = buyer;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "Purchasebill{" +
                "id=" + id +
                ", vdate=" + vdate +
                ", totalAmount=" + totalAmount +
                ", totalNum=" + totalNum +
                ", inputTime=" + inputTime +
                ", auditorTime=" + auditorTime +
                ", status=" + status +
                '}';
    }
}