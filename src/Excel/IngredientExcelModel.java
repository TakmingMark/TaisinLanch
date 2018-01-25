package Excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
			String fileName = calculateMenuDate(menuOutputData.getDate(), dayElement.getName());
			fileName = backSlashToDot(fileName);
			String filePath = "excel/ingredient" + fileName + ".xls";
			System.out.println(filePath);
			HSSFSheet hssfSheet = hssfWorkbook.createSheet(ExcelTextContent.ingredientSheetName);
			Object[] columnNames = ExcelTextContent.ingredientColumnNames;

			int rowNum = 0;
			Row row = hssfSheet.createRow(rowNum);
			int columnNum = 0;
			for (Object leaveColumn : columnNames) {
				Cell cell = row.createCell(columnNum++);
				if (leaveColumn instanceof String)
					cell.setCellValue((String) leaveColumn);
				else
					cell.setCellValue("");
			}

			writeDayIngredientToExcel(hssfSheet, dayElement, menuOutputData.getDate());

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

		// for(ArrayList<String> rowDataArrayList:tableDataArrayList) {
		// row=hssfSheet.createRow(rowNum++);
		// columnNum=0;
		// for(String leaveCell:rowDataArrayList) {
		// Cell cell=row.createCell(columnNum++);
		// if(leaveCell instanceof String)
		// cell.setCellValue((String) leaveCell);
		// else
		// cell.setCellValue("");
		// }
		// }

	}
	
	private void writeDayIngredientToExcel(HSSFSheet hssfSheet,Day dayElement,String date) {
		int rowNum=1;
		rowNum=writeFoodIngredientToExcel(hssfSheet,dayElement.getStapleFood(),date,dayElement.getName(),rowNum);

	}

	private int writeFoodIngredientToExcel(HSSFSheet hssfSheet,Food food,String date,String dayName,int rowNum) {
		Row row =null;
		for (String ingredientElement : food.getIngredient()) {
			row = hssfSheet.createRow(rowNum++);
			String ingredientName;
			String ingredientQuantity = null;
			for (int i = 0; i <  ExcelTextContent.ingredientColumnNames.length; i++) {
				Cell cell = row.createCell(i);
				switch (i) {
				case 0:
					cell.setCellValue(calculateMenuDate(date, dayName));
					break;
				case 1:
					break;
				case 2:
					cell.setCellValue(food.getName());
					break;
				case 3:
					String ingredientNameAndQuantity=ingredientUnitConversion(ingredientElement);
					ingredientName=ingredientNameAndQuantity.split("\\|")[0];
					ingredientQuantity=ingredientNameAndQuantity.split("\\|")[1];
					cell.setCellValue(ingredientName);
					break;
				case 4:
					cell.setCellValue(purchaseDate(date, dayName));
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
					cell.setCellValue(ingredientQuantity);
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
	
	private String ingredientUnitConversion(String ingredientNameAndQuantity) {
		String regex = "[0-9]{1,}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(ingredientNameAndQuantity);

		String ingredientName = null;
		String ingredientJinQuantity = null;
		boolean judgeWhetherDot = true;
		while (matcher.find()) {
			judgeWhetherDot = !judgeWhetherDot;
			if (judgeWhetherDot) {
				ingredientJinQuantity += "." + matcher.group();
			} else {
				ingredientName = ingredientNameAndQuantity.substring(0, matcher.start());
				ingredientJinQuantity = matcher.group(0);
			}
		}
		double ingredientKgQuantity = new BigDecimal(Double.valueOf(ingredientJinQuantity) * 0.6)
				.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
		
		return ingredientName+"|"+ingredientKgQuantity;
	}
}
