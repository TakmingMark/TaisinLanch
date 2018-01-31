package Excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.DARK_BLUE;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import Component.DayComponent;
import Component.FoodComponent;
import Component.IngredientComponent;
import Component.MenuDataComponent;
import Component.TextContent;

public class IngredientExcelModel extends ExcelModel {

	public void writeExcel(MenuDataComponent menuOutputData) {
		
		for (DayComponent day : menuOutputData.getDayArray()) {
			FileOutputStream fileOutputStream = null;
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
			HSSFSheet hssfSheet = hssfWorkbook.createSheet(ExcelTextContent.ingredientSheetName);

			String fileName = getFileName(day.getDate());
			
			String filePath = "excel/ingredient" + fileName + ".xls";
			Object[] columnNames = ExcelTextContent.ingredientColumnNames;

			Row row = hssfSheet.createRow(0);
			int columnNum = 0;
			for (Object leaveColumn : columnNames) {
				Cell cell = row.createCell(columnNum++);
				if (leaveColumn instanceof String)
					cell.setCellValue((String) leaveColumn);
				else
					cell.setCellValue("");
			}

			writeDayIngredientToExcel(hssfSheet, day);

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
	}

	private void writeDayIngredientToExcel(HSSFSheet hssfSheet, DayComponent dayElement) {
		int rowNum = 1;
		rowNum = writeFoodIngredientToExcel(hssfSheet, dayElement.getStapleFood(), dayElement, rowNum);
		rowNum = writeFoodIngredientToExcel(hssfSheet, dayElement.getMainCourse(), dayElement, rowNum);
		rowNum = writeFoodIngredientToExcel(hssfSheet, dayElement.getSideDishOne(), dayElement, rowNum);
		rowNum = writeFoodIngredientToExcel(hssfSheet, dayElement.getSideDishSecond(), dayElement, rowNum);
		rowNum = writeFoodIngredientToExcel(hssfSheet, dayElement.getSoup(), dayElement, rowNum);
	}

	private int writeFoodIngredientToExcel(HSSFSheet hssfSheet, FoodComponent food, DayComponent day,
			int rowNum) {
		Row row = null;

		for (IngredientComponent ingredient : food.getIngredientArray()) {
			row = hssfSheet.createRow(rowNum++);

			for (int i = 0; i < ExcelTextContent.ingredientColumnNames.length; i++) {
				Cell cell = row.createCell(i);
				switch (i) {
				case 0:
					cell.setCellValue(day.getDate());
					break;
				case 1:
					break;
				case 2:
					cell.setCellValue(food.getName());
					break;
				case 3:
					cell.setCellValue(ingredient.getName());
					break;
				case 4:
					cell.setCellValue(day.getParchaseDate());
					break;
				case 5:
				case 6:
					cell.setCellValue("");
					break;
				case 7:
					cell.setCellValue("1");
					break;
				case 8:
					cell.setCellValue("");
					break;
				case 9:
					cell.setCellValue(ExcelTextContent.ingredientSupplier);
					break;
				case 10:
				case 11:
				case 12:
					break;
				case 13:
					cell.setCellValue(ingredient.getUnit());
					break;
				case 14:
					cell.setCellValue(ExcelTextContent.ingredientO);
					break;
				case 15:
					cell.setCellValue(ExcelTextContent.ingredientP);
					break;
				case 16:
					cell.setCellValue(ExcelTextContent.ingredientQ);
					break;
				default:
					break;
				}
			}
		}
		return rowNum;
	}

	private String getFileName(String dayDate) {
		String fileName = backSlashToDot(dayDate);
		return fileName;
	}
}
