package cn.xiaji.query;
//encoding: utf-8


import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: BaseQuery.java
 */
/*

 */
public abstract class BaseQuery {
    //当前页
    private int currentPage = 1;
    //每页条数
    private int pageSize = 1;

    //排序字段名
    private String orderName;

    //排序规则
    private String orderType = "ASC";

    //写个抽象方法去规范子类获取Specification对象的名称 必需叫createSpec
    public abstract Specification createSpec();

    public Sort createSort() {
        if (StringUtils.isNotBlank(orderName)) {
            //toUpperCase()转为大写
            return new Sort(Sort.Direction.valueOf(orderType.toUpperCase()), orderName);
        }
        return null;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    //第一页 从0开始算的
    public int getJpaCurrentPage() {
        return currentPage - 1;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    //兼容easyUI
    public void setPage(int page) {
        this.currentPage = page;
    }

    public void setRows(int rows) {
        this.pageSize = rows;
    }

    public void setSort(String sort) {
        this.orderName = sort;
    }
    public void setOrder(String order) {
        this.orderName = order;
    }
}