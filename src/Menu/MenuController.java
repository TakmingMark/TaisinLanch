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
		menuModel.startFinishButtonProgressBar();
		menuModel.menuViewFormatToMenuDataOutput(menuView);
		menuModel.exportDataToExcel();
	}
	
	public void pressAnalysisButton() {
		menuModel.startAnalysisButtonProgressBar();
		menuModel.menuViewFormatToMenuDataOutput(menuView);
		menuModel.analysisIngredient(menuView);
	}
	
	public void pressRecrordButton() {
		menuModel.startRecrordButtonProgressBar();
		menuModel.menuViewFormatToMenuDataOutput(menuView);
		menuModel.recordFoodDataToFoodFile();
	}
	
	public void pressTestButton() {
		menuModel.startTestButtonProgressBar();
		menuModel.readMenuFileToMenuView(menuView);
	}
}
