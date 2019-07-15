package cn.xiaji.common;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import static org.junit.Assert.*;

public class ExcelTest {

    @Test
    public void createExcelTest() throws Exception {
        //在内存创建一个Excel文件
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        //在Excel文件创建一张表
        Sheet sheet = workbook.createSheet("乘法表");
        //创建行
        for (int i = 1; i <= 9; i++) {
            Row row = sheet.createRow(i);
            //创建列
            for (int j = 1; j <= i; j++) {
                Cell cell = row.createCell(j);
                //列里面装入数据
                cell.setCellValue(i + "*" + j + "=" + (i * j));
            }
        }

        //从内存中写出来
        FileOutputStream out = new FileOutputStream("99.xlsx");
        workbook.write(out);
        out.close();
    }

    @Test
    public void readExcelTest() throws Exception {
        //获取Excel文件
        Workbook workbook = WorkbookFactory.create(new FileInputStream("99.xlsx"));
        //获取第一张表
        Sheet sheet = workbook.getSheetAt(0);
        //获取相应的行
        //确定多少行
        int lastRowNum = sheet.getLastRowNum();
        //遍历拿到多少行
        for (int i = 1; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            //获取这一行的所有列
            //确定多少个单元格数据
            short lastCellNum = row.getLastCellNum();
            //遍历拿到单元格
            for (int j = 1; j <= lastCellNum; j++) {
                //拿到单元格的值
                Cell cell = row.getCell(j);
                if (cell != null) {
                    String stringCellValue = cell.getStringCellValue();
                    System.out.println(stringCellValue + " ");
                }
            }
            System.out.println();
        }
    }

}