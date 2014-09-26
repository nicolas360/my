package com.zhuc.my.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class PoiTest {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws InvalidFormatException 
	 */
	public static void main(String[] args) throws IOException, InvalidFormatException {
		Workbook workbook = WorkbookFactory.create(new FileInputStream("D:\\1.xlsx"));
		Sheet sheet = workbook.getSheetAt(0);
		for (Row row : sheet) {
			for (Cell cell : row) {
				if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					System.out.print(cell.getNumericCellValue() + "  ");
				} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
					System.out.print(cell.getStringCellValue() + "  ");
				}

			}
			System.out.println();
		}

	}

}
