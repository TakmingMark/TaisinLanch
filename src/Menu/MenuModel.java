package Menu;

import java.util.List;
import java.util.Map;

import Component.IngredientComponent;
import Component.MenuDataComponent;
import Excel.Excel;
import Parser.DataToViewParser;
import Parser.ComponentAndFileConverter;
import Parser.ViewToDataParser;
import View.DayView;


public class MenuModel {
	MenuDataComponent menuDataInput, menuDataOutput;
	Excel excel;
	ComponentAndFileConverter componentAndFileConverter;
	DataToViewParser dataToViewParser;
	ViewToDataParser viewToDataParser;
	private MenuModel() {

	}

	public static MenuModel getMenuModelObject() {
		return new MenuModel();
	}

	public void initMenuModel() {
		excel = Excel.getExcelObject();
		componentAndFileConverter=ComponentAndFileConverter.getFoodListJSONParserObject();
		dataToViewParser=DataToViewParser.getDataToViewParserObject();
		viewToDataParser=ViewToDataParser.getViewToDataParserObject();

	}
	
	public void readMenuFileToMenuView(MenuView menuView) {
		menuDataInput=componentAndFileConverter.getMenuDataParseMenuFile("json/menu.json");
		dataToViewParser.menuDataInputToMenuView(menuDataInput,menuView);
	}
	
	public void menuViewFormatToMenuFile(MenuView menuView) {
		menuDataOutput=viewToDataParser.menuViewToMenuDataOutput(menuView);
	}
	
	public void exportDataToExcel() {
		excel.exportDataToExcel(menuDataOutput);
	}

	public void recordFoodDataToFoodFile() {
		componentAndFileConverter.foodDataMergeToFoodFile(menuDataOutput,"json/food1.json");
	}
	
	public void analysisIngredient(MenuView menuView) {
		Map<String, List<IngredientComponent>> foodMap=componentAndFileConverter.getFoodMapParseFoodFile("json/food3.json");
	
		queryFoodMapAndToDayView(foodMap,menuView.getMonday());
		queryFoodMapAndToDayView(foodMap,menuView.getTuesday());
		queryFoodMapAndToDayView(foodMap,menuView.getWednesday());
		queryFoodMapAndToDayView(foodMap,menuView.getThursday());
		queryFoodMapAndToDayView(foodMap,menuView.getFriday());
	}
	
	private void queryFoodMapAndToDayView(Map<String, List<IngredientComponent>> foodMap,DayView dayView) {
		if (dayView.getDayCheckBox().isSelected() == true) {
			if(!dayView.getStapleFoodTextField().getText().equals("")){
				List<IngredientComponent> foodArray=foodMap.get(dayView.getStapleFoodTextField().getText());
				dataToViewParser.ingredientDataToIngredientView(foodArray, dayView.getStapleFoodIngredientView());
			}
			
			if(!dayView.getMainCourseTextField().getText().equals("")) {
				List<IngredientComponent> foodArray=foodMap.get(dayView.getMainCourseTextField().getText());
				dataToViewParser.ingredientDataToIngredientView(foodArray, dayView.getMainCourseIngredientView());
			}
			
			if(!dayView.getSideDishOneTextField().getText().equals("")) {
				List<IngredientComponent> foodArray=foodMap.get(dayView.getSideDishOneTextField().getText());
				dataToViewParser.ingredientDataToIngredientView(foodArray,dayView.getSideDishOneIngredientView());
			}
			
			if(!dayView.getSideDishSecondTextField().getText().equals("")) {
				List<IngredientComponent> foodArray=foodMap.get(dayView.getSideDishSecondTextField().getText());
				dataToViewParser.ingredientDataToIngredientView(foodArray, dayView.getSideDishSecondIngredientView());
			}
			
			if(!dayView.getSoupTextField().getText().equals("")) {
				List<IngredientComponent> foodArray=foodMap.get(dayView.getSoupTextField().getText());
				dataToViewParser.ingredientDataToIngredientView(foodArray, dayView.getSoupIngredientView());
			}
		}
		
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
