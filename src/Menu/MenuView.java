package Menu;

import java.io.BufferedWriter;
import java.nio.channels.NonWritableChannelException;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.xml.soap.Text;

import Component.Food;
import Component.TextContent;

public class MenuView {
	private JFrame frame;
	private JPanel panel;
	private JLabel schoolNameLabel, dateLabel, yearLabel, monthLabel, dayLabel;
	private JTextField schoolNameTextField;
	private JComboBox yearComboBox, monthComboBox, dayComboBox;
	private DayView mondy, tuesday, wednesday, thursday, friday;

	private MenuView() {
		initMenuView();
	}

	public static MenuView getMenuViewObject() {

		return new MenuView();
	}

	private void initMenuView() {
		frame = new JFrame("School Menu");
		panel = new JPanel();
		schoolNameLabel = new JLabel(TextContent.schoolLabelText);
		dateLabel = new JLabel(TextContent.dateLabelText);
		yearLabel = new JLabel(TextContent.yearLabelText);
		monthLabel = new JLabel(TextContent.monthLabelText);
		dayLabel = new JLabel(TextContent.dayLabelText);
		schoolNameTextField = new JTextField(10);
		yearComboBox = new JComboBox<>(TextContent.yearComboBoxText);
		monthComboBox = new JComboBox<>(TextContent.monthComboBoxText);
		dayComboBox = new JComboBox<>(TextContent.dayComboBoxText);

		mondy = new DayView(frame,TextContent.weekText[0]);
		tuesday = new DayView(frame,TextContent.weekText[1]);
		wednesday = new DayView(frame,TextContent.weekText[2]);
		thursday = new DayView(frame,TextContent.weekText[3]);
		friday = new DayView(frame,TextContent.weekText[4]);

		GroupLayout groupLayout = new GroupLayout(panel);
		panel.setLayout(groupLayout);
		groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup().addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup()
								.addComponent(schoolNameLabel, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(dateLabel, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup()
								.addComponent(schoolNameTextField, 0, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(yearComboBox, 0, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(yearLabel, 0, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(monthComboBox, 0, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(monthLabel, 0, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(dayComboBox, 0, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(dayLabel, 0, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))))
						.addComponent(mondy, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(tuesday, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(wednesday, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(thursday, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(friday, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))

		);

		groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup()
						.addComponent(schoolNameLabel, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(schoolNameTextField, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createParallelGroup()
						.addComponent(dateLabel, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(yearComboBox, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(yearLabel, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(monthComboBox, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(monthLabel, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(dayComboBox, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(dayLabel, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addComponent(mondy, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(tuesday, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(wednesday, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(thursday, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(friday, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));

		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}

	
}
