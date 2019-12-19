package com.slyang.test.excel;

import cn.hutool.poi.excel.RowUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

/**
 * TestExcel Style
 */
public class TestExcel {

    @Test
    public void helloStyle() throws IOException {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        Row headRow = RowUtil.getOrCreateRow(sheet, 0);
        cn.hutool.poi.excel.cell.CellUtil.mergingCells(sheet,0,1,0,10,null);
        Cell headCell = cn.hutool.poi.excel.cell.CellUtil.getOrCreateCell(headRow,0);
        headCell.setCellValue("wo shi tou");

        XSSFCellStyle titleStyle = (XSSFCellStyle) workbook.createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER); // 水平对齐
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 垂直对齐
        Font titleFont = workbook.createFont();
        titleFont.setFontHeightInPoints((short) 22);
        titleFont.setBold(true);
        titleFont.setFontName("微软雅黑");
        titleStyle.setFont(titleFont);

        headCell.setCellStyle(titleStyle);

        Row tetle = RowUtil.getOrCreateRow(sheet, 2);
        for(int i = 0 ;i<=10;i++){
            XSSFCellStyle tetleStyle = (XSSFCellStyle) workbook.createCellStyle();
            tetleStyle.setAlignment(HorizontalAlignment.CENTER); // 水平对齐
            tetleStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 垂直对齐
            // 背景色
            XSSFColor tetleStyleColor = new XSSFColor(java.awt.Color.pink);
            tetleStyle.setFillForegroundColor(tetleStyleColor);
            tetleStyle.setFillPattern(FillPatternType.FINE_DOTS);
            // 边框色
            tetleStyle.setBorderRight(BorderStyle.THIN);
            tetleStyle.setRightBorderColor(new XSSFColor(java.awt.Color.lightGray));

            Cell itemCell = CellUtil.createCell(tetle,i, String.valueOf(i),tetleStyle);
            if(i==5){
                itemCell.setCellValue(new Date().toString());
            }
        }

        OutputStream outputStream = new FileOutputStream("/Users/slyang/openSourceProject/java/java_base/hello.xlsx");
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();

    }


}
