package Menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedWriter;
import java.nio.channels.NonWritableChannelException;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.xml.soap.Text;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Component.ComponentListenerBuilder;
import Component.TextContent;
import Component.ZoomRowInput;

public class MenuView {
	private JFrame frame;
	private JScrollPane jScrollPane;
	private JPanel panel;
	private JLabel schoolNameLabel, dateLabel, yearLabel, monthLabel, dayLabel;
	private JTextField schoolNameTextField;
	private JComboBox yearComboBox, monthComboBox, dayComboBox;
	private JButton finishButton;
	private DayView monday, tuesday, wednesday, thursday, friday;

	private int frameWidth, frameHeight;

	private MenuView() {

	}

	public static MenuView getMenuViewObject() {
		return new MenuView();
	}

	public void initMenuView() {
		frame = new JFrame("School Menu");
		panel = new JPanel();
		jScrollPane = new JScrollPane(panel);
		schoolNameLabel = new JLabel(TextContent.schoolLabelText);
		dateLabel = new JLabel(TextContent.dateLabelText);
		yearLabel = new JLabel(TextContent.yearLabelText);
		monthLabel = new JLabel(TextContent.monthLabelText);
		dayLabel = new JLabel(TextContent.dayLabelText);
		schoolNameTextField = new JTextField(10);
		yearComboBox = new JComboBox<>(TextContent.yearComboBoxText);
		monthComboBox = new JComboBox<>(TextContent.monthComboBoxText);
		dayComboBox = new JComboBox<>(TextContent.dayComboBoxText);
		finishButton = new JButton(TextContent.finishButtonText);
		monday = new DayView(frame, TextContent.weekChineseText[0]);
		tuesday = new DayView(frame, TextContent.weekChineseText[1]);
		wednesday = new DayView(frame, TextContent.weekChineseText[2]);
		thursday = new DayView(frame, TextContent.weekChineseText[3]);
		friday = new DayView(frame, TextContent.weekChineseText[4]);
		finishButton.setPreferredSize(new Dimension(100, 50));

		monday.setBorder(new TitledBorder(new LineBorder(Color.black, 2, true), TextContent.weekEnglishText[0],
				TitledBorder.LEFT, TitledBorder.TOP, new Font("StSong", Font.BOLD, 16)));
		tuesday.setBorder(new TitledBorder(new LineBorder(Color.black, 2, true), TextContent.weekEnglishText[1],
				TitledBorder.LEFT, TitledBorder.TOP, new Font("StSong", Font.BOLD, 16)));
		wednesday.setBorder(new TitledBorder(new LineBorder(Color.black, 2, true), TextContent.weekEnglishText[2],
				TitledBorder.LEFT, TitledBorder.TOP, new Font("StSong", Font.BOLD, 16)));
		thursday.setBorder(new TitledBorder(new LineBorder(Color.black, 2, true), TextContent.weekEnglishText[3],
				TitledBorder.LEFT, TitledBorder.TOP, new Font("StSong", Font.BOLD, 16)));
		friday.setBorder(new TitledBorder(new LineBorder(Color.black, 2, true), TextContent.weekEnglishText[4],
				TitledBorder.LEFT, TitledBorder.TOP, new Font("StSong", Font.BOLD, 16)));

		GroupLayout groupLayout = new GroupLayout(panel);
		panel.setLayout(groupLayout);
		groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup().addGap(10).addGroup(groupLayout
				.createParallelGroup().addGroup(groupLayout.createSequentialGroup().addGap(10)
						.addGroup(groupLayout.createParallelGroup()
								.addComponent(schoolNameLabel, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(30)
								.addComponent(dateLabel, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(10)
						.addGroup(
								groupLayout.createParallelGroup()
										.addComponent(schoolNameTextField, 0, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(30)
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(yearComboBox, 0, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addGap(5)
												.addComponent(yearLabel, 0, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addGap(5)
												.addComponent(monthComboBox, 0, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addGap(5)
												.addComponent(monthLabel, 0, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addGap(5)
												.addComponent(dayComboBox, 0, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addGap(5).addComponent(dayLabel, 0, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)))
						.addGap(30).addComponent(finishButton, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(20).addComponent(monday, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(tuesday, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(wednesday, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(thursday, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(friday, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))

		);

		groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
				.addGroup(
						groupLayout.createParallelGroup(Alignment.CENTER).addGroup(groupLayout
								.createSequentialGroup().addGap(15).addGroup(groupLayout.createParallelGroup()
										.addComponent(schoolNameLabel, 0, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(
												schoolNameTextField, 0, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGap(15)
								.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
										.addComponent(dateLabel, 0, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
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
												GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup().addGap(20).addComponent(finishButton, 0,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))

				.addGap(15).addComponent(monday, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(finishButton, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(tuesday, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(wednesday, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(thursday, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(friday, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));

		jScrollPane.setPreferredSize(new Dimension(frameWidth, frameHeight));
		jScrollPane.getVerticalScrollBar().addAdjustmentListener(e -> resizeJScrollPaneState());

		frame.add(jScrollPane);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}

	public void setFrameSize(int frameWidth, int frameHeigh) {
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeigh;
	}

	public void resizeJScrollPaneState() {
		if (jScrollPane.getVerticalScrollBar().getMaximum() == 697) {
			jScrollPane.setPreferredSize(null);
		} else {
			jScrollPane.setPreferredSize(new Dimension(frameWidth, frameHeight));
		}
	}

	public void menuViewToJSONFormat() {
		JSONObject menuJSONObject = new JSONObject();
		menuJSONObject.put("schoolName", schoolNameTextField.getText());
		menuJSONObject.put("date", yearComboBox.getSelectedItem() + "/" + monthComboBox.getSelectedItem() + "/"
				+ dayComboBox.getSelectedItem());
		JSONArray dayJSONArray = new JSONArray();
		menuJSONObject.put("day", dayJSONArray);
		if (monday.getDayCheckBox().isSelected() == true) {
			JSONObject mondayJSONObject = new JSONObject();
			mondayJSONObject.put("name", monday.getDayCheckBox().getText());
			JSONObject stapleFoodJSONObject = new JSONObject();
			stapleFoodJSONObject.put("name", monday.getStapleFoodTextField().getText());
			JSONArray stapleFoodJSONArray = new JSONArray();
			stapleFoodJSONObject.put("ingredient", stapleFoodJSONArray);
			JSONObject mainCourseJSONObject = new JSONObject();
			mainCourseJSONObject.put("name", monday.getMainCourseTextField().getText());
			JSONArray mainCourseJSONArray = new JSONArray();
			for (ZoomRowInput element : monday.getMainCourseIngredientView().getZoomRowInputArrayList()) {
				if (!element.getZoomRowTextField().getText().equals(""))
					mainCourseJSONArray.add(element.getZoomRowTextField().getText());
			}
			mainCourseJSONObject.put("ingredient", mainCourseJSONArray);
			JSONObject sideDishOneJSONObject = new JSONObject();
			sideDishOneJSONObject.put("name", monday.getSideDishOneTextField().getText());
			JSONArray sideDishOneJSONArray = new JSONArray();
			for (ZoomRowInput element : monday.getSideDishOneIngredientView().getZoomRowInputArrayList()) {
				if (!element.getZoomRowTextField().getText().equals(""))
					sideDishOneJSONArray.add(element.getZoomRowTextField().getText());
			}
			sideDishOneJSONObject.put("ingredient", sideDishOneJSONArray);
			
			JSONObject sideDishSecondJSONObject = new JSONObject();
			sideDishSecondJSONObject.put("name", monday.getSideDishSecondTextField().getText());
			JSONArray sideDishSecondJSONArray = new JSONArray();
			for (ZoomRowInput element : monday.getSideDishSecondIngredientView().getZoomRowInputArrayList()) {
				if (!element.getZoomRowTextField().getText().equals(""))
					sideDishSecondJSONArray.add(element.getZoomRowTextField().getText());
			}
			sideDishSecondJSONObject.put("ingredient", sideDishOneJSONArray);
			
			JSONObject soupJSONObject = new JSONObject();
			soupJSONObject.put("name", monday.getSoupTextField().getText());
			JSONArray soupJSONArray = new JSONArray();
			for (ZoomRowInput element : monday.getSoupIngredientView().getZoomRowInputArrayList()) {
				if (!element.getZoomRowTextField().getText().equals(""))
					soupJSONArray.add(element.getZoomRowTextField().getText());
			}
			soupJSONObject.put("ingredient", sideDishOneJSONArray);
			
			JSONArray acceptanceJSONArray=new JSONArray();
			for (ZoomRowInput element : monday.getAcceptanceView().getZoomRowInputArrayList()) {
				if (!element.getZoomRowTextField().getText().equals(""))
					acceptanceJSONArray.add(element.getZoomRowTextField().getText());
			}
			
			mondayJSONObject.put("stapleFood",stapleFoodJSONObject);
			mondayJSONObject.put("mainCourse", mainCourseJSONObject);
			mondayJSONObject.put("sideDishOne",sideDishOneJSONObject);
			mondayJSONObject.put("sideDishSecond", sideDishSecondJSONObject);
			mondayJSONObject.put("soup", soupJSONObject);
			mondayJSONObject.put("acceptance", acceptanceJSONArray);
			System.out.println(mondayJSONObject);
		}
	}

	public JTextField getSchoolNameTextField() {
		return schoolNameTextField;
	}

	public JComboBox getYearComboBox() {
		return yearComboBox;
	}

	public JComboBox getMonthComboBox() {
		return monthComboBox;
	}

	public JComboBox getDayComboBox() {
		return dayComboBox;
	}

	public DayView getTuesday() {
		return tuesday;
	}

	public DayView getWednesday() {
		return wednesday;
	}

	public DayView getThursday() {
		return thursday;
	}

	public void setSchoolNameTextField(JTextField schoolNameTextField) {
		this.schoolNameTextField = schoolNameTextField;
	}

	public void setYearComboBox(JComboBox yearComboBox) {
		this.yearComboBox = yearComboBox;
	}

	public void setMonthComboBox(JComboBox monthComboBox) {
		this.monthComboBox = monthComboBox;
	}

	public void setDayComboBox(JComboBox dayComboBox) {
		this.dayComboBox = dayComboBox;
	}

	public DayView getMondy() {
		return monday;
	}

	public void setMondy(DayView mondy) {
		this.monday = mondy;
	}

	public void setTuesday(DayView tuesday) {
		this.tuesday = tuesday;
	}

	public void setWednesday(DayView wednesday) {
		this.wednesday = wednesday;
	}

	public void setThursday(DayView thursday) {
		this.thursday = thursday;
	}

	public DayView getFriday() {
		return friday;
	}

	public void setFriday(DayView friday) {
		this.friday = friday;
	}

}
