package cn.xiaji.poi;
//encoding: utf-8

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.Date;

/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: POIEmployee.java
 */
/*

 */
public class POIEmployee {
    private Long id;

    @Excel(name = "用户名")
    private String username;

    @Excel(name = "邮箱", width = 20)
    private String email;

    @Excel(name = "性别", replace = {"男_true", "女_false"})
    private Boolean sex = true;

    @Excel(name = "出生日期", format = "yyyy-MM-dd HH:mm:ss")
    private Date bornDate = new Date();

    @Excel(name = "头像", type = 2, width = 10, height = 20)
    private String headImage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }
}