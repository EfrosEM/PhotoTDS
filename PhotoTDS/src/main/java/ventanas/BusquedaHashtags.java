package ventanas;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import controlador.ControladorPhotoTDS;
import dominio.Foto;

public class BusquedaHashtags {

	protected JFrame busquedaHashtags;
	private ControladorPhotoTDS controlador;
	private List<String> tokens;
	private BusquedaHashtagsRenderer renderer;
	private JFrame inicio;

	public BusquedaHashtags(List<String> tokens, JFrame inicio) {
		this.tokens = new ArrayList<String>(tokens);
		this.inicio = inicio;
		controlador = ControladorPhotoTDS.getUnicaInstancia();
		renderer = new BusquedaHashtagsRenderer(this);
		initialize();
	}

	public void initialize() {
		busquedaHashtags = new JFrame();
		busquedaHashtags.setTitle("PhotoTDS");
		busquedaHashtags.setResizable(false);
		busquedaHashtags.setIconImage(
				Toolkit.getDefaultToolkit().getImage(PerfilUsuario.class.getResource("/recursos/image.png")));
		busquedaHashtags.setBounds(100, 100, 150, 150);
		busquedaHashtags.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		busquedaHashtags.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(150, 150));
		busquedaHashtags.getContentPane().add(panel);

		JList<String> listaHashtags = new JList<String>(getListaHashtags());
		listaHashtags.setCellRenderer(renderer);
		listaHashtags.setPreferredSize(new Dimension(150, 150));
		panel.add(listaHashtags);
	}

	private DefaultListModel<String> getListaHashtags() {
		DefaultListModel<String> lista = new DefaultListModel<>();

		for (Foto f : controlador.recuperarTodasFotos()) {
			for (String token : tokens) {
				Pattern patron = Pattern.compile(token);
				
				for (String hashtag : f.getHashtags()) {
					Matcher matcher = patron.matcher(hashtag);
					if (matcher.find()) {
						lista.addElement(" " + hashtag + " -> " + String.valueOf(f.getUser().getSeguidores().size()));
					}

				}

			}
		}

		return lista;
	}

	public void cerrar() {
		busquedaHashtags.dispose();
		inicio.dispose();
	}

}
