package Menu;

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
	private JLabel stapleFoodLabel, mainCourseLabel, sideDishOneLabel, sideDishSecondLabel, soupLabel;

	private JTextField stapleFoodTextField, mainCourseTextField, sideDishOneTextField, sideDishSecondTextField,
			soupTextField;
	private IngredientView mainCourseIngredientView, sideDishOneIngredientView, sideDishSecondIngredientView,
			soupIngredientView;

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

		stapleFoodTextField = new JTextField(10);
		mainCourseTextField = new JTextField(10);
		sideDishOneTextField = new JTextField(10);
		sideDishSecondTextField = new JTextField(10);
		soupTextField = new JTextField(10);

		mainCourseIngredientView = IngredientView.getIngredientObject(frame);
		sideDishOneIngredientView = IngredientView.getIngredientObject(frame);
		sideDishSecondIngredientView = IngredientView.getIngredientObject(frame);
		soupIngredientView = IngredientView.getIngredientObject(frame);

		GroupLayout groupLayout = new GroupLayout(this);
		this.setLayout(groupLayout);
		groupLayout
				.setHorizontalGroup(groupLayout.createSequentialGroup()
						.addComponent(dayCheckBox, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(stapleFoodLabel, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(stapleFoodTextField, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup()
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(mainCourseLabel, 0, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(mainCourseTextField, 0, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(mainCourseIngredientView, 0, groupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup()
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(sideDishOneLabel, 0, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(sideDishOneTextField, 0, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(sideDishOneIngredientView, 0, groupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup()
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(sideDishSecondLabel, 0, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(sideDishSecondTextField, 0, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(
										sideDishSecondIngredientView, 0, groupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup()
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(soupLabel, 0, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(soupTextField, 0, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(soupIngredientView, 0, groupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)));

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

		));
	}
}