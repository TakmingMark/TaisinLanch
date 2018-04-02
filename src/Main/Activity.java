package Main;

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
