package Menu;

public class MenuController {
	private MenuModel menuModel;
	private MenuView menuView;
	
	private MenuController(MenuModel menuModel,MenuView menuView){
		this.menuModel=menuModel;
		this.menuView=menuView;
		initMenuController();
	}
	
	public static MenuController getMenuControllerObject(MenuModel menuModel,MenuView menuView) {
		return new MenuController(menuModel,menuView);
	}
	
	private void initMenuController() {
		menuView.setFrameSize(1400, 738);
		menuView.initMenuView();
		menuModel.menuDataInputToMenuView(menuView);
		
		menuView.menuViewToJSONFormat();
	}
}
