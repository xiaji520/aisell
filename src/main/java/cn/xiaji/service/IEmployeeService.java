package cn.xiaji.service;

import cn.xiaji.domain.Employee;

public interface IEmployeeService extends IBaseService<Employee, Long> {
    Boolean checkUsername(String username);

    //根据用户名拿到对应的员工
    Employee findByUsername(String username);

}
