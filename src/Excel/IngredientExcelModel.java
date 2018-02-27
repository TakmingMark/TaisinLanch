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

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Component.DayComponent;
import Component.FoodComponent;
import Component.IngredientComponent;
import Component.MenuDataComponent;
import Component.TextContent;

public class IngredientExcelModel extends ExcelModel {

	public void writeExcel(MenuDataComponent menuOutputData) {
		
		for (DayComponent day : menuOutputData.getDayArray()) {
			FileOutputStream fileOutputStream = null;
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
			XSSFSheet xssfSheet = xssfWorkbook.createSheet(ExcelTextContent.ingredientSheetName);

			String fileName = getFileName(day.getDate());
			
			String filePath = "excel/ingredient" + fileName + ".xlsx";
			Object[] columnNames = ExcelTextContent.ingredientColumnNames;

			XSSFRow row = xssfSheet.createRow(0);
			int columnNum = 0;
			for (Object leaveColumn : columnNames) {
				XSSFCell cell = row.createCell(columnNum++);
				if (leaveColumn instanceof String)
					cell.setCellValue((String) leaveColumn);
				else
					cell.setCellValue("");
			}

			writeDayIngredientToExcel(xssfSheet, day);

			try {
				fileOutputStream = new FileOutputStream(filePath);
				xssfWorkbook.write(fileOutputStream);
				xssfWorkbook.close();
				fileOutputStream.close();
			} catch (FileNotFoundException e) {
				// TODO: handle exception
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void writeDayIngredientToExcel(XSSFSheet xssfSheet, DayComponent dayElement) {
		int rowNum = 1;
		rowNum = writeFoodIngredientToExcel(xssfSheet, dayElement.getStapleFood(), dayElement, rowNum);
		rowNum = writeFoodIngredientToExcel(xssfSheet, dayElement.getMainCourse(), dayElement, rowNum);
		rowNum = writeFoodIngredientToExcel(xssfSheet, dayElement.getSideDishOne(), dayElement, rowNum);
		rowNum = writeFoodIngredientToExcel(xssfSheet, dayElement.getSideDishSecond(), dayElement, rowNum);
		rowNum = writeFoodIngredientToExcel(xssfSheet, dayElement.getSoup(), dayElement, rowNum);
	}

	private int writeFoodIngredientToExcel(XSSFSheet xssfSheet, FoodComponent food, DayComponent day,
			int rowNum) {
		XSSFRow row = null;

		for (IngredientComponent ingredient : food.getIngredientArray()) {
			row = xssfSheet.createRow(rowNum++);

			for (int i = 0; i < ExcelTextContent.ingredientColumnNames.length; i++) {
				XSSFCell cell = row.createCell(i);
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
					cell.setCellValue(converterJinToKg(ingredient.getUnit()));
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
	
	private String converterJinToKg(String unitJin) {
		String inputStr=unitJin;
		String patternStr="[0-9]{1,}";
		Pattern pattern=Pattern.compile(patternStr);
		Matcher matcher=pattern.matcher(inputStr);
		
		if(matcher.find()){
			String weighJintStr=matcher.group();
			double weightKgDouble=Integer.valueOf(weighJintStr)*0.6;
			double weightKgInt=new BigDecimal(weightKgDouble)
                    .setScale(2, BigDecimal.ROUND_HALF_UP)
                    .doubleValue();
			String unitKg=String.valueOf(weightKgInt);
			return unitKg;
		}
		else{
			patternStr="[®w¦s]{1,}";
			pattern=Pattern.compile(patternStr);
			matcher=pattern.matcher(inputStr);
			
			if(matcher.find()){
				return "(®w¦s)";
			}
		}
		return null;
	}
	
	private String getFileName(String dayDate) {
		String fileName = backSlashToDot(dayDate);
		return fileName;
	}
}
