package Menu;

import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import Component.DataProgressBar;
import Component.IngredientComponent;
import Component.MenuDataComponent;
import Excel.Excel;
import Parser.ComponentAndFileConverter;
import Parser.DataToViewParser;
import Parser.ViewToDataParser;
import View.DayView;

public class MenuModel {
	MenuDataComponent menuDataInput, menuDataOutput;
	Excel excel;
	ComponentAndFileConverter componentAndFileConverter;
	DataToViewParser dataToViewParser;
	ViewToDataParser viewToDataParser;
	DataProgressBar finishButtonProgressBar, analysisButtonProgressBar, recordButtonProgressBar, testButtonProgressBar;

	private MenuModel() {

	}

	public static MenuModel getMenuModelObject() {
		return new MenuModel();
	}

	public void initMenuModel() {
		excel = Excel.getExcelObject();
		componentAndFileConverter = ComponentAndFileConverter.getFoodListJSONParserObject();
		dataToViewParser = DataToViewParser.getDataToViewParserObject();
		viewToDataParser = ViewToDataParser.getViewToDataParserObject();

	}

	public void initProgressBar(JFrame frame) {
		finishButtonProgressBar = DataProgressBar.getDataProgressBarObject(frame);
		analysisButtonProgressBar = DataProgressBar.getDataProgressBarObject(frame);
		recordButtonProgressBar = DataProgressBar.getDataProgressBarObject(frame);
		testButtonProgressBar = DataProgressBar.getDataProgressBarObject(frame);
	}

	public void readMenuFileToMenuView(MenuView menuView) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				menuDataInput = componentAndFileConverter.getMenuDataParseMenuFile("json/menu.json");
				testButtonProgressBar.addProgressRate();
				testButtonProgressBar.addProgressRate();
				dataToViewParser.menuDataInputToMenuView(menuDataInput, menuView);
				testButtonProgressBar.addProgressRate();
				testButtonProgressBar.addProgressRate();
			}
		}).start();
	}

	public void menuViewFormatToMenuDataOutput(MenuView menuView) {
		menuDataOutput = viewToDataParser.menuViewToMenuDataOutput(menuView);
	}

	public void exportDataToExcel() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				finishButtonProgressBar.addProgressRate();
				finishButtonProgressBar.addProgressRate();
				excel.exportDataToExcel(menuDataOutput);
				finishButtonProgressBar.addProgressRate();
				finishButtonProgressBar.addProgressRate();
			}
		}).start();
	}

	public void recordFoodDataToFoodFile() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				recordButtonProgressBar.addProgressRate();
				recordButtonProgressBar.addProgressRate();
				componentAndFileConverter.foodDataMergeToFoodFile(menuDataOutput, "json/food.json");
				recordButtonProgressBar.addProgressRate();
				recordButtonProgressBar.addProgressRate();
			}
		}).start();
	}

	public void analysisIngredient(MenuView menuView) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Map<String, List<IngredientComponent>> foodMap = componentAndFileConverter
						.getFoodMapParseFoodFile("json/food.json");

				queryFoodMapAndToDayView(foodMap, menuView.getMonday());
				analysisButtonProgressBar.addProgressRate();
				queryFoodMapAndToDayView(foodMap, menuView.getTuesday());
				analysisButtonProgressBar.addProgressRate();
				queryFoodMapAndToDayView(foodMap, menuView.getWednesday());
				analysisButtonProgressBar.addProgressRate();
				queryFoodMapAndToDayView(foodMap, menuView.getThursday());
				queryFoodMapAndToDayView(foodMap, menuView.getFriday());
				analysisButtonProgressBar.addProgressRate();
			}
		}).start();
	}

	private void queryFoodMapAndToDayView(Map<String, List<IngredientComponent>> foodMap, DayView dayView) {
		if (dayView.getDayCheckBox().isSelected() == true) {
			if (!dayView.getStapleFoodTextField().getText().equals("")) {
				List<IngredientComponent> foodArray = foodMap.get(dayView.getStapleFoodTextField().getText());
				dataToViewParser.ingredientDataToIngredientView(foodArray, dayView.getStapleFoodIngredientView());
			}

			if (!dayView.getMainCourseTextField().getText().equals("")) {
				List<IngredientComponent> foodArray = foodMap.get(dayView.getMainCourseTextField().getText());
				dataToViewParser.ingredientDataToIngredientView(foodArray, dayView.getMainCourseIngredientView());
			}

			if (!dayView.getSideDishOneTextField().getText().equals("")) {
				List<IngredientComponent> foodArray = foodMap.get(dayView.getSideDishOneTextField().getText());
				dataToViewParser.ingredientDataToIngredientView(foodArray, dayView.getSideDishOneIngredientView());
			}

			if (!dayView.getSideDishSecondTextField().getText().equals("")) {
				List<IngredientComponent> foodArray = foodMap.get(dayView.getSideDishSecondTextField().getText());
				dataToViewParser.ingredientDataToIngredientView(foodArray, dayView.getSideDishSecondIngredientView());
			}

			if (!dayView.getSoupTextField().getText().equals("")) {
				List<IngredientComponent> foodArray = foodMap.get(dayView.getSoupTextField().getText());
				dataToViewParser.ingredientDataToIngredientView(foodArray, dayView.getSoupIngredientView());
			}
		}
	}

	public void startTestButtonProgressBar() {
		testButtonProgressBar.start();
		testButtonProgressBar.addProgressRate();
	}

	public void startRecrordButtonProgressBar() {
		recordButtonProgressBar.start();
		recordButtonProgressBar.addProgressRate();
	}

	public void startAnalysisButtonProgressBar() {
		analysisButtonProgressBar.start();
		analysisButtonProgressBar.addProgressRate();
	}

	public void startFinishButtonProgressBar() {
		finishButtonProgressBar.start();
		finishButtonProgressBar.addProgressRate();
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
