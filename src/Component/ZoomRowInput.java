package Component;
import javax.swing.JButton;
import javax.swing.JTextField;

public class ZoomRowInput {
	JTextField textField;
	JButton cancelButton;

	public ZoomRowInput(JTextField textField, JButton cancelButton) {
		this.textField = textField;
		this.cancelButton = cancelButton;
	}

	public JTextField getTextField() {
		return textField;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}
	
	
}