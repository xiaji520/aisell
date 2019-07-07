package cn.xiaji.web.controller;
//encoding: utf-8

import cn.xiaji.common.JsonResult;
import cn.xiaji.common.UIPage;
import cn.xiaji.domain.Employee;
import cn.xiaji.query.EmployeeQuery;
import cn.xiaji.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    //@ModelAttribute在执行方法前,先执行这个方法
    @ModelAttribute("editEmployee")
    public Employee beforeEdit(Long id, String cmd) {
        if (id != null && "_upd_".equals(cmd)) {
            Employee one = employeeService.findOne(id);
            //解决n-to-n的问题
            one.setDepartment(null);
            return one;
        }
        return null;
    }

    //删除
    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        try {
            employeeService.delete(id);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
    }

    //修改
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@ModelAttribute("editEmployee") Employee employee) {
        try {
            employeeService.save(employee);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
    }

    //保存
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(Employee employee) {
        try {
            employeeService.save(employee);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
    }

    //验证用户名
    @RequestMapping("/checkUsername")
    @ResponseBody
    public Boolean checkUsername(Long id, String username) {
        //通过id是否为null来判断是修改还是增加
        if (id != null) {
            //根据id查询对象
            Employee dbEmp = employeeService.findOne(id);
            //判断名称是否相等
            if (username.equals(dbEmp.getUsername())) {
                //4.如果相等，直接返回true
                return true;
            }
        }
        return employeeService.checkUsername(username);
    }


}