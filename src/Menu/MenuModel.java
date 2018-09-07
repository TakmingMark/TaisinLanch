package Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.xml.soap.Text;

import Component.DataProgressBar;
import Component.IngredientComponent;
import Component.MenuDataComponent;
import Component.TextContent;
import Component.ThreadPoolModel;
import Component.Toast;
import Excel.Excel;
import Parser.MenuDataAndFileConverter;
import Parser.DataToViewParser;
import Parser.ViewToDataParser;
import View.DayView;
import View.RowView;
import View.ZoomRowView;
import word.Word;

public class MenuModel {
	MenuDataComponent menuDataInput, menuDataOutput;
	Excel excel;
	Word word;
	MenuDataAndFileConverter menuDataAndFileConverter;
	DataToViewParser dataToViewParser;
	ViewToDataParser viewToDataParser;
	DataProgressBar finishButtonProgressBar, analysisButtonProgressBar, recordButtonProgressBar, testButtonProgressBar;
	ThreadPoolModel threadPool;
	ArrayList<JComponent> previousFocusComponentList;

	private MenuModel() {

	}

	public static MenuModel getMenuModelObject() {
		return new MenuModel();
	}

	public void initMenuModel() {
		excel = Excel.getExcelObject();
		word = Word.getWordObject();
		menuDataAndFileConverter = MenuDataAndFileConverter.getMenuDataAndFileConverterObject();
		dataToViewParser = DataToViewParser.getDataToViewParserObject();
		viewToDataParser = ViewToDataParser.getViewToDataParserObject();
		threadPool = ThreadPoolModel.getThreadPoolModelObject(1, 5000);
		previousFocusComponentList = new ArrayList<JComponent>();
	}

	public void initKnowFiledName(MenuView menuView) {
		menuView.getSchoolNameTextField().setText(TextContent.schoolName);
		menuView.getSupplierNametextField().setText(TextContent.supplierName);
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

	public void readMenuFileToMenuView(MenuView menuView, String menuFileName) {
		startTestButtonProgressBar();
		threadPool.executeThreadPool(new Runnable() {
			@Override
			public void run() {
				menuDataInput = menuDataAndFileConverter.getMenuDataFromMenuFile(TextContent.filePath+"json/" + menuFileName);
				testButtonProgressBar.addProgressRate();
				testButtonProgressBar.addProgressRate();
				dataToViewParser.menuDataInputToMenuView(menuDataInput, menuView);
				testButtonProgressBar.addProgressRate();
				testButtonProgressBar.addProgressRate();
			}

		});
	}

	private void menuViewFormatToMenuDataOutput(MenuView menuView) {
		menuDataOutput = viewToDataParser.menuViewToMenuDataOutput(menuView);
	}

	public void exportData(MenuView menuView) {
		startFinishButtonProgressBar();
		threadPool.executeThreadPool(new Runnable() {
			@Override
			public void run() {
				finishButtonProgressBar.addProgressRate();
				menuViewFormatToMenuDataOutput(menuView);
				finishButtonProgressBar.addProgressRate();
				exportDataToExcel();
				finishButtonProgressBar.addProgressRate();
				exportDataToWord();
				finishButtonProgressBar.addProgressRate();
			}
		});
	}

	private void exportDataToExcel() {
		excel.exportDataToExcel(menuDataOutput);
	}

	private void exportDataToWord() {
		word.exportDataToExcel(menuDataOutput);
	}

	public void recordFoodDataToFoodFile(MenuView menuView, String foodFileName) {
		startRecrordButtonProgressBar();
		new Thread(new Runnable() {
			@Override
			public void run() {
				recordButtonProgressBar.addProgressRate();
				menuViewFormatToMenuDataOutput(menuView);
				recordButtonProgressBar.addProgressRate();
				menuDataAndFileConverter.mergeFoodDataToFoodFile(menuDataOutput, TextContent.filePath+"json/" + foodFileName);
				recordButtonProgressBar.addProgressRate();
				recordButtonProgressBar.addProgressRate();
			}
		}).start();
	}

	public void analysisIngredient(MenuView menuView, String menuFileName) {
		startAnalysisButtonProgressBar();
		threadPool.executeThreadPool(new Runnable() {
			@Override
			public void run() {
				analysisButtonProgressBar.addProgressRate();
				menuViewFormatToMenuDataOutput(menuView);

				Map<String, List<IngredientComponent>> foodMap = menuDataAndFileConverter
						.getFoodMapFromFoodFile(TextContent.filePath+"json/" + menuFileName);

				queryFoodMapAndToDayView(foodMap, menuView.getMonday());
				queryFoodMapAndToDayView(foodMap, menuView.getTuesday());
				analysisButtonProgressBar.addProgressRate();
				queryFoodMapAndToDayView(foodMap, menuView.getWednesday());
				analysisButtonProgressBar.addProgressRate();
				queryFoodMapAndToDayView(foodMap, menuView.getThursday());
				queryFoodMapAndToDayView(foodMap, menuView.getFriday());
				analysisButtonProgressBar.addProgressRate();
				// not store in food.json
			}

		});
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

	synchronized public void recordEditHistory(MenuView menuView, String historyFileName) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				menuViewFormatToMenuDataOutput(menuView);
				menuDataAndFileConverter.getHistoryFileFromMenuData(menuDataOutput, "json/" + historyFileName);
			}
		}).start();
	}

	public boolean showYesOrNoToash() {
		if (Toast.geYesOrNoToastObject("需要還原上一次關閉的資料嗎?最後一筆需要檢查") == 0)
			return true;
		else
			return false;
	}

	public void recordPreviousComponentPosition(MenuView menuView) {
		previousFocusComponentList.add((JComponent) menuView.getFrame().getFocusOwner());
	}

	public void restoreFocusComponentPosition() {
		if (previousFocusComponentList.size() != 0) {
			previousFocusComponentList.get(previousFocusComponentList.size() - 1).requestFocusInWindow();
			previousFocusComponentList.remove(previousFocusComponentList.size() - 1);
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