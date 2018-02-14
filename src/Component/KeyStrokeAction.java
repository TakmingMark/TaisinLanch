package Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;

public class KeyStrokeAction extends AbstractAction{

	ActionListener actionListener;

    public KeyStrokeAction(ActionListener customActionListener) {
        this.actionListener = customActionListener;
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		actionListener.actionPerformed(arg0);
	}
	
}