package ventanas;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import controlador.ControladorPhotoTDS;
import dominio.Usuario;

public class BusquedaUsuarios {

	protected JFrame busquedaUsuarios;
	private ControladorPhotoTDS controladorPhotoTDS;
	private String token;
	private BusquedaUsuariosRenderer renderer;
	private JFrame inicio;

	/**
	 * Create the frame.
	 */
	public BusquedaUsuarios(String token, JFrame inicio) {
		this.token = token;
		this.inicio = inicio;
		controladorPhotoTDS = ControladorPhotoTDS.getUnicaInstancia();
		renderer = new BusquedaUsuariosRenderer(this);
		initialize();
	}
	
	public void initialize() {
		busquedaUsuarios = new JFrame();
		busquedaUsuarios.setTitle("PhotoTDS");
		busquedaUsuarios.setResizable(false);
		busquedaUsuarios.setIconImage(Toolkit.getDefaultToolkit().getImage(PerfilUsuario.class.getResource("/recursos/image.png")));
		busquedaUsuarios.setBounds(100, 100, 300, 300);
		busquedaUsuarios.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		busquedaUsuarios.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(295, 265));
		busquedaUsuarios.getContentPane().add(panel);
		
		JList<Usuario> listaUsuarios = new JList<Usuario>(getListaUsuarios());
		listaUsuarios.setCellRenderer(renderer);
		listaUsuarios.setPreferredSize(new Dimension(295, 260));
		panel.add(listaUsuarios);
	}
	
	private DefaultListModel<Usuario> getListaUsuarios(){
		DefaultListModel<Usuario> lista = new DefaultListModel<>();
		
		Pattern patron = Pattern.compile(token);
		for (Usuario u : controladorPhotoTDS.recuperarTodosUsuarios()) {
			Matcher matcher = patron.matcher(u.getNombre() + u.getApellidos());
			if (matcher.find()) {
				lista.addElement(u);
			}
		}
		
		
		return lista;
	}
	
	public void cerrar() {
		busquedaUsuarios.dispose();
		inicio.dispose();
	}
	
	@SuppressWarnings("unused")
	private static ListCellRenderer<? super Usuario> createListRenderer() {
		return null;
		/*return new DefaultListCellRenderer() {
		

			private static final long serialVersionUID = 1L;
			private Color background = new Color(0, 100, 255, 25);
			private Color defaultBackground = (Color) UIManager.getColor("List.background");
			
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				
				if (c instanceof JLabel) {
					JLabel label = (JLabel) c;
					Usuario usuario = (Usuario) value;
					
					ImageIcon icon = new ImageIcon(usuario.getFotoPerfil());
					Image img = icon.getImage();
					Image otraimg = img.getScaledInstance(50, 50, java.awt.Image.SCALE_DEFAULT);
					ImageIcon otroicon = new ImageIcon(otraimg);
					
					label.setIcon(otroicon);
					label.setText(String.format("%s %s",  usuario.getNombre(), usuario.getApellidos()));
					
					if (isSelected) {
						//label.setBackground(index % 2 == 0 ? background : defaultBackground);
						
					}
				}
				
				return c;
			}
			
			
		};*/
	}

}


