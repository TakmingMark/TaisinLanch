package Menu;

public class MenuController {
	private MenuModel menuModel;
	private MenuView menuView;
	
	private MenuController(MenuModel menuModel,MenuView menuView){
		this.menuModel=menuModel;
		this.menuView=menuView;
		initMenuView();
		initMenuModel();
		initMenuController();
	}
	
	public static MenuController getMenuControllerObject(MenuModel menuModel,MenuView menuView) {
		return new MenuController(menuModel,menuView);
	}
	
	private void initMenuView() {
		menuView.setFrameSize(1000, 700);
		menuView.initMenuView();
	}
	
	private void initMenuModel() {
		menuModel.initMenuModel();
		menuModel.readMenuFileToMenuView(menuView);
	}
	private void initMenuController() {
		initMenuViewListener();
	}

	private void initMenuViewListener() {
		menuView.getFinishButton().addActionListener(e ->pressFinishButton());
		menuView.getAnalysisButton().addActionListener(e->pressAnalysisButton());
		menuView.getRecordButton().addActionListener(e ->pressRecrordButton());
	}
	
	public void pressFinishButton() {
		menuModel.menuViewFormatToMenuFile(menuView);
		menuModel.exportDataToExcel();
	}
	
	public void pressAnalysisButton() {
		menuModel.menuViewFormatToMenuFile(menuView);
		menuModel.analysisIngredient();
	}
	
	public void pressRecrordButton() {
		menuModel.menuViewFormatToMenuFile(menuView);
		menuModel.recordFoodDataToFoodFile();
	}
}
