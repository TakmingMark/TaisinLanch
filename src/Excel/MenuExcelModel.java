package Excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import Component.DayComponent;
import Component.MenuDataComponent;
import Component.TextContent;

public class MenuExcelModel extends ExcelModel {

	public void writeExcel(MenuDataComponent menuOutputData) {
		FileOutputStream fileOutputStream = null;
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		HSSFSheet hssfSheet = hssfWorkbook.createSheet(ExcelTextContent.menuSheetName);
		Random random = new Random();
		
		String fileName=getFileName(menuOutputData.getDate());
		String filePath = "excel/menu" + fileName + ".xls";
		String[] columnNames = ExcelTextContent.menuColumnNames;

		int rowNum = 0;
		int columnNum = 0;
	
		Row row = hssfSheet.createRow(rowNum++);
		for (String leaveColumn : columnNames) {
			Cell cell = row.createCell(columnNum++);
			cell.setCellValue(leaveColumn);
		}

		for (DayComponent day : menuOutputData.getDayArray()) {
			row = hssfSheet.createRow(rowNum++);
			columnNum = 0;
			for (int i = 0; i < columnNames.length; i++) {
				Cell cell = row.createCell(columnNum++);
				switch (i) {
				case 0:
					cell.setCellValue(menuOutputData.getSchoolName());
					break;
				case 1:
					cell.setCellValue(calculateMenuDayDate(menuOutputData.getDate(), day.getName()));
					break;
				case 2:
					cell.setCellValue(day.getStapleFood().getName());
					break;
				case 3:
					cell.setCellValue("");
					break;
				case 4:
					cell.setCellValue(day.getMainCourse().getName());
					break;
				case 5:
				case 6:
				case 7:
					cell.setCellValue("");
					break;
				case 8:
					cell.setCellValue(day.getSideDishOne().getName());
					break;
				case 9:
					cell.setCellValue(day.getSideDishSecond().getName());
					break;
				case 10:
				case 11:
				case 12:
				case 13:
				case 14:
					cell.setCellValue("");
					break;
				case 15:
					cell.setCellValue(day.getSoup().getName());
					break;
				case 16:
				case 17:
					cell.setCellValue("");
					break;
				case 18:
					cell.setCellValue(String.valueOf((random.nextInt((int) ((4.6 - 4.1) * 10 + 1)) + 4.1 * 10) / 10.0));
					break;
				case 19:
				case 20:
					cell.setCellValue(String.valueOf((random.nextInt((int) ((3.0 - 2.5) * 10 + 1)) + 2.5 * 10) / 10.0));
					;
					break;
				case 21:
					cell.setCellValue(String.valueOf((random.nextInt((int) ((1.6 - 1.0) * 10 + 1)) + 1.0 * 10) / 10.0));
					break;
				case 22:
				case 23:
					cell.setCellValue("0");
					break;
				case 24:
					cell.setCellValue(String.valueOf((random.nextInt((int) ((799 - 700) * 10 + 1)) + 700 * 10) / 10.0));
					break;
				default:
					break;
				}

			}
		}

		try {
			fileOutputStream = new FileOutputStream(filePath);
			hssfWorkbook.write(fileOutputStream);
			hssfWorkbook.close();
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String getFileName(String targetDate) {
		String fileName = calculateMenuDayDate(targetDate, "星期一") + "-"
				+ calculateMenuDayDate(targetDate, "星期五");
		fileName = backSlashToDot(fileName);
		return fileName;
	}
}
