package cn.xiaji.domain;
//encoding: utf-8


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: Stockincomebill.java
 */
/*

 */
@Entity//表示一个由jpa管理的持久对象,对应数据库的一个表
@Table(name = "stockincomebill")//table数据库的表名
public class Stockincomebill extends BaseDomain{

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
    @JoinColumn(name = "keeper_id")
    private Employee keeper;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "depot_id")
    private Depot depot;


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

    public Employee getKeeper() {
        return keeper;
    }

    public void setKeeper(Employee keeper) {
        this.keeper = keeper;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    @Override
    public String toString() {
        return "Stockincomebill{" +
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