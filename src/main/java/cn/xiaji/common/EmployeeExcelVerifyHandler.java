package cn.xiaji.common;
//encoding: utf-8

import cn.afterturn.easypoi.excel.entity.result.ExcelVerifyHandlerResult;
import cn.afterturn.easypoi.handler.inter.IExcelVerifyHandler;
import cn.xiaji.domain.Employee;
import cn.xiaji.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: EmployeeExcelVerifyHandler.java
 */
/*

 */
@Component
public class EmployeeExcelVerifyHandler implements IExcelVerifyHandler<Employee> {
    @Autowired
    private IEmployeeService employeeService;

    @Override
    public ExcelVerifyHandlerResult verifyHandler(Employee employee) {
        ExcelVerifyHandlerResult result = new ExcelVerifyHandlerResult(true);
        //拿到用户名
        String username = employee.getUsername();
        if (!employeeService.checkUsername(username)) {
            result.setMsg("用户名已经存在!");
            result.setSuccess(false);
        }
        return result;

    }
}