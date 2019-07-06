package cn.xiaji.domain;
//encoding: utf-8


import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: Product.java
 */
/*

 */
@Entity//表示一个由jpa管理的持久对象,对应数据库的一个表
@Table(name = "product")//table数据库的表名
public class Product extends BaseDomain  {

    private String name;
    private String color;
    private String pic;
    private String smallPic;
    @Column(columnDefinition = "decimal(19,2)")
    private BigDecimal costPrice;
    @Column(columnDefinition = "decimal(19,2)")
    private BigDecimal salePrice;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "types_id")
    private Producttype types;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    private Systemdictionarydetail unit;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Systemdictionarydetail brand;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getSmallPic() {
        return smallPic;
    }

    public void setSmallPic(String smallPic) {
        this.smallPic = smallPic;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public Producttype getTypes() {
        return types;
    }

    public void setTypes(Producttype types) {
        this.types = types;
    }

    public Systemdictionarydetail getUnit() {
        return unit;
    }

    public void setUnit(Systemdictionarydetail unit) {
        this.unit = unit;
    }

    public Systemdictionarydetail getBrand() {
        return brand;
    }

    public void setBrand(Systemdictionarydetail brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", pic='" + pic + '\'' +
                ", smallPic='" + smallPic + '\'' +
                ", costPrice=" + costPrice +
                ", salePrice=" + salePrice +
                ", id=" + id +
                '}';
    }
}