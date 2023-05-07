package ventanas;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class PopUpMenuFoto {
	
	JPopupMenu menu;
	
	public void show(Component component, int x, int y) {

		menu = new JPopupMenu(); 
		JMenuItem ver = new JMenuItem("Ver Foto"); 
		JMenuItem elim = new JMenuItem("Eliminar");
		elim.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		menu.add(ver); 
		menu.add(elim); 
		
		menu.setVisible(true);
		menu.setLocation(x+50, y+350);
		
	}
	
	public void close() {
		menu.setVisible(false);
	}
	
	 
	
}
