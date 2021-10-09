package com.hb.unic.common.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;

/**
 * excel工具类
 *
 * @version v0.1, 2021/10/9 20:08, create by huangbiao.
 */
public class ExcelUtils {

    /**
     * 文件类型
     */
    public static final String XLS = ".xls";

    /**
     * 文件类型
     */
    public static final String XLSX = ".xlsx";

    /**
     * 导出excel
     * 
     * @param response
     *            HttpServletResponse
     * @param fullName
     *            文件完整名称
     * @param headers
     *            表头
     * @param rows
     *            数据
     */
    public static void export(HttpServletResponse response, String fullName, List<String> headers,
        List<List<String>> rows) {

        Workbook wb = null;
        // 创建一个webbook，对应一个Excel文件
        if (fullName.endsWith(XLS)) {
            wb = new HSSFWorkbook();
        } else if (fullName.endsWith(XLSX)) {
            wb = new XSSFWorkbook();
        } else {
            throw new IllegalArgumentException("Excel filename is error");
        }

        if (CollectionUtils.isEmpty(headers)) {
            throw new IllegalArgumentException("Please set headers");
        }

        // 在webbook中添加一个sheet，对应Excel文件中的sheet
        Sheet sheet = wb.createSheet("Sheet1");

        // 处理表头
        Row headerRow = sheet.createRow(0);
        Font headerFont = wb.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short)16);
        CellStyle headerCellStyle = wb.createCellStyle();
        headerCellStyle.setFont(headerFont);
        for (int i = 0; i < headers.size(); i++) {
            sheet.setColumnWidth(i, 20 * 256);
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers.get(i));
            cell.setCellType(CellType.STRING);
            cell.setCellStyle(headerCellStyle);
        }

        // 处理数据
        Font rowFont = wb.createFont();
        rowFont.setFontHeightInPoints((short)12);
        CellStyle rowCellStyle = wb.createCellStyle();
        rowCellStyle.setFont(rowFont);
        if (!CollectionUtils.isEmpty(rows)) {
            for (int i = 0; i < rows.size(); i++) {
                Row row = sheet.createRow(i + 1);
                List<String> rowData = rows.get(i);
                for (int j = 0; j < rowData.size(); j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(rowData.get(j));
                    cell.setCellType(CellType.STRING);
                    cell.setCellStyle(rowCellStyle);
                }
            }
        }

        // 导出excel
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fullName);
        try (OutputStream outputStream = response.getOutputStream()) {
            wb.write(outputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
