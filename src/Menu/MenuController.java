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
		menuView.setFrameSize(1250, 700);
		menuView.initMenuView();
	}
	
	private void initMenuModel() {
		menuModel.initMenuModel();
		menuModel.initProgressBar(menuView.getFrame());
	}
	private void initMenuController() {
		initMenuViewListener();
	}

	private void initMenuViewListener() {
		menuView.getFinishButton().addActionListener(e ->pressFinishButton());
		menuView.getAnalysisButton().addActionListener(e->pressAnalysisButton());
		menuView.getRecordButton().addActionListener(e ->pressRecrordButton());
		menuView.getTestButton().addActionListener(e -> pressTestButton());
	}
	
	public void pressFinishButton() {
		menuModel.exportDataToExcel(menuView);
	}
	
	public void pressAnalysisButton() {
		menuModel.analysisIngredient(menuView);
	}
	
	public void pressRecrordButton() {
		menuModel.recordFoodDataToFoodFile(menuView);
	}
	
	public void pressTestButton() {
		menuModel.readMenuFileToMenuView(menuView);
	}
}
