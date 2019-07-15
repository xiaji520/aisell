package cn.xiaji.service.Impl;
//encoding: utf-8

import cn.xiaji.domain.Department;
import cn.xiaji.repository.DepartmentRepository;
import cn.xiaji.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: DepartmentServiceImpl.java
 */
/*

 */
@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department, Long> implements IDepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department findByName(String name) {
        return departmentRepository.findByName(name);
    }
}