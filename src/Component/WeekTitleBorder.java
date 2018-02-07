package Component;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

/**
 * 
 * @reference https://www.javalobby.org//java/forums/t33048.html
 *
 */

public class WeekTitleBorder implements Border, MouseListener, SwingConstants {
	int offset = 5;

	Component component;
	JComponent container;
	Rectangle rectangle;
	Border border;

	public WeekTitleBorder(Component component, JComponent container, Border border) {
		this.component = component;
		this.container = container;
		this.border = border;
		container.addMouseListener(this);
	}

	public boolean isBorderOpaque() {
		return true;
	}

	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		Insets borderInsets = border.getBorderInsets(c);
		Insets insets = getBorderInsets(c);
		int temp = (insets.top - borderInsets.top) / 2;
		border.paintBorder(c, g, x, y + temp, width, height - temp);
		Dimension size = component.getPreferredSize();
		rectangle = new Rectangle(offset, 0, size.width, size.height);
		SwingUtilities.paintComponent(g, component, (Container) c, rectangle);
	}

	public Insets getBorderInsets(Component c) {
		Dimension size = component.getPreferredSize();
		Insets insets = border.getBorderInsets(c);
		insets.top = Math.max(insets.top, size.height);
		return insets;
	}

	private void dispatchEvent(MouseEvent me) {
		if (rectangle != null && rectangle.contains(me.getX(), me.getY())) {
			Point pt = me.getPoint();
			pt.translate(-offset, 0);
			component.setBounds(rectangle);
			component.dispatchEvent(new MouseEvent(component, me.getID(), me.getWhen(), me.getModifiers(), pt.x, pt.y,
					me.getClickCount(), me.isPopupTrigger(), me.getButton()));
			if (!component.isValid())
				container.repaint();
		}
	}

	public void mouseClicked(MouseEvent me) {
		dispatchEvent(me);
	}

	public void mouseEntered(MouseEvent me) {
		dispatchEvent(me);
	}

	public void mouseExited(MouseEvent me) {
		dispatchEvent(me);
	}

	public void mousePressed(MouseEvent me) {
		dispatchEvent(me);
	}

	public void mouseReleased(MouseEvent me) {
		dispatchEvent(me);
	}
}