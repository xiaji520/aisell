package cn.xiaji.service.Impl;
//encoding: utf-8

import cn.xiaji.common.MD5Utils;
import cn.xiaji.domain.Employee;
import cn.xiaji.repository.EmployeeRepository;
import cn.xiaji.service.IEmployeeService;
import com.hazelcast.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    //验证邮箱是否存在 存在返回false
    @Override
    public Boolean checkEmail(String email) {
        return !(employeeRepository.getCountByEmail(email) > 0);
    }

    @Override
    public Employee findByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }

    @Override
    public List<Employee> findBuyer() {
        return employeeRepository.findByDepartment_Name("采购部");
    }

    //添加或更新 密码加密
    @Override
    @Transactional
    public void save(Employee employee) {
        if (employee.getId() == null) {
            employee.setPassword(MD5Utils.createMD5Pwd(employee.getPassword()));
        }
        super.save(employee);
    }
}