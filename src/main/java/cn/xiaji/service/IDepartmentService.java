package cn.xiaji.service;

import cn.xiaji.domain.Department;

public interface IDepartmentService extends IBaseService<Department,Long>{
    //根据名称拿到部门对象
    Department findByName(String name);
}
