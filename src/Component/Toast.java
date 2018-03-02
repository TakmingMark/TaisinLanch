package Component;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Toast {
	public Toast(String message) {
		JFrame jFrame2 = new JFrame();
		JOptionPane.showMessageDialog(jFrame2, message, "Warning", JOptionPane.WARNING_MESSAGE);
	}
}
