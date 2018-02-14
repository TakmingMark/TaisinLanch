package Menu;

import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import Component.KeyStrokeAction;

import View.RowView;
import View.ZoomRowView;

public class MenuController {
	private MenuModel menuModel;
	private MenuView menuView;

	private MenuController(MenuModel menuModel, MenuView menuView) {
		this.menuModel = menuModel;
		this.menuView = menuView;
		initMenuView();
		initMenuModel();
		initMenuController();
	}

	public static MenuController getMenuControllerObject(MenuModel menuModel, MenuView menuView) {
		return new MenuController(menuModel, menuView);
	}

	private void initMenuView() {
		menuView.setFrameSize(1250, 700);
		menuView.initMenuView();
	}

	private void initMenuModel() {
		menuModel.initMenuModel();
		menuModel.initProgressBar(menuView.getFrame());
		menuModel.initKnowFiledName(menuView);
	}

	private void initMenuController() {
		initMenuViewListener();
		initMenuViewKeyStrokeAction();
	}

	private void initMenuViewListener() {
		menuView.getFinishButton().addActionListener(e -> pressFinishButton());
		menuView.getAnalysisButton().addActionListener(e -> pressAnalysisButton());
		menuView.getRecordButton().addActionListener(e -> pressRecrordButton());
		menuView.getTestButton().addActionListener(e -> pressTestButton());

	}

	private void initMenuViewKeyStrokeAction() {
		menuView.getFrame().getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("F1"), "insertRowView");
		menuView.getFrame().getRootPane().getActionMap().put("insertRowView",
				new KeyStrokeAction(ae -> pressF1KeyStroke()));

		menuView.getFrame().getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("F2"), "removeRowView");
		menuView.getFrame().getRootPane().getActionMap().put("removeRowView",
				new KeyStrokeAction(ae -> pressF2KeyStroke()));

		menuView.getFrame().getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("F4"), "pressFinishButton");
		menuView.getFrame().getRootPane().getActionMap().put("pressFinishButton",
				new KeyStrokeAction(ae -> pressF4KeyStroke()));

		menuView.getFrame().getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("F5"), "pressAnalysisButton");
		menuView.getFrame().getRootPane().getActionMap().put("pressAnalysisButton",
				new KeyStrokeAction(ae -> pressF5KeyStroke()));

		menuView.getFrame().getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("F9"), "pressRecrordButton");
		menuView.getFrame().getRootPane().getActionMap().put("pressRecrordButton",
				new KeyStrokeAction(ae -> pressF9KeyStroke()));

		menuView.getFrame().getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("F12"), "pressTestButton");
		menuView.getFrame().getRootPane().getActionMap().put("pressTestButton",
				new KeyStrokeAction(ae -> pressF12KeyStroke()));
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

	public void pressF1KeyStroke() {
		menuModel.insertIngredientRowView(menuView);
	}

	public void pressF2KeyStroke() {
		menuModel.removeIngredientRowView(menuView);
	}

	public void pressF4KeyStroke() {
		pressFinishButton();
	}

	public void pressF5KeyStroke() {
		pressAnalysisButton();
	}

	public void pressF9KeyStroke() {
		pressRecrordButton();
	}

	public void pressF12KeyStroke() {
		pressTestButton();
	}

}
