package com.example.nationaltax.utils;
import com.example.nationaltax.bean.User;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ExcelUtil {
    public static void exportUserExcel(List<User> userList, OutputStream outputStream) throws IOException {
        Workbook workbook = new XSSFWorkbook(); // 创建一个新的Excel文件
        Sheet sheet = workbook.createSheet("用户列表"); // 创建一个工作表

        // 创建表头
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("姓名");
        headerRow.createCell(2).setCellValue("生日");
        headerRow.createCell(3).setCellValue("部门");
        headerRow.createCell(4).setCellValue("工号");
        headerRow.createCell(5).setCellValue("性别");
        headerRow.createCell(6).setCellValue("电话");
        headerRow.createCell(7).setCellValue("邮箱");
        headerRow.createCell(8).setCellValue("备注");

        // 填充数据
        int rowNum = 1;
        for (User user : userList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(user.getName());
            row.createCell(2).setCellValue(user.getBirthday());
            row.createCell(3).setCellValue(user.getDept());
            row.createCell(4).setCellValue(user.getAccount());
            row.createCell(5).setCellValue(user.isGender() ? "男" : "女");
            row.createCell(6).setCellValue(user.getMobile());
            row.createCell(7).setCellValue(user.getEmail());
            row.createCell(8).setCellValue(user.getMemo());
        }

        // 设置列宽自适应
        for (int i = 0; i < sheet.getRow(0).getPhysicalNumberOfCells(); i++) {
            sheet.autoSizeColumn(i);
        }
        for (User user : userList) {
            System.out.println(user.getName());
            System.out.println(user.getId());
            System.out.println(user.getDept());
        }

        // 写入输出流
        workbook.write(outputStream);
    }

}