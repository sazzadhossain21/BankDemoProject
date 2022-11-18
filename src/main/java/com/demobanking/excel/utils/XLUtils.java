package com.demobanking.excel.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {
	public static FileInputStream xlfile;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static FileOutputStream fos;

	public static int getRowCount(String filelocation, String xlsheet) throws IOException {

		xlfile = new FileInputStream(filelocation);
		wb = new XSSFWorkbook(xlfile);
		ws = wb.getSheet(xlsheet);
		int rowCount = ws.getLastRowNum();
		wb.close();
		xlfile.close();
		return rowCount;

	}

	public static int getCellCount(String filelocation, String xlsheet, int rownum) throws IOException {

		xlfile = new FileInputStream(filelocation);
		wb = new XSSFWorkbook(xlfile);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		int cellCount = row.getLastCellNum();
		wb.close();
		xlfile.close();
		return cellCount;

	}

	public static String getCellData(String filelocation, String xlsheet, int rownum, int cellnum) throws IOException {

		xlfile = new FileInputStream(filelocation);
		wb = new XSSFWorkbook(xlfile);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.getCell(cellnum);
		String data;
		try {
			DataFormatter df = new DataFormatter();
			String cellData = df.formatCellValue(cell);
			return cellData;
		} catch (Exception e) {
			data = "";
		}
		wb.close();
		xlfile.close();
		return data;
	}

	public static void setCellData(String filelocation, String xlsheet, int rownum, int colnum, String data)
			throws IOException {

		xlfile = new FileInputStream(filelocation);
		wb = new XSSFWorkbook(xlfile);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.createCell(colnum);
		cell.setCellValue(data);
		fos = new FileOutputStream(filelocation);
		wb.write(fos);
		wb.close();
		xlfile.close();
		fos.close();
	}
}
