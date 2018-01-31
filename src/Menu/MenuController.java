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
		menuView.setFrameSize(1430, 738);
		menuView.initMenuView();
	}
	
	private void initMenuModel() {
		menuModel.menuDataInputToMenuView(menuView);
		menuModel.initMenuModel();
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
		menuModel.menuViewToMenuDataOutput(menuView);
		menuModel.exportDataToExcel();
	}
	
	public void pressAnalysisButton() {
		menuModel.menuViewToMenuDataOutput(menuView);
		menuModel.analysisIngredient();
	}
	
	public void pressRecrordButton() {
		menuModel.menuViewToMenuDataOutput(menuView);
		menuModel.recordDataToJsonFile();
	}
}
