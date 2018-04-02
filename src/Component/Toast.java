package Component;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Toast {

	public static WarningToast getWarningToastObject(String message) {
		return new WarningToast(message);
	}

	public static int geYesOrNoToastObject(String message) {
		return new YesOrNoToast(message).getOptionResult();
	}
}

class WarningToast {
	public WarningToast(String message) {
		JFrame toastFrame = new JFrame();
		JOptionPane.showMessageDialog(toastFrame, message, "Warning", JOptionPane.WARNING_MESSAGE);
		toastFrame.setDefaultCloseOperation(toastFrame.EXIT_ON_CLOSE);
	}
}

class YesOrNoToast {
	private int dialogResult;

	public YesOrNoToast(String message) {
		JFrame toastFrame = new JFrame();
		int dialogButton = JOptionPane.YES_NO_OPTION;
		dialogResult = JOptionPane.showConfirmDialog(toastFrame, message, "YesOrNoOptioning", dialogButton);
		toastFrame.setDefaultCloseOperation(toastFrame.EXIT_ON_CLOSE);
	}

	public int getOptionResult() {
		return dialogResult;
	}
}