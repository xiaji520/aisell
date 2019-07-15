package cn.xiaji.web.controller;
//encoding: utf-8

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.xiaji.common.JsonResult;
import cn.xiaji.common.UIPage;
import cn.xiaji.domain.Employee;
import cn.xiaji.query.EmployeeQuery;
import cn.xiaji.service.IEmployeeService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    public UIPage<Employee> page(EmployeeQuery query, String cmd) {
        if ("_isdelete_".equals(cmd)) {
            query.setIsdelete(0);
        }
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
    public JsonResult delete(Long id, String cmd) {
        Employee employee = employeeService.findOne(id);
        if ("_rec_".equals(cmd)) {
            employee.setIsdelete(1);
        } else {
            employee.setIsdelete(0);
        }
        try {
            employeeService.save(employee);
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

    //下载Excel文件
    @RequestMapping("/download")
    public String download(ModelMap map, HttpServletRequest request, EmployeeQuery query) {
        List<Employee> list = employeeService.findByQuery(query);
        //获得头像真实路径
        String realPath = request.getSession().getServletContext().getRealPath("");
        list.forEach(e -> {
            e.setHeadImage(realPath + e.getHeadImage());
        });
        ExportParams params = new ExportParams("员工数据", "测试", ExcelType.XSSF);
        //冻结相应的行
        //params.setFreezeCol(2);
        // 数据集合
        map.put(NormalExcelConstants.DATA_LIST, list);
        //导出实体
        map.put(NormalExcelConstants.CLASS, Employee.class);
        //参数
        map.put(NormalExcelConstants.PARAMS, params);
        //文件名称
        map.put(NormalExcelConstants.FILE_NAME, "employee");
        //View名称
        return NormalExcelConstants.EASYPOI_EXCEL_VIEW;
    }


}