package Menu;

import Component.MenuDataComponent;
import Excel.IngredientExcelModel;
import Excel.MenuExcelModel;

public class MenuController {
	private MenuModel menuModel;
	private MenuView menuView;
	
	private MenuController(MenuModel menuModel,MenuView menuView){
		this.menuModel=menuModel;
		this.menuView=menuView;
		initMenuController();
		initMenuViewListener();
	}
	
	public static MenuController getMenuControllerObject(MenuModel menuModel,MenuView menuView) {
		return new MenuController(menuModel,menuView);
	}
	
	private void initMenuController() {
		menuView.setFrameSize(1430, 738);
		menuView.initMenuView();
		menuModel.menuDataInputToMenuView(menuView);
		menuModel.menuViewToMenuDataOutput(menuView);
	}

	private void initMenuViewListener() {
		menuView.getFinishButton().addActionListener(e ->pressFinishButton());
	}
	public void exportMenuExcel() {
		MenuExcelModel menuExcelModel=new MenuExcelModel();
		menuExcelModel.writeExcel(menuModel.getMenuDataOutput());
		menuExcelModel.calculateMenuDayDate("2019/12/29","�P���@");
		menuExcelModel.calculateMenuDayDate("2019/12/29","�P���G");
		menuExcelModel.calculateMenuDayDate("2019/12/29","�P���T");
		menuExcelModel.calculateMenuDayDate("2019/12/29","�P���|");
		menuExcelModel.calculateMenuDayDate("2019/12/29","�P����");
	}
	
	public void exportIngredientExcel() {
		IngredientExcelModel ingredientExcelModel=new IngredientExcelModel();
		ingredientExcelModel.writeExcel(menuModel.getMenuDataOutput());
	}
	
	public void pressFinishButton() {
		exportMenuExcel();
		exportIngredientExcel();
	}
}
