package cn.xiaji.query;
//encoding: utf-8


import cn.xiaji.domain.Employee;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: EmployeeQuery.java
 */
/*

 */
public class EmployeeQuery extends BaseQuery {
    private String username;
    private String email;
    private Integer age;
    //查询部门支持
    private Long departmentId;

    //返回查询条件
    @Override
    public Specification createSpec() {
        Specification<Employee> build = Specifications.<Employee>and()
                .like(StringUtils.isNotBlank(username), "username", "%" + username + "%")
                .like(StringUtils.isNotBlank(email), "email", "%" + email + "%")
                .gt(age != null, "age", age)
                //查询部门支持
                .eq(departmentId != null, "department.id", departmentId)
                .build();
        return build;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}