package Component;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Toast {
	public Toast(){
		JFrame jFrame2=new JFrame();
		JOptionPane.showMessageDialog(jFrame2,
			    "單位不能為國字，請輸入數字",
			    "Warning",
			    JOptionPane.WARNING_MESSAGE);
	}
}
