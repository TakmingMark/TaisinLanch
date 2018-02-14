package Excel;

import java.util.ArrayList;

import Component.DayComponent;
import Component.MenuDataComponent;

public class Excel extends ExcelModel{
	MenuExcelModel menuExcelModel;
	IngredientExcelModel ingredientExcelModel;
	AcceptanceExcelModel acceptanceExcelModel;
	
	private Excel() {
		initExcel();
	}
	
	public static Excel getExcelObject() {
		return new Excel();
	}
	
	private void initExcel() {
		menuExcelModel=new MenuExcelModel();
		ingredientExcelModel=new IngredientExcelModel();
		acceptanceExcelModel=new AcceptanceExcelModel();
	}
	
	public void exportDataToExcel(MenuDataComponent menuOutputData) {
		supplementMenuOutputData(menuOutputData);
		menuExcelModel.writeExcel(menuOutputData);
		ingredientExcelModel.writeExcel(menuOutputData);
		
		acceptanceExcelModel.writeExcel(menuOutputData);
	}
	
	private void supplementMenuOutputData(MenuDataComponent menuOutputData) {
		calculateDayDate(menuOutputData);
		calculateParchaseDate(menuOutputData);
	}
}
