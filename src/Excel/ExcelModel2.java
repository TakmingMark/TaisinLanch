package Excel;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

public class ExcelModel2 {
//	private ArrayList<Food> foodList;
//	private String schoolMenuExcelPath=null;
//	private ExcelModel() {
//		initExcelModel();
//	}
//	
//	public static ExcelModel getExcelModelObject(){
//		return new ExcelModel();
//	}
//	
//	private void initExcelModel() {
//		foodList = new ArrayList<>();
//	}
//	
//	public void readSchoolMenuExcel() {
//		FileInputStream fileInputStream = null;
//		POIFSFileSystem poifsFileSystem = null;
//		HSSFWorkbook hssfWorkbook = null;
//		Food food = null;
//		int row = 0;
//	
//		try {
//			fileInputStream = new FileInputStream(schoolMenuExcelPath);
//			poifsFileSystem = new POIFSFileSystem(fileInputStream);
//			hssfWorkbook = new HSSFWorkbook(poifsFileSystem);
//			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
//
//			Iterator<Row> rowIterator = hssfSheet.iterator();
//			food = new Food();
//			if (row == 0)
//				rowIterator.next();
//			
//			while (rowIterator.hasNext() && row<27) {
//				Row currentRow = rowIterator.next();
//				Iterator<Cell> cellIterator = currentRow.iterator();
//				while (cellIterator.hasNext()) {
//					Cell currentCell = cellIterator.next();
//					if (currentCell.getCellTypeEnum()!=CellType.BLANK && !currentCell.toString().equals("")) {
//						switch (currentCell.getColumnIndex()) {
//						case 0:
//							food.setDate(currentCell.toString());
//							break;
//						case 1:
//							food.setWeek(currentCell.toString());
//							break;
//						case 2:
//							food.setStapleFood(currentCell.toString());
//							break;
//						case 3:
//							food.addNonStapleFoodList(currentCell.toString());
//							break;
//						case 4:
//							food.setSoup(currentCell.toString());
//							break;
//						case 5:
//						case 6:
//							food.addIngredientList(currentCell.toString());
//							break;
//						case 7:
//						case 8:
//							food.addSeasoningList(currentCell.toString());
//							break;
//						default:
//							break;
//						}
//					}
//				}
//				if (++row % 5 == 0) {
//					foodList.add(food);
//					food = new Food();
//				}
//
//			}
//			fileInputStream.close();
//
//			for (Food element : foodList) {
//				element.print();
//			}
//		} catch (java.io.IOException e) {
//			e.printStackTrace();
//		}
//
//	}
//	
//	public void writeSchoolIngredientsExcel() {
//		
//	}
//	
//	public ArrayList<Food> getFoodList() {
//		return foodList;
//	}
//
//	public String getSchoolMenuExcelPath() {
//		return schoolMenuExcelPath;
//	}
//
//	public void setFoodList(ArrayList<Food> foodList) {
//		this.foodList = foodList;
//	}
//
//	public void setSchoolMenuExcelPath(String schoolMenuExcelPath) {
//		this.schoolMenuExcelPath = schoolMenuExcelPath;
//	}
}
