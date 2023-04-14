package ventanas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;

import javax.swing.JFrame;


import dominio.Usuario;

public class BusquedaUsuariosRenderer extends JLabel implements ListCellRenderer<Usuario>{

	private static final long serialVersionUID = 1L;
	private JFrame bu;
	private String usuario = null;
	
	public BusquedaUsuariosRenderer() {
		setOpaque(true);
	}
	

	@Override
	public Component getListCellRendererComponent(JList<? extends Usuario> list, Usuario value, int index,
			boolean isSelected, boolean cellHasFocus) {
		Usuario usuario = (Usuario) value;
		
		ImageIcon icon = new ImageIcon(usuario.getFotoPerfil());
		Image img = icon.getImage();
		Image otraimg = img.getScaledInstance(50, 50, java.awt.Image.SCALE_DEFAULT);
		ImageIcon otroicon = new ImageIcon(otraimg);
		
		setIcon(otroicon);
		setText(String.format("%s %s",  usuario.getNombre(), usuario.getApellidos()));
		
		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
			//bu.setVisible(false);
			System.out.println("Usuario elegido: " + usuario.getUsuario());
			this.usuario = usuario.getUsuario();
			
		} else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}
	
	return this;
	}
	
	public String getUsuario() {
		return this.usuario;
	}
}
