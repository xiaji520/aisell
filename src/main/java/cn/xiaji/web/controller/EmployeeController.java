package cn.xiaji.web.controller;
//encoding: utf-8

import cn.xiaji.common.UIPage;
import cn.xiaji.domain.Employee;
import cn.xiaji.query.EmployeeQuery;
import cn.xiaji.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: EmployeeController.java
 */
/*

 */
@Controller
@RequestMapping("/employee")
public class EmployeeController extends BaseController {
    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping("/index")
    public String index() {
        return "employee/employee";
    }

    @RequestMapping("/page")
    @ResponseBody
    public UIPage<Employee> page(EmployeeQuery query) {
        Page page = employeeService.findPageByQuery(query);
        return new UIPage(page);
    }

}