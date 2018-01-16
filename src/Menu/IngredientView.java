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

import javax.swing.ImageIcon;

public class IngredientView extends JPanel{
	JFrame jFrame;
	JPanel currentJPanel,previousJPanel;
	JButton ingredientButton;
	private ArrayList<IngredientInput> ingredientInputArrayList;

	private IngredientView(JFrame jFrame) {
		this.jFrame=jFrame;
		initIngredient();
	}

	public static IngredientView getIngredientObject(JFrame jFrame) {
		return new IngredientView(jFrame);
	}

	public void initIngredient() {
		ingredientInputArrayList = new ArrayList<>();

		initGroupLayout();
		ingredientButton.doClick();
	}

	private void initGroupLayout() {
		previousJPanel=currentJPanel;
		currentJPanel = new JPanel();
		ingredientButton = new JButton(TextContent.ingredientText);
		GroupLayout groupLayout = new GroupLayout(currentJPanel);
		currentJPanel.setLayout(groupLayout);
		
		SequentialGroup sequentialGroup = groupLayout.createSequentialGroup();
		ParallelGroup parallelGroup = groupLayout.createParallelGroup();

		groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
				.addComponent(ingredientButton, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGroup(parallelGroup));

		groupLayout
				.setVerticalGroup(
						groupLayout.createParallelGroup()
								.addGroup(groupLayout.createSequentialGroup().addComponent(ingredientButton, 0,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(sequentialGroup));
		
		ingredientButton.addActionListener(e -> ingredientButtonOnClick(groupLayout, parallelGroup, sequentialGroup));
		
		for (IngredientInput ingredientInput : ingredientInputArrayList) {
			addIngredient(ingredientInput, groupLayout, parallelGroup, sequentialGroup);
		}
		
		this.add(currentJPanel);
		
	}

	private void addIngredient(IngredientInput ingredientInput, GroupLayout groupLayout, ParallelGroup parallelGroup,
			SequentialGroup sequentialGroup) {
		JTextField ingredientTextFiel = ingredientInput.ingredientTextField;
		JButton cancelButton = ingredientInput.cancelButton;

		parallelGroup.addGroup(groupLayout.createSequentialGroup()
				.addComponent(ingredientTextFiel, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(cancelButton, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));

		sequentialGroup.addGroup(groupLayout.createParallelGroup()
				.addComponent(ingredientTextFiel, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(cancelButton, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));
	}

	private void ingredientButtonOnClick(GroupLayout groupLayout, ParallelGroup parallelGroup,
			SequentialGroup sequentialGroup) {
		JTextField ingredientTextFiel = new JTextField(10);
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

		IngredientInput ingredientInput = new IngredientInput(ingredientTextFiel, cancelButton);
		ingredientInputArrayList.add(ingredientInput);
		cancelButton.addActionListener(e -> cancelButtonOnClick(ingredientInput));
	
		rePaint();
	}

	private void cancelButtonOnClick(IngredientInput ingredientInput) {
		ingredientInputArrayList.remove(ingredientInput);
		rePaint();
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
	
	private class IngredientInput {
		JTextField ingredientTextField;
		JButton cancelButton;

		public IngredientInput(JTextField ingredientTextField, JButton cancelButton) {
			this.ingredientTextField = ingredientTextField;
			this.cancelButton = cancelButton;
		}
	}
}

