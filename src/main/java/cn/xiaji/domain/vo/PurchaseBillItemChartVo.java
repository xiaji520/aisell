package cn.xiaji.domain.vo;
//encoding: utf-8

/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: PurchaseBillItemChartVo.java
 */
/*

 */
public class PurchaseBillItemChartVo {
    private Object name;
    private Object y;

    public PurchaseBillItemChartVo() {
    }

    public PurchaseBillItemChartVo(Object name, Object y) {
        this.name = name;
        this.y = y;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getY() {
        return y;
    }

    public void setY(Object y) {
        this.y = y;
    }
}