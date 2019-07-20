package cn.xiaji.domain;
//encoding: utf-8


import cn.afterturn.easypoi.excel.annotation.Excel;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: Employee.java
 */
/*

 */
@Entity//表示一个由jpa管理的持久对象,对应数据库的一个表
@Table(name = "employee")//table数据库的表名
public class Employee extends BaseDomain {
    @Excel(name = "用户名")
    @NotNull(message = "用户名不能为空!")
    private String username;

    private String password;

    @Excel(name = "邮箱", width = 25)
    private String email;

    @Excel(name = "头像", type = 2, width = 10, height = 20)
    private String headImage;

    @Excel(name = "年龄")
    @Max(value = 100, message = "年龄不能超过100岁!")
    @Min(value = 0, message = "年龄不能小于0岁!")
    @Column(length = 11)
    private Integer age;
    @Column(length = 1)
    private Integer isdelete;

    @ManyToMany
    @JoinTable(
            name = "employee_role",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", headImage='" + headImage + '\'' +
                ", age=" + age +
                ", isdelete=" + isdelete +
              /*  ", roles=" + roles +*/
                ", id=" + id +
                '}';
    }
}