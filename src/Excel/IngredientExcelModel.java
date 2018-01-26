package Excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

import Component.Day;
import Component.Food;
import Component.MenuDataComponent;
import Component.TextContent;

public class IngredientExcelModel extends ExcelModel {

	public void writeExcel(MenuDataComponent menuOutputData) {

		for (Day dayElement : menuOutputData.getDay()) {
			FileOutputStream fileOutputStream = null;
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
			HSSFSheet hssfSheet = hssfWorkbook.createSheet(ExcelTextContent.ingredientSheetName);
			Row row = hssfSheet.createRow(0);
			String fileName = calculateMenuDayDate(menuOutputData.getDate(), dayElement.getName());
			fileName = backSlashToDot(fileName);
			String filePath = "excel/ingredient" + fileName + ".xls";
			Object[] columnNames = ExcelTextContent.ingredientColumnNames;
			
			dayElement.setDate(calculateMenuDayDate(menuOutputData.getDate(), dayElement.getName()));
			dayElement.setParchaseDate(calculateMenuDayDate(menuOutputData.getDate(), dayElement.getName()));
			
			int columnNum = 0;
			for (Object leaveColumn : columnNames) {
				Cell cell = row.createCell(columnNum++);
				if (leaveColumn instanceof String)
					cell.setCellValue((String) leaveColumn);
				else
					cell.setCellValue("");
			}

			writeDayIngredientToExcel(hssfSheet, dayElement);

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

	private void writeDayIngredientToExcel(HSSFSheet hssfSheet, Day dayElement) {
		int rowNum = 1;
		rowNum = writeFoodIngredientToExcel(hssfSheet, dayElement.getStapleFood(), dayElement, rowNum);
		rowNum = writeFoodIngredientToExcel(hssfSheet, dayElement.getMainCourse(), dayElement, rowNum);
		rowNum = writeFoodIngredientToExcel(hssfSheet, dayElement.getSideDishOne(), dayElement, rowNum);
		rowNum = writeFoodIngredientToExcel(hssfSheet, dayElement.getSideDishSecond(), dayElement, rowNum);
		rowNum = writeFoodIngredientToExcel(hssfSheet, dayElement.getSoup(), dayElement, rowNum);
	}

	private int writeFoodIngredientToExcel(HSSFSheet hssfSheet, Food food, Day dayElement, int rowNum) {
		Row row = null;
		
		for (String ingredientElement : food.getIngredient()) {
			row = hssfSheet.createRow(rowNum++);
			String ingredientName=null;
			String ingredientWeight = null;
			for (int i = 0; i < ExcelTextContent.ingredientColumnNames.length; i++) {
				Cell cell = row.createCell(i);
				switch (i) {
				case 0:
					cell.setCellValue(dayElement.getDate());
					break;
				case 1:
					break;
				case 2:
					cell.setCellValue(food.getName());
					break;
				case 3:
					String ingredientNameAndWeight = ingredientUnitConversion(ingredientElement);
					ingredientName = ingredientNameAndWeight.split("\\|")[0];
					ingredientWeight = ingredientNameAndWeight.split("\\|")[1];
					cell.setCellValue(ingredientName);
					break;
				case 4:
					cell.setCellValue(dayElement.getParchaseDate());
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
					cell.setCellValue(ingredientWeight);
					break;
				case 14:
					cell.setCellValue(ExcelTextContent.ingredientO);
					break;
				case 15:
					cell.setCellValue(ExcelTextContent.ingredientP);
					break;
				case 16:
					cell.setCellValue(ExcelTextContent.ingredinetQ);
					break;
				default:
					break;
				}
			}
		}
		return rowNum;
	}

	private String ingredientUnitConversion(String ingredientNameAndWeight) {
		String regex = "[0-9]{1,}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(ingredientNameAndWeight);

		String ingredientName = null;
		String ingredientWeight = null;
		double ingredientKgWeight = 0;
		boolean judgeWhetherDot = true;

		while (matcher.find()) {
			judgeWhetherDot = !judgeWhetherDot;
			if (judgeWhetherDot) {
				ingredientWeight += "." + matcher.group();
			} else {
				ingredientName = ingredientNameAndWeight.substring(0, matcher.start());
				ingredientWeight = matcher.group(0);
			}
		}

		if (ingredientNameAndWeight.endsWith("¤ç")) {
			ingredientKgWeight = new BigDecimal(Double.valueOf(ingredientWeight) * 0.6)
					.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();

		} else if (ingredientNameAndWeight.endsWith("Áû")) {
			ingredientKgWeight = Double.valueOf(ingredientWeight) * 0.125;
		} else if (ingredientNameAndWeight.endsWith("°¦")) {
			ingredientKgWeight = new BigDecimal(Double.valueOf(ingredientWeight) * 0.13)
					.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
		} else if (ingredientNameAndWeight.endsWith("¥]")) {
			ingredientKgWeight = Double.valueOf(ingredientWeight) * 1;
		} else if (ingredientNameAndWeight.endsWith("(®w¦s)")) {
			ingredientName = ingredientNameAndWeight;
			ingredientKgWeight = new Random().nextInt(2) + 2;
		} else {
			ingredientKgWeight = 0;
		}
		return ingredientName + "|" + ingredientKgWeight;
	}
}
