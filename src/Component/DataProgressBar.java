package Component;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class DataProgressBar {

	private JFrame frame;
	private JDialog jDialog;
	private JProgressBar jProgressBar;
	private DataProgressBar(JFrame frame) {
		this.frame = frame;
		initDataProgressBar();
	}

	public static DataProgressBar getDataProgressBarObject(JFrame frame) {
		return new DataProgressBar(frame);
	}

	private void initDataProgressBar() {
		jDialog = new JDialog(frame, "Data Progress", true);
		jProgressBar = new JProgressBar(0, 100);
		jDialog.add(BorderLayout.CENTER, jProgressBar);
		jDialog.add(BorderLayout.NORTH, new JLabel("資料處理中，請稍候..."));
		jDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		jDialog.setSize(300, 75);
		jDialog.setLocationRelativeTo(frame);
	}
	
	public void run() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				jDialog.setVisible(true);
			}
		}).start();
	}
	
	public void addProgressRate() {
		jProgressBar.setValue(jProgressBar.getValue()+25);
		if(jProgressBar.getValue()==jProgressBar.getMaximum())
			end();
	}
	
	private void end() {
		jDialog.setVisible(false);
	}
}