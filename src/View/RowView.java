package View;

import java.awt.Dimension;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RowView extends JPanel {
	JTextField nameTextField, unitTextField;
	JButton cancelButton;

	private RowView() {
		initRowView();
		cancelSystemCallAboutKeyPress();
	}

	public static RowView getRowViewObject() {
		return new RowView();
	}

	private void initRowView() {
		nameTextField = new JTextField(4);
		unitTextField = new JTextField(3);
		cancelButton = new JButton();

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

		GroupLayout groupLayout = new GroupLayout(this);
		this.setLayout(groupLayout);

		groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
				.addComponent(nameTextField, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(unitTextField, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addGap(5)
				.addComponent(cancelButton, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));

		groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup()
						.addComponent(nameTextField, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(unitTextField, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cancelButton, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));

	}

	private void cancelSystemCallAboutKeyPress() {
		nameTextField.setFocusTraversalKeysEnabled(false);
		unitTextField.setFocusTraversalKeysEnabled(false);
	}
	
	public JTextField getNameTextField() {
		return nameTextField;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setNameTextField(JTextField textField) {
		this.nameTextField = textField;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}

	public JTextField getUnitTextField() {
		return unitTextField;
	}

	public void setUnitTextField(JTextField unitTextField) {
		this.unitTextField = unitTextField;
	}

}