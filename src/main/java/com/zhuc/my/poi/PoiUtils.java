package com.zhuc.my.poi;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created by ZHUC on 2015/6/30.
 */
public class PoiUtils {

    /**
     * excel文件路径获取Workbook
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbook(String path) throws IOException {
        Workbook workbook = null;
        if (path.endsWith(".xlsx")) {
            workbook = new XSSFWorkbook(new FileInputStream(path));
        } else if (path.endsWith(".xls")) {
            workbook = new HSSFWorkbook(new FileInputStream(path));
        }
        return workbook;
    }

    /**
     * 根据文件后缀名和输入流获取Workbook
     *
     * @param fileName
     * @param in
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbook(String fileName, InputStream in) throws IOException {
        Workbook workbook = null;
        if (fileName.endsWith(".xlsx")) {
            workbook = new XSSFWorkbook(in);
        } else if (fileName.endsWith(".xls")) {
            workbook = new HSSFWorkbook(in);
        }
        return workbook;
    }

    /**
     * @param path	excel文件完整路径
     * @return
     * @throws IOException
     */
    public static List<Object[]> importExcel(String path) throws IOException {
        Workbook workbook = getWorkbook(path);
        return importExcel(workbook);
    }

    /**
     * @param fileName
     *            文件名
     * @param in
     *            输入流
     * @return
     * @throws IOException
     */
    public static List<Object[]> importExcel(String fileName, InputStream in) throws IOException {
        Workbook workbook = getWorkbook(fileName, in);
        return importExcel(workbook);
    }

    /**
     * @param workbook
     * @return
     * @throws IOException
     */
    public static List<Object[]> importExcel(Workbook workbook) throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();

        Sheet sheet = workbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i <= lastRowNum; i++) { // 第1行为title，从第2行开始遍历
            Row row = sheet.getRow(i);
            if (null == row) {
                continue;
            }
            int lastCellNum = row.getLastCellNum();
            Object[] objs = new Object[lastCellNum];
            for (int j = 0; j < lastCellNum; j++) { // 第1列开始遍历
                Object temp = "";
                Cell cell = row.getCell(j);
                if (cell == null) {
                    objs[j] = temp;
                    continue;
                }
                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    temp = (long) cell.getNumericCellValue(); // double型
                    if (DateUtil.isCellDateFormatted(cell)) {
                        temp = DateUtil.getJavaDate((Long) temp);
                    }
                } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                    temp = cell.getStringCellValue();
                }
                objs[j] = temp;
            }
            list.add(objs);
        }

        return list;
    }

}