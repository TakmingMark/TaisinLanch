package Excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Component.DayComponent;
import Component.IngredientComponent;
import Component.MenuDataComponent;
import Component.TextContent;
import Component.Toast;

public class AcceptanceExcelModel extends ExcelModel {
	HashMap<String, ingredientQuantity> IngredientMap;

	public AcceptanceExcelModel() {
		initAcceptanceExcelModel();
	}

	public void initAcceptanceExcelModel() {
		IngredientMap = new HashMap<>();
	}

	public void writeExcel(MenuDataComponent menuOutputData) {
		FileOutputStream fileOutputStream = null;
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
		XSSFSheet xssfSheet = xssfWorkbook.createSheet(ExcelTextContent.acceptanceSHeetName);

		String fileName = getFileName(menuOutputData.getDate());
		String filePath = TextContent.filePath+"excel/acceptance" + fileName + ".xlsx";
		
		String supplierName=menuOutputData.getSupplierName();
		
		String[] columnNames = ExcelTextContent.accpetanceColumnNames;
		String startDate = calculateStartDate(menuOutputData);
		String endDate = calculateEndDate(menuOutputData);
		try {
			removeRepeatAndWeightAdd(menuOutputData);
		} catch (NumberFormatException e) {
			try {
				xssfWorkbook.close();
				throw e;
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}

		int rowNum = 0;
		int columnNum = 0;

		XSSFRow row = xssfSheet.createRow(rowNum++);

		for (String leaveColumn : columnNames) {
			XSSFCell cell = row.createCell(columnNum++);
			cell.setCellValue(leaveColumn);
		}

		Iterator<Entry<String, ingredientQuantity>> iterator = IngredientMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, ingredientQuantity> pair = (Map.Entry) iterator.next();
			row = xssfSheet.createRow(rowNum++);
			for (int i = 0; i < columnNames.length; i++) {
				XSSFCell cell = row.createCell(i);
				switch (i) {
				case 0:
					cell.setCellValue(pair.getKey());
					break;
				case 1:
					cell.setCellValue(startDate);
					break;
				case 2:
				case 3:
					break;
				case 4:
					cell.setCellValue(startDate);
					break;
				case 5:
					cell.setCellValue(endDate);
					break;
				case 6:
					cell.setCellValue(supplierName);
					break;
				case 7:
				case 8:
				case 9:
				case 10:
				case 11:
					break;
				case 12:
					cell.setCellValue(pair.getValue().weight);
					break;
				case 13:
					cell.setCellValue(pair.getValue().unit);
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
			iterator.remove(); // avoids a ConcurrentModificationException
		}

		try {
			fileOutputStream = new FileOutputStream(filePath);
			xssfWorkbook.write(fileOutputStream);
			xssfWorkbook.close();
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void removeRepeatAndWeightAdd(MenuDataComponent menuOutputData) {
		for (DayComponent day : menuOutputData.getDayArray()) {
			for (int i = 0; i < day.getAcceptanceArray().size(); i++) {
				try {
					insertSeasoningToMap(day.getAcceptanceArray().get(i));
				} catch (NumberFormatException e) {
					throw e;
				}
			}
		}
	}

	private void insertSeasoningToMap(IngredientComponent ingredient) {
		String seasoningName = null;
		String seasoningWeight = null;
		String seasoningUnit = null;

		try {
			seasoningName = ingredient.getName();
			seasoningUnit = ingredient.getUnit().substring(ingredient.getUnit().length() - 1,
					ingredient.getUnit().length());
			seasoningWeight = ingredient.getUnit().substring(0, ingredient.getUnit().length() - 1);

			if (IngredientMap.containsKey(seasoningName)) {
				IngredientMap.get(seasoningName).weight += Double.valueOf(seasoningWeight);
			} else {
				IngredientMap.put(seasoningName,
						new ingredientQuantity(Double.valueOf(seasoningWeight), seasoningUnit));
			}
		} catch (NumberFormatException e) {
			Toast.getWarningToastObject("單位不能為國字，請輸入數字");
			throw new NumberFormatException("the seasoning wegiht must number unit");
		}
	}

	private String getFileName(String targetDate) {
		String fileName = calculateMenuDayDate(targetDate, "星期一") + "-" + calculateMenuDayDate(targetDate, "星期五");
		fileName = backSlashToDot(fileName);
		return fileName;
	}
	
	private String calculateStartDate(MenuDataComponent menuOutputData) {
		DayComponent day=menuOutputData.getDayArray().get(0);
		String startDate = calculateMenuDayDate(menuOutputData.getDate(), day.getName());
		return startDate;
		
	}
	
	private String calculateEndDate(MenuDataComponent menuOutputData) {
		DayComponent day=menuOutputData.getDayArray().get(menuOutputData.getDayArray().size()-1);
		String endDate = calculateMenuDayDate(menuOutputData.getDate(), day.getName());
		return endDate;
	}

	class ingredientQuantity {
		public Double weight;
		public String unit;

		public ingredientQuantity(Double weight, String unit) {
			this.weight = weight;
			this.unit = unit;
		}
	}
}