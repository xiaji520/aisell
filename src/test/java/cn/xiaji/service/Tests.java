package cn.xiaji.service;

import cn.xiaji.common.MD5Utils;
import cn.xiaji.domain.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;

/*
要是报错: HV000183: Unable to load 'javax.el.ExpressionFactory'.
 <!--报错导入-->
        <dependency>
            <groupId>javax.el</groupId>
            <artifactId>javax.el-api</artifactId>
            <version>2.2.4</version>
        </dependency>
        <dependency>
            <groupId>org.glassfish.web</groupId>
            <artifactId>javax.el</artifactId>
            <version>2.2.4</version>
        </dependency>
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Tests {
    @Autowired
    private IEmployeeService employeeService;

    @Test
    public void testAdd() throws Exception {
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            int i1 = random.nextInt(100);
            String username = "user" + i1;
            System.out.println(username);
            Employee employee = new Employee();
            if (employeeService.checkUsername(username)) {
                employee.setUsername(username);
                employee.setIsdelete(1);
                employee.setPassword(MD5Utils.createMD5Pwd(username));
                employeeService.save(employee);
            }
        }
    }
}