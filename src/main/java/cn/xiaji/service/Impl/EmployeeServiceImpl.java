package cn.xiaji.service.Impl;
//encoding: utf-8

import cn.xiaji.domain.Employee;
import cn.xiaji.repository.EmployeeRepository;
import cn.xiaji.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: EmployeeServiceImpl.java
 */
/*

 */
@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee, Long> implements IEmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    //验证用户名是否存在
    @Override
    public Boolean checkUsername(String username) {
        return !(employeeRepository.getCountByUsername(username) > 0);
    }
}