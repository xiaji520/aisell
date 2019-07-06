package cn.xiaji.query;
//encoding: utf-8

import cn.xiaji.domain.Department;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: DepartmentQuery.java
 */
/*

 */
public class DepartmentQuery extends BaseQuery {

    private String name;

    //where username = ?
    @Override
    public Specification createSpec() {
        Specification<Department> spec = Specifications.<Department>and()
                .like(StringUtils.isNotBlank(this.name), "name", "%" + this.name + "%")
                .build();
        return spec;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}