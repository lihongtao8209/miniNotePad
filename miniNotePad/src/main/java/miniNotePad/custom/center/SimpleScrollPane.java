package miniNotePad.custom.center;

import java.awt.Component;

import javax.swing.JScrollPane;

public class SimpleScrollPane extends JScrollPane {

	public SimpleScrollPane() {
		
	}

	public SimpleScrollPane(Component view) {
		super(view);
		
	}

	public SimpleScrollPane(int vsbPolicy, int hsbPolicy) {
		super(vsbPolicy, hsbPolicy);
		
	}

	public SimpleScrollPane(Component view, int vsbPolicy, int hsbPolicy) {
		super(view, vsbPolicy, hsbPolicy);
		
	}
	
	

}
