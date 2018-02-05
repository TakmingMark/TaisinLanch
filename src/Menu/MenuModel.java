package Menu;

import java.util.List;
import java.util.Map;

import Component.IngredientComponent;
import Component.MenuDataComponent;
import Excel.Excel;
import Parser.DataToViewParser;
import Parser.FoodListJSONParser;
import Parser.ViewToDataParser;


public class MenuModel {
	MenuDataComponent menuDataInput, menuDataOutput;
	Excel excel;
	FoodListJSONParser foodListJSONParser;
	DataToViewParser dataToViewParser;
	ViewToDataParser viewToDataParser;
	private MenuModel() {

	}

	public static MenuModel getMenuModelObject() {
		return new MenuModel();
	}

	public void initMenuModel() {
		excel = Excel.getExcelObject();
		foodListJSONParser=FoodListJSONParser.getFoodListJSONParserObject();
		dataToViewParser=DataToViewParser.getDataToViewParserObject();
		viewToDataParser=ViewToDataParser.getViewToDataParserObject();
	}
	
	public void readMenuFileToMenuView(MenuView menuView) {
		menuDataInput=foodListJSONParser.parseJSONFromMenuFile();
		dataToViewParser.menuDataInputToMenuView(menuDataInput,menuView);
	}
	
	public void menuViewFormatToMenuFile(MenuView menuView) {
		menuDataOutput=viewToDataParser.menuViewToMenuDataOutput(menuView);
	}
	
	public void exportDataToExcel() {
		excel.exportDataToExcel(menuDataOutput);
	}

	public void recordFoodDataToFoodFile() {
		Map<String, List<IngredientComponent>> oldFoodMap,newFoodMap;
		oldFoodMap=foodListJSONParser.parseJSONFromFoodFile();
		newFoodMap=foodListJSONParser.parseMenuDataOutputToFoodList(menuDataOutput);
		oldFoodMap.putAll(newFoodMap);
		
		foodListJSONParser.writeFoodFileFromFoodMap(oldFoodMap);
	}
	
	public void analysisIngredient() {

	}
	
	public MenuDataComponent getMenuDataInput() {
		return menuDataInput;
	}

	public MenuDataComponent getMenuDataOutput() {
		return menuDataOutput;
	}

	public void setMenuDataInput(MenuDataComponent menuDataInput) {
		this.menuDataInput = menuDataInput;
	}

	public void setMenuDataOutput(MenuDataComponent menuDataOutput) {
		this.menuDataOutput = menuDataOutput;
	}

}
