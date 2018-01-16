package Menu;

import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

import Component.TextContent;

class DayView extends JPanel {

	private JCheckBox dayCheckBox;
	private JLabel stapleFoodLabel, mainCourseLabel, sideDishOneLabel, sideDishSecondLabel, soupLabel;

	private JTextField stapleFoodTextField, mainCourseTextField, sideDishOneTextField, sideDishSecondTextField,
			soupTextField;
	
	DayView(String dayCheckBoxName) {
		initDayView(dayCheckBoxName);
	}
	
	public static DayView getDayViewObject(String dayCheckBoxName) {
		return new DayView(dayCheckBoxName);
	}
	
	private void initDayView(String dayCheckBoxName) {

		dayCheckBox = new JCheckBox(dayCheckBoxName);
		stapleFoodLabel = new JLabel(TextContent.stapleFoodLabelText);
		mainCourseLabel = new JLabel(TextContent.manCourseLabelText);
		sideDishOneLabel = new JLabel(TextContent.sideDishOneLabelText);
		sideDishSecondLabel = new JLabel(TextContent.sideDishSecondLabelText);
		soupLabel = new JLabel(TextContent.soupLabelText);

		stapleFoodTextField = new JTextField(10);
		mainCourseTextField = new JTextField(10);
		sideDishOneTextField = new JTextField(10);
		sideDishSecondTextField = new JTextField(10);
		soupTextField = new JTextField(10);

		GroupLayout groupLayout = new GroupLayout(this);
		this.setLayout(groupLayout);
		groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
				.addComponent(dayCheckBox, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(stapleFoodLabel, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(stapleFoodTextField, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(mainCourseLabel, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(mainCourseTextField, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(sideDishOneLabel, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(sideDishOneTextField, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(sideDishSecondLabel, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(sideDishSecondTextField, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(soupLabel, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(soupTextField, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));

		groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
				
				.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
						.addComponent(dayCheckBox, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(stapleFoodLabel, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(stapleFoodTextField, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(mainCourseLabel, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(mainCourseTextField, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(sideDishOneLabel, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(sideDishOneTextField, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(sideDishSecondLabel, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(sideDishSecondTextField, 0, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(soupLabel, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(soupTextField, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));
	}
}