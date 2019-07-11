package cn.xiaji.service;

import cn.xiaji.common.MD5Utils;
import cn.xiaji.domain.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EmployeeServiceTest {
    @Autowired
    private IEmployeeService employeeService;

    @Test
    public void testFindAll() throws Exception {
        employeeService.findAll().forEach(e -> System.out.println(e));
    }

    @Test
    public void testPwd() throws Exception {
        List<Employee> employees = employeeService.findAll();
        for (Employee employee : employees) {
            employee.setPassword(MD5Utils.createMD5Pwd(employee.getUsername()));
            employeeService.save(employee);
        }
    }
}