package View;

import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Component.IngredientComponent;

public class ZoomRowView extends JPanel {
	JFrame jFrame;
	JPanel currentJPanel, previousJPanel;
	JButton insertButton;
	private ArrayList<RowView> rowViewArrayList;
	private String insertButtonText;

	private ZoomRowView(JFrame jFrame, String insertButtonText) {
		this.jFrame = jFrame;
		this.insertButtonText = insertButtonText;
		initZoomRowView();

	}

	public static ZoomRowView getZoomRowViewObject(JFrame jFrame, String insertButtonText) {
		return new ZoomRowView(jFrame, insertButtonText);
	}

	private void initZoomRowView() {
		rowViewArrayList = new ArrayList<>();

		initGroupLayout();
		setDefaultRowView();
	}

	private void setDefaultRowView() {
		insertButton.doClick();
	}

	private void initGroupLayout() {
		previousJPanel = currentJPanel;
		currentJPanel = new JPanel();
		insertButton = new JButton(insertButtonText);

		GroupLayout groupLayout = new GroupLayout(currentJPanel);
		currentJPanel.setLayout(groupLayout);

		SequentialGroup sequentialGroup = groupLayout.createSequentialGroup();
		ParallelGroup parallelGroup = groupLayout.createParallelGroup();

		groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
				.addComponent(insertButton, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addGap(11)
				.addGroup(parallelGroup));

		groupLayout
				.setVerticalGroup(
						groupLayout.createParallelGroup()
								.addGroup(groupLayout.createSequentialGroup().addComponent(insertButton, 0,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(sequentialGroup));

		insertButton.addActionListener(e -> pressInsertButton(groupLayout, parallelGroup, sequentialGroup));

		for (RowView rowView : rowViewArrayList) {
			addRowViewToZoomRowView(rowView, groupLayout, parallelGroup, sequentialGroup);
		}

		this.add(currentJPanel);
	}

	private void addRowViewToZoomRowView(RowView rowView, GroupLayout groupLayout, ParallelGroup parallelGroup,
			SequentialGroup sequentialGroup) {

		parallelGroup.addGroup(groupLayout.createSequentialGroup().addComponent(rowView, 0,
				GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));
		sequentialGroup.addGap(3);
		sequentialGroup.addGroup(groupLayout.createParallelGroup().addComponent(rowView, 0,
				GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));
	}

	private void pressInsertButton(GroupLayout groupLayout, ParallelGroup parallelGroup,
			SequentialGroup sequentialGroup) {

		RowView rowView = RowView.getRowViewObject();
		rowView.getCancelButton().addActionListener(e -> pressCancelButton(rowView));
		rowViewArrayList.add(rowView);

		rePaint();
	}

	private void pressCancelButton(RowView rowView) {
		if (checkMinimumRowView()) {
			rowViewArrayList.remove(rowView);
			rePaint();
		}
	}

	private boolean checkMinimumRowView() {
		if (rowViewArrayList.size() > 1)
			return true;
		else
			return false;
	}

	public void insertDataToZoomRowView(IngredientComponent ingredient) {
		insertButton.doClick();
		rowViewArrayList.get(rowViewArrayList.size() - 2).getNameTextField().setText(ingredient.getName());
		rowViewArrayList.get(rowViewArrayList.size() - 2).getUnitTextField().setText(ingredient.getUnit());
	}

	public boolean isExistData() {
		if (rowViewArrayList.get(0).getNameTextField().getText().equals(""))
			return false;
		else
			return true;
	}
	
	public void rePaint() {
		initGroupLayout();
		this.remove(previousJPanel);
		this.add(currentJPanel);
		this.validate();
		this.repaint();
		jFrame.validate();
		jFrame.repaint();
		jFrame.pack();
	}

	public ArrayList<RowView> getRowViewArrayList() {
		return rowViewArrayList;
	}

	public void setRowViewArrayList(ArrayList<RowView> rowViewArrayList) {
		this.rowViewArrayList = rowViewArrayList;
	}

	
}
