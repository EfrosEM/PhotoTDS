package ventanas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PopMenuFotoListener extends MouseAdapter {
	PopUpMenuFoto menu;
	
	public void mousePressed(MouseEvent e) {
		if (e.isPopupTrigger())
			mostrarMenu(e);
	}

	public void mouseReleased(MouseEvent e) {
		if (e.isPopupTrigger())
			mostrarMenu(e);
	}

	private void mostrarMenu(MouseEvent e) {
		menu = new PopUpMenuFoto();
		menu.show(e.getComponent(), e.getX(), e.getY());
	}
}
