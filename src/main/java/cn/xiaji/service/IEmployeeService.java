package cn.xiaji.service;

import cn.xiaji.domain.Employee;

public interface IEmployeeService extends IBaseService<Employee, Long> {
    Boolean checkUsername(String username);
}
