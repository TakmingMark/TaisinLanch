package Menu;

import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.xml.soap.Text;

import Component.DataProgressBar;
import Component.IngredientComponent;
import Component.MenuDataComponent;
import Component.TextContent;
import Excel.Excel;
import Parser.MenuDataAndFileConverter;
import Parser.DataToViewParser;
import Parser.ViewToDataParser;
import View.DayView;
import View.RowView;
import View.ZoomRowView;

public class MenuModel {
	MenuDataComponent menuDataInput, menuDataOutput;
	Excel excel;
	MenuDataAndFileConverter menuDataAndFileConverter;
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
		menuDataAndFileConverter = MenuDataAndFileConverter.getMenuDataAndFileConverterObject();
		dataToViewParser = DataToViewParser.getDataToViewParserObject();
		viewToDataParser = ViewToDataParser.getViewToDataParserObject();

	}

	public void initKnowFiledName(MenuView menuView) {
		menuView.getSchoolNameTextField().setText(TextContent.schoolName);
		menuView.getMonday().getStapleFoodTextField().setText(TextContent.stapleFoodName);
		menuView.getTuesday().getStapleFoodTextField().setText(TextContent.stapleFoodName);
		menuView.getWednesday().getStapleFoodTextField().setText(TextContent.stapleFoodName);
		menuView.getThursday().getStapleFoodTextField().setText(TextContent.stapleFoodName);
		menuView.getFriday().getStapleFoodTextField().setText(TextContent.stapleFoodName);
		
	}
	
	public void initProgressBar(JFrame frame) {
		finishButtonProgressBar = DataProgressBar.getDataProgressBarObject(frame);
		analysisButtonProgressBar = DataProgressBar.getDataProgressBarObject(frame);
		recordButtonProgressBar = DataProgressBar.getDataProgressBarObject(frame);
		testButtonProgressBar = DataProgressBar.getDataProgressBarObject(frame);
	}

	public void readMenuFileToMenuView(MenuView menuView) {
		startTestButtonProgressBar();
		new Thread(new Runnable() {
			@Override
			public void run() {
				menuDataInput = menuDataAndFileConverter.getMenuDataFromMenuFile("json/menu.json");
				testButtonProgressBar.addProgressRate();
				testButtonProgressBar.addProgressRate();
				dataToViewParser.menuDataInputToMenuView(menuDataInput, menuView);
				testButtonProgressBar.addProgressRate();
				testButtonProgressBar.addProgressRate();
			}
		}).start();
	}

	private void menuViewFormatToMenuDataOutput(MenuView menuView) {
		menuDataOutput = viewToDataParser.menuViewToMenuDataOutput(menuView);
	}

	public void exportDataToExcel(MenuView menuView) {
		startFinishButtonProgressBar();
		new Thread(new Runnable() {
			@Override
			public void run() {
				finishButtonProgressBar.addProgressRate();
				menuViewFormatToMenuDataOutput(menuView);
				finishButtonProgressBar.addProgressRate();
				excel.exportDataToExcel(menuDataOutput);
				finishButtonProgressBar.addProgressRate();
				finishButtonProgressBar.addProgressRate();
			}
		}).start();
	}

	public void recordFoodDataToFoodFile(MenuView menuView) {
		startRecrordButtonProgressBar();
		new Thread(new Runnable() {
			@Override
			public void run() {
				recordButtonProgressBar.addProgressRate();
				menuViewFormatToMenuDataOutput(menuView);
				recordButtonProgressBar.addProgressRate();
				menuDataAndFileConverter.mergeFoodDataToFoodFile(menuDataOutput, "json/food.json");
				recordButtonProgressBar.addProgressRate();
				recordButtonProgressBar.addProgressRate();
			}
		}).start();
	}

	public void analysisIngredient(MenuView menuView) {
		startAnalysisButtonProgressBar();
		new Thread(new Runnable() {
			@Override
			public void run() {
				analysisButtonProgressBar.addProgressRate();
				menuViewFormatToMenuDataOutput(menuView);
				
				Map<String, List<IngredientComponent>> foodMap = menuDataAndFileConverter
						.getFoodMapFromFoodFile("json/food.json");

				queryFoodMapAndToDayView(foodMap, menuView.getMonday());
				queryFoodMapAndToDayView(foodMap, menuView.getTuesday());
				analysisButtonProgressBar.addProgressRate();
				queryFoodMapAndToDayView(foodMap, menuView.getWednesday());
				analysisButtonProgressBar.addProgressRate();
				queryFoodMapAndToDayView(foodMap, menuView.getThursday());
				queryFoodMapAndToDayView(foodMap, menuView.getFriday());
				analysisButtonProgressBar.addProgressRate();
				
				//not store in food.json
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
	public void insertIngredientRowView(MenuView menuView) {
		if (menuView.getFrame().getFocusOwner().getParent().getParent().getParent() instanceof ZoomRowView) {
			ZoomRowView zoomRowView = ((ZoomRowView) menuView.getFrame().getFocusOwner().getParent().getParent()
					.getParent());
			zoomRowView.insertRowView();
			menuView.getFrame().getFocusOwner().requestFocus();
		}
	}
	
	public void removeIngredientRowView(MenuView menuView) {
		if (menuView.getFrame().getFocusOwner().getParent().getParent().getParent() instanceof ZoomRowView) {
			ZoomRowView zoomRowView = ((ZoomRowView) menuView.getFrame().getFocusOwner().getParent().getParent()
					.getParent());
			RowView rowView = ((RowView) menuView.getFrame().getFocusOwner().getParent());
			zoomRowView.removeRowView(rowView);
			zoomRowView.getRowViewArrayList().get(zoomRowView.getRowViewArrayList().size() - 1).getNameTextField()
					.requestFocus();
		}
	}
	private void startTestButtonProgressBar() {
		testButtonProgressBar.run();
		testButtonProgressBar.addProgressRate();
	}

	private void startRecrordButtonProgressBar() {
		recordButtonProgressBar.run();
		recordButtonProgressBar.addProgressRate();
	}

	private void startAnalysisButtonProgressBar() {
		analysisButtonProgressBar.run();
		analysisButtonProgressBar.addProgressRate();
	}

	private void startFinishButtonProgressBar() {
		finishButtonProgressBar.run();
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
