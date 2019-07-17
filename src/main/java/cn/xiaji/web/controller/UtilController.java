package cn.xiaji.web.controller;
//encoding: utf-8

import cn.xiaji.common.UserContext;
import cn.xiaji.domain.*;
import cn.xiaji.service.*;
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
    @Autowired
    private ISupplierService supplierService;
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IProductService productService;

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

    //返回所有的供应商
    @RequestMapping("/findAllSupplier")
    @ResponseBody
    public List<Supplier> findAllSupplier() {
        return supplierService.findAll();
    }

    //返回采购员
    @RequestMapping("/findBuyer")
    @ResponseBody
    public List<Employee> findBuyer() {
        return employeeService.findBuyer();
    }

    //返回采购员
    @RequestMapping("/findProduct")
    @ResponseBody
    public List<Product> findProduct() {
        return productService.findAll();
    }


}
