package Main;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddressBase;

import Component.Food;

public class Activity {

	public static void main(String args[]) {
		System.out.println("Activity.main");
		Activity activity = new Activity();
		activity.readExcel();
	}

	public static void readExcel() {
		FileInputStream fileInputStream = null;
		POIFSFileSystem poifsFileSystem = null;
		HSSFWorkbook hssfWorkbook = null;
		ArrayList<Food> foodList = new ArrayList<>();
		Food food = null;
		int row = 0;
		String filePath = "excel/123.xls";

		try {
			fileInputStream = new FileInputStream(filePath);
			poifsFileSystem = new POIFSFileSystem(fileInputStream);
			hssfWorkbook = new HSSFWorkbook(poifsFileSystem);
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);

			Iterator<Row> rowIterator = hssfSheet.iterator();
			food = new Food();
			if (row == 0)
				rowIterator.next();
			while (rowIterator.hasNext()) {
				Row currentRow = rowIterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				while (cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();
					if (currentCell.getCellTypeEnum()!=CellType.BLANK && !currentCell.toString().equals("")) {
						switch (currentCell.getColumnIndex()) {
						case 0:
							food.setDate(currentCell.toString());
							break;
						case 1:
							food.setWeek(currentCell.toString());
							break;
						case 2:
							food.setStapleFood(currentCell.toString());
							break;
						case 3:
							food.addNonStapleFoodList(currentCell.toString());
							break;
						case 4:
							food.setSoup(currentCell.toString());
							break;
						case 5:
						case 6:
							food.addIngredientList(currentCell.toString());
							break;
						case 7:
						case 8:
							food.addSeasoningList(currentCell.toString());
							break;
						default:
							break;
						}
					}
				}
				if (++row % 4 == 0) {
					foodList.add(food);
					food = new Food();
				}

			}
			fileInputStream.close();

			for (Food element : foodList) {
				element.print();
			}
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}

	}

	public static void readExcel2() {
		FileInputStream fileInputStream = null;
		POIFSFileSystem poifsFileSystem = null;
		HSSFWorkbook hssfWorkbook = null;
		String filePath = "excel/123.xls";
		try {
			fileInputStream = new FileInputStream(filePath);
			poifsFileSystem = new POIFSFileSystem(fileInputStream);
			hssfWorkbook = new HSSFWorkbook(poifsFileSystem);
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);

			Iterator<Row> rowIterator = hssfSheet.iterator();

			while (rowIterator.hasNext()) {
				Row currentRow = rowIterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				while (cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();

					if (currentCell.getCellTypeEnum() != CellType.BLANK)
						System.out.print(currentCell.toString() + " ");
				}
				System.out.println("L");
			}

			fileInputStream.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}

	}
}
