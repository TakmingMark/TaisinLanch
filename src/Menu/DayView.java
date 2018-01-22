package Menu;

import javax.naming.Context;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

import Component.TextContent;

class DayView extends JPanel {
	private JFrame frame;
	private JCheckBox dayCheckBox;
	private JLabel stapleFoodLabel, mainCourseLabel, sideDishOneLabel, sideDishSecondLabel, soupLabel, acceptanceLabel;

	private JTextField stapleFoodTextField, mainCourseTextField, sideDishOneTextField, sideDishSecondTextField,
			soupTextField;
	private ZoomRowView mainCourseIngredientView, sideDishOneIngredientView, sideDishSecondIngredientView,
			soupIngredientView;

	private ZoomRowView acceptanceView;

	DayView(JFrame frame, String dayCheckBoxName) {
		this.frame = frame;
		initDayView(dayCheckBoxName);
	}

	public static DayView getDayViewObject(JFrame frame, String dayCheckBoxName) {
		return new DayView(frame, dayCheckBoxName);
	}

	private void initDayView(String dayCheckBoxName) {

		dayCheckBox = new JCheckBox(dayCheckBoxName);
		stapleFoodLabel = new JLabel(TextContent.stapleFoodLabelText);
		mainCourseLabel = new JLabel(TextContent.manCourseLabelText);
		sideDishOneLabel = new JLabel(TextContent.sideDishOneLabelText);
		sideDishSecondLabel = new JLabel(TextContent.sideDishSecondLabelText);
		soupLabel = new JLabel(TextContent.soupLabelText);
		
		stapleFoodTextField = new JTextField(8);
		mainCourseTextField = new JTextField(8);
		sideDishOneTextField = new JTextField(8);
		sideDishSecondTextField = new JTextField(8);
		soupTextField = new JTextField(8);

		mainCourseIngredientView = ZoomRowView.getZoomRowViewObject(frame, TextContent.ingredientText);
		sideDishOneIngredientView = ZoomRowView.getZoomRowViewObject(frame, TextContent.ingredientText);
		sideDishSecondIngredientView = ZoomRowView.getZoomRowViewObject(frame, TextContent.ingredientText);
		soupIngredientView = ZoomRowView.getZoomRowViewObject(frame, TextContent.ingredientText);
		acceptanceView = ZoomRowView.getZoomRowViewObject(frame, TextContent.acceptanceText);

		GroupLayout groupLayout = new GroupLayout(this);
		this.setLayout(groupLayout);
		groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
				.addComponent(dayCheckBox, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addGap(10)
				.addComponent(stapleFoodLabel, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addGap(10)
				.addComponent(stapleFoodTextField, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addGap(30)
				.addGroup(groupLayout.createParallelGroup()
						.addGroup(groupLayout.createSequentialGroup().addGap(20)
								.addComponent(mainCourseLabel, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(30).addComponent(mainCourseTextField, 0, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addComponent(mainCourseIngredientView, 0, groupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(30)
				.addGroup(groupLayout.createParallelGroup()
						.addGroup(groupLayout.createSequentialGroup().addGap(18)
								.addComponent(sideDishOneLabel, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(19).addComponent(sideDishOneTextField, 0, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addComponent(sideDishOneIngredientView, 0, groupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(30)
				.addGroup(groupLayout.createParallelGroup()
						.addGroup(groupLayout.createSequentialGroup().addGap(18)
								.addComponent(sideDishSecondLabel, 0, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(19).addComponent(sideDishSecondTextField, 0, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addComponent(
								sideDishSecondIngredientView, 0, groupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(30)
				.addGroup(groupLayout.createParallelGroup().addGroup(groupLayout.createSequentialGroup().addGap(20)
						.addComponent(soupLabel, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addGap(30)
						.addComponent(soupTextField, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(soupIngredientView, 0, groupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createParallelGroup()
						.addComponent(acceptanceView, 0, groupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
);

		groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()

				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(dayCheckBox, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(stapleFoodLabel, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(stapleFoodTextField, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup()
										.addComponent(mainCourseLabel, 0, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(mainCourseTextField, 0, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(mainCourseIngredientView, 0, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))

						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup()
										.addComponent(sideDishOneLabel, 0, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(sideDishOneTextField, 0, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(sideDishOneIngredientView, 0, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup()
										.addComponent(sideDishSecondLabel, 0, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(sideDishSecondTextField, 0, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(sideDishSecondIngredientView, 0, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup()
										.addComponent(soupLabel, 0, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(soupTextField, 0, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(soupIngredientView, 0, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout.createParallelGroup()
								.addComponent(acceptanceView, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))

		));
	}

	public JCheckBox getDayCheckBox() {
		return dayCheckBox;
	}

	public JTextField getStapleFoodTextField() {
		return stapleFoodTextField;
	}

	public JTextField getMainCourseTextField() {
		return mainCourseTextField;
	}

	public JTextField getSideDishOneTextField() {
		return sideDishOneTextField;
	}

	public JTextField getSideDishSecondTextField() {
		return sideDishSecondTextField;
	}

	public JTextField getSoupTextField() {
		return soupTextField;
	}

	public ZoomRowView getMainCourseIngredientView() {
		return mainCourseIngredientView;
	}

	public ZoomRowView getSideDishOneIngredientView() {
		return sideDishOneIngredientView;
	}

	public ZoomRowView getSideDishSecondIngredientView() {
		return sideDishSecondIngredientView;
	}

	public ZoomRowView getSoupIngredientView() {
		return soupIngredientView;
	}

	public ZoomRowView getAcceptanceView() {
		return acceptanceView;
	}

	public void setDayCheckBox(JCheckBox dayCheckBox) {
		this.dayCheckBox = dayCheckBox;
	}

	public void setStapleFoodTextField(JTextField stapleFoodTextField) {
		this.stapleFoodTextField = stapleFoodTextField;
	}

	public void setMainCourseTextField(JTextField mainCourseTextField) {
		this.mainCourseTextField = mainCourseTextField;
	}

	public void setSideDishOneTextField(JTextField sideDishOneTextField) {
		this.sideDishOneTextField = sideDishOneTextField;
	}

	public void setSideDishSecondTextField(JTextField sideDishSecondTextField) {
		this.sideDishSecondTextField = sideDishSecondTextField;
	}

	public void setSoupTextField(JTextField soupTextField) {
		this.soupTextField = soupTextField;
	}

	public void setMainCourseIngredientView(ZoomRowView mainCourseIngredientView) {
		this.mainCourseIngredientView = mainCourseIngredientView;
	}

	public void setSideDishOneIngredientView(ZoomRowView sideDishOneIngredientView) {
		this.sideDishOneIngredientView = sideDishOneIngredientView;
	}

	public void setSideDishSecondIngredientView(ZoomRowView sideDishSecondIngredientView) {
		this.sideDishSecondIngredientView = sideDishSecondIngredientView;
	}

	public void setSoupIngredientView(ZoomRowView soupIngredientView) {
		this.soupIngredientView = soupIngredientView;
	}

	public void setAcceptanceView(ZoomRowView acceptanceView) {
		this.acceptanceView = acceptanceView;
	}
	
	
}