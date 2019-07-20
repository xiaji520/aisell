package cn.xiaji.domain.vo;
//encoding: utf-8

import cn.xiaji.domain.Purchasebillitem;
import cn.xiaji.query.PurchasebillitemQuery;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: PurchaseBillItemVo.java
 */
/*

 */
public class PurchaseBillItemVo {
    private Long id; //编号
    private String supplierName; //供应商
    private String buyerName;//采购员
    private String productName; //产品
    private String productTypeName; //产品类型
    private Date vdate;//日期
    private BigDecimal price;//单价
    private BigDecimal num;//数量
    private BigDecimal amount;//小计
    private Integer status; //状态

    //分组字段
    private String groupField;

    //把一个Purchasebillitem对象变成vo对象
    public PurchaseBillItemVo(Purchasebillitem item, PurchasebillitemQuery query) {
        this.id = item.getId();
        this.supplierName = item.getBill().getSupplier().getName();
        this.buyerName = item.getBill().getBuyer().getUsername();
        this.productName = item.getProduct().getName();
        this.productTypeName = item.getProduct().getTypes().getName();
        this.vdate = item.getBill().getVdate();
        this.price = item.getPrice();
        this.num = item.getNum();
        this.amount = item.getAmount();
        this.status = item.getBill().getStatus();

        Integer groupBy = query.getGroupBy();
        if (groupBy == 1) { //采购员分组
            this.groupField = buyerName;
        } else if (groupBy == 2) {//月份
            Calendar cal = Calendar.getInstance();
            cal.setTime(vdate);
            String month = (cal.get(Calendar.MONTH) + 1) + "";
            this.groupField = month;
        } else {
            //供应商分组
            this.groupField = supplierName;
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getGroupField() {
        return groupField;
    }

    public void setGroupField(String groupField) {
        this.groupField = groupField;
    }
}