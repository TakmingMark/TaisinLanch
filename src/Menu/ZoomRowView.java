package Menu;

import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.zip.Inflater;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;

import Component.TextContent;
import Component.ZoomRowInput;

import javax.swing.ImageIcon;

public class ZoomRowView extends JPanel{
	JFrame jFrame;
	JPanel currentJPanel,previousJPanel;
	JButton insertButton;
	private ArrayList<ZoomRowInput> zoomRowInputArrayList;
	private String insertButtonText;
	private ZoomRowView(JFrame jFrame,String insertButtonText) {
		this.jFrame=jFrame;
		this.insertButtonText=insertButtonText;
		initZoomRowView(this.insertButtonText);
	}

	public static ZoomRowView getZoomRowViewObject(JFrame jFrame,String insertButtonText) {
		return new ZoomRowView(jFrame,insertButtonText);
	}

	public void initZoomRowView(String insertButtonText) {
		zoomRowInputArrayList = new ArrayList<>();

		initGroupLayout(insertButtonText);
		insertButton.doClick();
	}

	private void initGroupLayout(String insertButtonText) {
		previousJPanel=currentJPanel;
		currentJPanel = new JPanel();
		insertButton = new JButton(insertButtonText);
		GroupLayout groupLayout = new GroupLayout(currentJPanel);
		currentJPanel.setLayout(groupLayout);
		
		SequentialGroup sequentialGroup = groupLayout.createSequentialGroup();
		ParallelGroup parallelGroup = groupLayout.createParallelGroup();

		groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
				.addComponent(insertButton, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(11)
				.addGroup(parallelGroup)
				);

		groupLayout
				.setVerticalGroup(
						groupLayout.createParallelGroup()
								.addGroup(groupLayout.createSequentialGroup().addComponent(insertButton, 0,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(sequentialGroup));
		
		insertButton.addActionListener(e -> insertButtonOnClick(groupLayout, parallelGroup, sequentialGroup));

		for (ZoomRowInput  zoomRowInput : zoomRowInputArrayList) {
			addIngredient(zoomRowInput, groupLayout, parallelGroup, sequentialGroup);
		}
		
		this.add(currentJPanel);
		
	}

	private void addIngredient(ZoomRowInput zoomRowInput, GroupLayout groupLayout, ParallelGroup parallelGroup,
			SequentialGroup sequentialGroup) {
		JTextField zoomRowInputTextFiel = zoomRowInput.getZoomRowTextField();
		JButton cancelButton = zoomRowInput.getCancelButton();
		
		
		parallelGroup.addGroup(groupLayout.createSequentialGroup()
				.addComponent(zoomRowInputTextFiel, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(5)
				.addComponent(cancelButton, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));
		sequentialGroup.addGap(3);
		sequentialGroup.addGroup(groupLayout.createParallelGroup()
				.addComponent(zoomRowInputTextFiel, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(cancelButton, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));
	}

	private void insertButtonOnClick(GroupLayout groupLayout, ParallelGroup parallelGroup,
			SequentialGroup sequentialGroup) {
		JTextField zoomRowTextField = new JTextField(8);
		JButton cancelButton = new JButton();
		try {
			 Image img = ImageIO.read(new File("img/cancelButton.png"));
			 cancelButton.setIcon(new ImageIcon(img));
			 cancelButton.setPreferredSize(new Dimension(20, 20));
			 cancelButton.setOpaque(false);
			 cancelButton.setContentAreaFilled(false);
			 cancelButton.setBorderPainted(false);
		} catch (Exception ex) {
			System.out.println(ex);
		}

		ZoomRowInput zoomRowInput = new ZoomRowInput(zoomRowTextField, cancelButton);
		zoomRowInputArrayList.add(zoomRowInput);
		cancelButton.addActionListener(e -> cancelButtonOnClick(zoomRowInput));
	
		rePaint();
	}

	private void cancelButtonOnClick(ZoomRowInput zoomRowInput) {
		zoomRowInputArrayList.remove(zoomRowInput);
		rePaint();
	}
	
	public void rePaint() {
		initGroupLayout(insertButtonText);
		this.remove(previousJPanel);
		this.add(currentJPanel);
		this.validate();
		this.repaint();
		jFrame.validate();
		jFrame.repaint();
		jFrame.pack();
	}
	
	public ArrayList<ZoomRowInput> getZoomRowInputArrayList() {
		return zoomRowInputArrayList;
	}

	public void setZoomRowInputArrayList(ArrayList<ZoomRowInput> zoomRowInputArrayList) {
		this.zoomRowInputArrayList = zoomRowInputArrayList;
	}
	
	public void testInsertZoomRowInput(String zoomRowTextFieldText) {
		insertButton.doClick();
		zoomRowInputArrayList.get(zoomRowInputArrayList.size()-2).getZoomRowTextField().setText(zoomRowTextFieldText);
	}
}

