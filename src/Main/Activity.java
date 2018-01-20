package Main;

import java.awt.Menu;

import Excel.Excel;
import Menu.MenuModel;
import Menu.MenuView;

public class Activity {

	public static void main(String args[]) {
		Excel excel=Excel.getExcelObject();
		
		MenuView menuView=MenuView.getMenuViewObject(1279, 738);
		MenuModel menuModel=MenuModel.getMenuModelObject();
	}
}
