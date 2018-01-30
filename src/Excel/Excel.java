package Excel;

import Component.MenuDataComponent;

public class Excel {
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
		menuExcelModel.writeExcel(menuOutputData);
		ingredientExcelModel.writeExcel(menuOutputData);
		acceptanceExcelModel.writeExcel(menuOutputData);
	}
}
