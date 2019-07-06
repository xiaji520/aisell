package cn.xiaji.common;
//encoding: utf-8

import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: UIPage.java
 */
/*
    返回easyUI需要的分页格式
 */
public class UIPage<T> {
    //总条数
    private Long total;
    //当前页 数据
    private List<T> rows;

    public UIPage() {
    }

    public UIPage(Page page) {
        this.total = page.getTotalElements();
        this.rows = page.getContent();
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}