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

import Component.ComponentListenerBuilder;
import Component.Food;
import Component.TextContent;

public class MenuView {
	private JFrame frame;
	private JScrollPane jScrollPane;
	private JPanel panel;
	private JLabel schoolNameLabel, dateLabel, yearLabel, monthLabel, dayLabel;
	private JTextField schoolNameTextField;
	private JComboBox yearComboBox, monthComboBox, dayComboBox;
	private DayView mondy, tuesday, wednesday, thursday, friday;

	private int frameWidth,frameHeight;
	private int recordFrameHeight = 0;

	private MenuView(int frameWidth,int frameHeigh) {
		this.frameWidth=frameWidth;
		this.frameHeight=frameHeigh;
		initMenuView();
	}

	public static MenuView getMenuViewObject(int frameWidth,int frameHeight) {
		
		return new MenuView(frameWidth, frameHeight);
	}

	private void initMenuView() {
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

		mondy = new DayView(frame, TextContent.weekChineseText[0]);
		tuesday = new DayView(frame, TextContent.weekChineseText[1]);
		wednesday = new DayView(frame, TextContent.weekChineseText[2]);
		thursday = new DayView(frame, TextContent.weekChineseText[3]);
		friday = new DayView(frame, TextContent.weekChineseText[4]);

		mondy.setBorder(new TitledBorder(new LineBorder(Color.black, 2, true), TextContent.weekEnglishText[0],
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
		groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
				.addGap(10)
				.addGroup(groupLayout
				.createParallelGroup()
				.addGroup(groupLayout.createSequentialGroup().addGap(10)
						.addGroup(groupLayout.createParallelGroup()
								.addComponent(schoolNameLabel, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(30)
								.addComponent(dateLabel, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(10).addGroup(
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
														GroupLayout.PREFERRED_SIZE))))
				.addGap(20).addComponent(mondy, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(tuesday, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(wednesday, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(thursday, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(friday, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))

		);

		groupLayout.setVerticalGroup(groupLayout.createSequentialGroup().addGap(15)
				.addGroup(groupLayout.createParallelGroup()
						.addComponent(schoolNameLabel, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(schoolNameTextField, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(15)
				.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
						.addComponent(dateLabel, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(yearComboBox, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(yearLabel, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(monthComboBox, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(monthLabel, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(dayComboBox, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(dayLabel, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(15)
				.addComponent(mondy, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				
				.addComponent(tuesday, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(wednesday, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(thursday, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(friday, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));
		jScrollPane.setPreferredSize(new Dimension(frameWidth, frameHeight));
		frame.addComponentListener(new ComponentListenerBuilder().componentResized(e -> resizeFrameState()).build());
		jScrollPane.addComponentListener(
				new ComponentListenerBuilder().componentResized(e -> resizeJScrollPaneState()).build());
		jScrollPane.addComponentListener(
				new ComponentListenerBuilder().componentHidden(e -> resizeJScrollPaneState()).build());
		jScrollPane.addComponentListener(
				new ComponentListenerBuilder().componentMoved(e -> resizeJScrollPaneState()).build());
		jScrollPane.addComponentListener(
				new ComponentListenerBuilder().componentShown(e -> resizeJScrollPaneState()).build());

		jScrollPane.getVerticalScrollBar().addAdjustmentListener(e -> resizeJScrollPaneState());

		frame.add(jScrollPane);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}

	public void resizeFrameState() {

	}

	public void resizeJScrollPaneState() {
		
		System.out.println(frame.getWidth());
		System.out.println(frame.getHeight());
		if (jScrollPane.getVerticalScrollBar().getMaximum() == 697) {
			jScrollPane.setPreferredSize(null);
		} else {
			jScrollPane.setPreferredSize(new Dimension(frameWidth, frameHeight));
		}
	}
}
