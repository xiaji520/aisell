package cn.xiaji.poi;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class POIEmployeeTest {
    @Test
    public void test() throws Exception {
        List<POIEmployee> list = new ArrayList<>();
        POIEmployee poi = new POIEmployee();
        poi.setId(1L);
        poi.setUsername("老污龟");
        poi.setEmail("1233455@qq.com");
        poi.setSex(true);
        poi.setHeadImage("1.jpg");
        POIEmployee poi2 = new POIEmployee();
        poi2.setId(1L);
        poi2.setUsername("污龟");
        poi2.setEmail("14343@qq.com");
        poi2.setSex(false);
        poi2.setHeadImage("2.jpg");
        list.add(poi);
        list.add(poi2);

        Workbook sheets = ExcelExportUtil.exportExcel(new ExportParams(),
                POIEmployee.class, list);

        //将文件创建出来
        FileOutputStream out = new FileOutputStream("employee.xls");
        sheets.write(out);
        out.close();

    }

}