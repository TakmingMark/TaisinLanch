package Main;

import java.awt.Menu;

import Excel.Excel;
import Menu.MenuController;
import Menu.MenuModel;
import Menu.MenuView;

public class Activity {

	public static void main(String args[]) {
//		Excel excel=Excel.getExcelObject();
		
		MenuView menuView=MenuView.getMenuViewObject();
		MenuModel menuModel=MenuModel.getMenuModelObject();
		MenuController menuController=MenuController.getMenuControllerObject(menuModel, menuView);
	}
}
