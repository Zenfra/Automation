package com.zenfra.automation.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;

public class ExcelDataUtils {

	String excelFileName;
	int counter = 0;

	public ExcelDataUtils(String excelFileName) {
		this.excelFileName = excelFileName;
	}

	public void createExcelFile() {
		try (FileOutputStream outputStream = new FileOutputStream("ExcelReport\\"+excelFileName)) {
			try (HSSFWorkbook workbook = new HSSFWorkbook()) {
				HSSFSheet sheet = workbook.createSheet("TestResults");
				Row header = sheet.createRow(0);
				header.createCell(0).setCellValue("Sr_No");
				header.createCell(1).setCellValue("Scenario");
				header.createCell(2).setCellValue("Status");
				header.createCell(3).setCellValue("Start_Time");
				header.createCell(4).setCellValue("End_Time");
				header.createCell(5).setCellValue("Execution_Time");
				workbook.write(outputStream);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getCounterValue() {
		return counter;
	}

	public void incrementCounter() {
		counter++;
	}

	public void insertResult(String testCaseName, int testStatus, String testStartTime, String testEndTime,
			long executionTime) {
		incrementCounter();

		try {
			Connection connection = new Fillo().getConnection("ExcelReport\\"+excelFileName);
			connection.executeUpdate(
					"INSERT INTO TestResults(Sr_No,Scenario,Status,Start_Time,End_Time,Execution_Time) VALUES('"
							+ getCounterValue() + "','" + testCaseName + "','"
							+ (testStatus == 1 ? "PASS" : "FAIL/SKIPPED") + "','" + testStartTime + "','" + testEndTime
							+ "','" + executionTime + "')");
		} catch (FilloException e) {
			e.printStackTrace();
		}
	}

}
