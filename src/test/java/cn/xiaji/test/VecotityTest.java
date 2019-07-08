package cn.xiaji.test;

import cn.xiaji.domain.Employee;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;

import java.io.FileFilter;
import java.io.FileWriter;
import java.io.StringWriter;

public class VecotityTest {

    //测试默认就是项目根目录
    //数据 + 模板 = 输入文本
    @Test
    public void testHello() throws Exception {
        //创建一个Velocity引擎
        VelocityEngine ve = new VelocityEngine();
        //获到到相应的模板
        Template template = ve.getTemplate("template/hello.vm", "UTF-8");
        //创建模板上下文(装数据的东西)
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("username", "二狗");
        //准备输出的位置
        //StringWriter writer = new StringWriter();
        FileWriter writer = new FileWriter("template/h.html");
        //数据 + 模板 = 输入文本
        template.merge(velocityContext, writer);
        writer.close();
    }

    @Test
    public void testHelloFile() throws Exception {
        //创建一个Velocity引擎
        VelocityEngine ve = new VelocityEngine();
        //获到到相应的模板
        Template template = ve.getTemplate("template/hello.vm", "UTF-8");
        //创建模板上下文(装数据的东西)
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("username", "二狗");
        Employee employee = new Employee();
        employee.setUsername("ergou");
        employee.setAge(18);
        velocityContext.put("employee",employee);
        //准备输出的位置
        FileWriter writer = new FileWriter("template/h.html");
        //数据 + 模板 = 输入文本
        template.merge(velocityContext, writer);
        writer.close();
    }
}
