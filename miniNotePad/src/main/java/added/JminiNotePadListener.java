package added;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class JminiNotePadListener extends JFrame {
	/**
	 * 
	 */
	public static MiniNotePadData data = null;

	private static final long serialVersionUID = 1L;

	public void initSystemButtonListener() {
		//
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				closeWindow(event);
			}
		});
	}

	public void initEditListener() {
		data.getTextArea().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					data.getPopUpMenu().show(data.getEditMenu(), e.getX(), e.getY());
				}
			}

			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					data.getPopUpMenu().setVisible(false);
				}
			}
		});
	}

	protected void closeWindow(WindowEvent event) {

	}
}
