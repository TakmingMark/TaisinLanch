package Excel;

import java.awt.List;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

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
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		HSSFSheet hssfSheet = hssfWorkbook.createSheet(ExcelTextContent.acceptanceSHeetName);

		String fileName = getFileName(menuOutputData.getDate());
		String filePath = "excel/acceptance" + fileName + ".xls";

		String[] columnNames = ExcelTextContent.accpetanceColumnNames;
		String startDate = calculateMenuDayDate(menuOutputData.getDate(), "星期一");
		String endDate = calculateMenuDayDate(menuOutputData.getDate(), "星期五");

		try {
			removeRepeatAndWeightAdd(menuOutputData);
		} catch (NumberFormatException e) {
			try {
				hssfWorkbook.close();
				throw e;
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}

		int rowNum = 0;
		int columnNum = 0;

		Row row = hssfSheet.createRow(rowNum++);

		for (String leaveColumn : columnNames) {
			Cell cell = row.createCell(columnNum++);
			cell.setCellValue(leaveColumn);
		}

		Iterator<Entry<String, ingredientQuantity>> iterator = IngredientMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, ingredientQuantity> pair = (Map.Entry) iterator.next();
			row = hssfSheet.createRow(rowNum++);
			for (int i = 0; i < columnNames.length; i++) {
				Cell cell = row.createCell(i);
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
					cell.setCellValue(ExcelTextContent.ingredientSupplier);
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
			hssfWorkbook.write(fileOutputStream);
			hssfWorkbook.close();
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
				IngredientMap.get(seasoningName).weight += Integer.valueOf(seasoningWeight);
			} else {
				IngredientMap.put(seasoningName,
						new ingredientQuantity(Double.valueOf(seasoningWeight), seasoningUnit));
			}
		} catch (NumberFormatException e) {
			new Toast();
			throw new NumberFormatException("the seasoning wegiht must number unit");
		}
	}

	private String getFileName(String targetDate) {
		String fileName = calculateMenuDayDate(targetDate, "星期一") + "-" + calculateMenuDayDate(targetDate, "星期五");
		fileName = backSlashToDot(fileName);
		return fileName;
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
