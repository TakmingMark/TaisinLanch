package Main;

import java.awt.Menu;

import Component.MenuDataComponent;
import Excel.Excel;
import Excel.MenuExcelModel;
import Menu.MenuController;
import Menu.MenuModel;
import Menu.MenuView;

public class Activity {

	public static void main(String args[]) {
		MenuView menuView=MenuView.getMenuViewObject();
		MenuModel menuModel=MenuModel.getMenuModelObject();
		MenuController menuController=MenuController.getMenuControllerObject(menuModel, menuView);
	}
}
