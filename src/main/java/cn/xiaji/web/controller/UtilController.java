package cn.xiaji.web.controller;
//encoding: utf-8

import cn.xiaji.common.UserContext;
import cn.xiaji.domain.Department;
import cn.xiaji.domain.Employee;
import cn.xiaji.domain.Producttype;
import cn.xiaji.domain.Systemdictionarydetail;
import cn.xiaji.service.IDepartmentService;
import cn.xiaji.service.IMenuService;
import cn.xiaji.service.IProducttypeService;
import cn.xiaji.service.ISystemdictionarydetailService;
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
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IProducttypeService producttypeService;
    @Autowired
    private ISystemdictionarydetailService systemdictionarydetailService;

    @RequestMapping("/dept")
    @ResponseBody
    public List<Department> findAllDepts() {
        return departmentService.findAll();
    }

    //拿类型
    @RequestMapping("/types")
    @ResponseBody
    public List<Producttype> findAllTypes() {
        return producttypeService.findAllTypes();
    }

    //拿单位
    @RequestMapping("/unit")
    @ResponseBody
    public List<Systemdictionarydetail> findAllUnit() {
        return systemdictionarydetailService.findAllUnit();
    }

    //拿品牌
    @RequestMapping("/brand")
    @ResponseBody
    public List<Systemdictionarydetail> findAllBrand() {
        return systemdictionarydetailService.findAllBrand();
    }

    //菜单栏
    @RequestMapping("/loginUserMenus")
    @ResponseBody
    public List loginUserMenus() {
        return menuService.findByUser();
    }

}
