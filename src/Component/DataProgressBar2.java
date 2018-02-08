package Component;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class DataProgressBar2 extends SwingWorker{

	private JFrame frame;
	private JDialog jDialog;
	private JProgressBar jProgressBar;
	private DataProgressBar2(JFrame frame) {
		this.frame = frame;
		initDataProgressBar();
	}

	public static DataProgressBar2 getDataProgressBarObject(JFrame frame) {
		return new DataProgressBar2(frame);
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
	
	public void start() {
		Thread thread = new Thread(new Runnable() {
			public void run() {
		
			}
		});
		thread.start();
	}
	
	public void addProgressRate() {
		jProgressBar.setValue(jProgressBar.getValue()+25);
		if(jProgressBar.getValue()==jProgressBar.getMaximum())
			end();
	}
	
	private void end() {
		jDialog.setVisible(false);
	}

	@Override
	protected Object doInBackground() throws Exception {
		jProgressBar.setValue(0);
		jDialog.setVisible(true);
		jProgressBar.setIndeterminate(true);
		 return "Done.";
	}
}
