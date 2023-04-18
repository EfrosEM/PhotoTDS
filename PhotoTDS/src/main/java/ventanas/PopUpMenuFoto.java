package ventanas;

import java.awt.Component;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class PopUpMenuFoto {
	
	JPopupMenu menu;
	
	public void show(Component component, int x, int y) {

		menu = new JPopupMenu(); 
		JMenuItem ver = new JMenuItem("Ver Foto"); 
		JMenuItem elim = new JMenuItem("Eliminar");
		
		menu.add(ver); 
		menu.add(elim); 
		
		menu.setVisible(true);
		menu.setLocation(x+300, y+350);
		
	} 
	
	 
	
}
