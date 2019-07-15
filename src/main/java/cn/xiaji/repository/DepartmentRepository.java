package cn.xiaji.repository;

import cn.xiaji.domain.Department;

public interface DepartmentRepository extends BaseRepository<Department, Long> {
    //根据名称拿到部门对象
    Department findByName(String name);
}
