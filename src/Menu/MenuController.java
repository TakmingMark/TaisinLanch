package Menu;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.NotActiveException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import com.microsoft.schemas.office.visio.x2012.main.impl.VisioDocumentDocument1Impl;

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
		initEditHistoryRestore();
	}

	private void initMenuViewListener() {
		menuView.getFinishButton().addActionListener(e -> pressFinishButton());
		menuView.getAnalysisButton().addActionListener(e -> pressAnalysisButton());
		menuView.getRecordButton().addActionListener(e -> pressRecrordButton());
		menuView.getTestButton().addActionListener(e -> pressTestButton());

	}

	private void initMenuViewKeyStrokeAction() {
		menuView.getFrame().getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "insertRowView");
		menuView.getFrame().getRootPane().getActionMap().put("insertRowView",
				new KeyStrokeAction(ae -> pressF1KeyStroke()));

		menuView.getFrame().getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "removeRowView");
		menuView.getFrame().getRootPane().getActionMap().put("removeRowView",
				new KeyStrokeAction(ae -> pressF2KeyStroke()));

		menuView.getFrame().getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), "pressFinishButton");
		menuView.getFrame().getRootPane().getActionMap().put("pressFinishButton",
				new KeyStrokeAction(ae -> pressF4KeyStroke()));

		menuView.getFrame().getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), "pressAnalysisButton");
		menuView.getFrame().getRootPane().getActionMap().put("pressAnalysisButton",
				new KeyStrokeAction(ae -> pressF5KeyStroke()));

		menuView.getFrame().getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0), "pressRecrordButton");
		menuView.getFrame().getRootPane().getActionMap().put("pressRecrordButton",
				new KeyStrokeAction(ae -> pressF9KeyStroke()));

		menuView.getFrame().getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), "pressTestButton");
		menuView.getFrame().getRootPane().getActionMap().put("pressTestButton",
				new KeyStrokeAction(ae -> pressF12KeyStroke()));

		menuView.getFrame().getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "recordEditText");
		menuView.getFrame().getRootPane().getActionMap().put("recordEditText",
				new KeyStrokeAction(ae -> pressTabKeyStroke()));
	}

	private void initEditHistoryRestore() {
		if (menuModel.showYesOrNoToash())
			menuModel.readMenuFileToMenuView(menuView, "history.json");
		else
			System.out.println("not restore anything");
	}

	private void pressFinishButton() {
		menuModel.exportDataToExcel(menuView);
	}

	private void pressAnalysisButton() {
		menuModel.analysisIngredient(menuView, "food.json");
	}

	private void pressRecrordButton() {
		menuModel.recordFoodDataToFoodFile(menuView, "food.json");
	}

	private void pressTestButton() {
		menuModel.readMenuFileToMenuView(menuView, "menuTest.json");
	}

	private void pressF1KeyStroke() {
		menuModel.insertIngredientRowView(menuView);
	}

	private void pressF2KeyStroke() {
		menuModel.removeIngredientRowView(menuView);
	}

	private void pressF4KeyStroke() {
		pressFinishButton();
	}

	private void pressF5KeyStroke() {
		pressAnalysisButton();
	}

	private void pressF9KeyStroke() {
		pressRecrordButton();
	}

	private void pressF12KeyStroke() {
		pressTestButton();
		System.out.println("pressF12");
	}

	private void pressTabKeyStroke() {
		// resign tab action in system operation
		menuView.getFrame().getFocusOwner().transferFocus();
		menuModel.recordEditHistory(menuView, "history.json");
	}

}
