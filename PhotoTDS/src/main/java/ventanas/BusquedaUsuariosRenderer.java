package ventanas;


import java.awt.Component;
import java.awt.Image;


import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;


import dominio.Usuario;

public class BusquedaUsuariosRenderer extends JLabel implements ListCellRenderer<Usuario> {

	private static final long serialVersionUID = 1L;
	private BusquedaUsuarios bu;
	private String usuario = null;

	public BusquedaUsuariosRenderer(BusquedaUsuarios bu) {
		this.bu = bu;
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
		setText(String.format("%s %s", usuario.getNombre(), usuario.getApellidos()));

		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
			
			this.usuario = usuario.getUsuario();

			bu.cerrar();
			PerfilUsuario perfilUsuario = new PerfilUsuario(usuario);
			perfilUsuario.frmPhototds.setVisible(true);

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
