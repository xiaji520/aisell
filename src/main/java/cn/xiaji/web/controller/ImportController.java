package cn.xiaji.web.controller;
//encoding: utf-8

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import cn.xiaji.common.EmployeeExcelVerifyHandler;
import cn.xiaji.domain.Department;
import cn.xiaji.domain.Employee;
import cn.xiaji.service.IDepartmentService;
import cn.xiaji.service.IEmployeeService;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @author: xj
 * @contact: xiaruji520@gmail.com
 * @file: ImportController.java
 */
/*

 */
@Controller
@RequestMapping("/import")
public class ImportController {
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private EmployeeExcelVerifyHandler excelVerifyHandler;

    @RequestMapping("/index")
    public String index() {
        return "import";
    }

    @RequestMapping("/empxlsx")
    public String empxlsx(MultipartFile empFile, HttpServletResponse response) throws Exception {
       /*
        System.out.println(empFile);
        System.out.println(empFile.getName());  //empFile:上传控件名称
        System.out.println(empFile.getOriginalFilename()); //emp.xlsx:上传的文件名称
        System.out.println(empFile.getSize()); //文件大小
        System.out.println(empFile.getContentType());//文件的memi类型
        */
        InputStream inputStream = empFile.getInputStream();
        //准备一些导入的参数
        ImportParams params = new ImportParams();
        //要求导入的时候做验证
        params.setNeedVerfiy(true);
        //设置自定义的验证处理器
        params.setVerifyHandler(excelVerifyHandler);
        //params.setTitleRows(1);
        //params.setHeadRows(1);
        //把上传的excel文件中的数据变成Employee数据
        ExcelImportResult<Employee> result = ExcelImportUtil.importExcelMore(
                inputStream,
                Employee.class, params);
        //获取到引入成功的数据
        List<Employee> list = result.getList();
        //把员工进行保存
        list.forEach(e -> {
            //设置一个默认密码
            e.setPassword("123456");
            //设置默认为不删除
            e.setIsdelete(1);
            //根据名称到数据库中拿到部门
            Department department = e.getDepartment();
            if (department != null) {
                Department dbDept = departmentService.findByName(department.getName());
                e.setDepartment(dbDept);
            }
            employeeService.save(e);
            System.out.println(e);
        });
        //获到到失败的数据 -> 导出到error.xlsx
        if (result.isVerfiyFail()) {
            //错误的文件
            Workbook wb = result.getFailWorkbook();
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); //mime类型
            response.setHeader("Content-disposition", "attachment;filename=error.xlsx");
            response.setHeader("Pragma", "No-cache");//设置不要缓存
            OutputStream ouputStream = response.getOutputStream();
            wb.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        }
        return "import";
    }

}