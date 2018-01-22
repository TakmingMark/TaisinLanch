package Component;
import javax.swing.JButton;
import javax.swing.JTextField;

public class ZoomRowInput {
	JTextField zoomRowTextField;
	JButton cancelButton;

	public ZoomRowInput(JTextField zoomRowTextField, JButton cancelButton) {
		this.zoomRowTextField = zoomRowTextField;
		this.cancelButton = cancelButton;
	}

	public JTextField getZoomRowTextField() {
		return zoomRowTextField;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setZoomRowTextField(JTextField zoomRowTextField) {
		this.zoomRowTextField = zoomRowTextField;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}
	
	
}