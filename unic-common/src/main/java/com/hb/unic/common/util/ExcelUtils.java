package com.hb.unic.common.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 解析excel
     * 
     * @param file
     *            excel文件
     * @return 结果
     */
    public static List<Map<String, String>> parse(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (!originalFilename.endsWith(XLS) && !originalFilename.endsWith(XLSX)) {
            throw new IllegalArgumentException("Excel file type is error");
        }
        if (file.getSize() == 0) {
            throw new IllegalArgumentException("Excel file is empty");
        }
        InputStream inputStream = null;
        Workbook wb = null;
        try {
            inputStream = file.getInputStream();
            if (originalFilename.endsWith(XLS)) {
                wb = new HSSFWorkbook(inputStream);
            } else {
                wb = new XSSFWorkbook(inputStream);
            }
            Sheet sheet = wb.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            int firstCellNum = (int)headerRow.getFirstCellNum();
            int lastCellNum = (int)headerRow.getLastCellNum();
            List<Map<String, String>> list = new ArrayList<>();
            for (int i = sheet.getFirstRowNum() + 1; i < sheet.getLastRowNum(); i++) {
                Map<String, String> map = new HashMap<>();
                Row row = sheet.getRow(i);
                if (rowNotNull(row, firstCellNum, lastCellNum)) {
                    for (int j = firstCellNum; j < lastCellNum; j++) {
                        map.put(getStringCellValue(headerRow.getCell(j)), getStringCellValue(row.getCell(j)));
                    }
                    list.add(map);
                }
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 判断行是否为空
     * 
     * @param row
     *            行
     * @param firstCellNum
     *            单元格开始位置
     * @param lastCellNum
     *            单元格结束位置
     * @return 结果
     */
    private static boolean rowNotNull(Row row, int firstCellNum, int lastCellNum) {
        if (row == null) {
            return false;
        }
        for (int j = firstCellNum; j < lastCellNum; j++) {
            if (row.getCell(j) != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取单元格的字符串值
     * 
     * @param cell
     *            单元格
     * @return 字符串
     */
    private static String getStringCellValue(Cell cell) {
        String cellValue = null;
        switch (cell.getCellType()) {
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case NUMERIC:
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            default:
                cellValue = cell.getStringCellValue();
                break;
        }
        return cellValue;
    }

}
