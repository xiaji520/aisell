package cn.xiaji.web.controller;
//encoding: utf-8

import cn.xiaji.domain.Department;
import cn.xiaji.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: UtilController.java
 */
/*
返回所有的下拉数据
 */
@Controller
@RequestMapping("/util")
public class UtilController {
    @Autowired
    private IDepartmentService departmentService;
    @RequestMapping("/dept")
    @ResponseBody
    public List<Department> findAllDepts(){
        return departmentService.findAll();
    }
}