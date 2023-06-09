package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import javax.swing.border.LineBorder;

import dominio.Comentario;
import dominio.Foto;

import java.awt.Toolkit;
import java.util.List;

public class VerComentarios extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public VerComentarios(Foto foto) {

		setIconImage(Toolkit.getDefaultToolkit().getImage(VerComentarios.class.getResource("/recursos/image.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 500);
		contentPane = new JPanel();

		contentPane.setLayout(new BorderLayout(0, 0));

		List<Comentario> comentarios = foto.getComentarios();

		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setSize(new Dimension(300, 600));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(280, 450));

		panelCentro.add(scrollPane);

		JPanel panelComentarios = new JPanel();
		panelComentarios.setSize(new Dimension(550, 400));
		panelComentarios.setPreferredSize(new Dimension(280, 2000));
		scrollPane.setViewportView(panelComentarios);
		panelComentarios.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		for (Comentario comentario : comentarios) {
			JPanel panelPublicacion = new JPanel();
			panelPublicacion.setBackground(Color.WHITE);
			panelPublicacion.setBorder(new LineBorder(new Color(0, 0, 0)));
			panelPublicacion.setPreferredSize(new Dimension(240, 80));
			panelPublicacion.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

			JLabel lblFoto = new JLabel();
			lblFoto.setAlignmentY(Component.TOP_ALIGNMENT);
			lblFoto.setSize(new Dimension(5, 5));
			lblFoto.setBorder(new LineBorder(new Color(0, 0, 0)));

			ImageIcon icon = new ImageIcon(comentario.getUser().getFotoPerfil());
			Image img = icon.getImage();
			Image otraimg = img.getScaledInstance(25, 25, java.awt.Image.SCALE_DEFAULT);
			ImageIcon otroicon = new ImageIcon(otraimg);

			lblFoto.setIcon(otroicon);
			panelPublicacion.add(lblFoto);

			JLabel user = new JLabel(comentario.getUser().getUsuario() + ":");
			panelPublicacion.add(user);

			JTextArea comentarioArea = new JTextArea();
			comentarioArea.setText(comentario.getTexto());
			comentarioArea.setEditable(false);

			panelPublicacion.add(comentarioArea);

			panelComentarios.add(panelPublicacion);
		}

		setContentPane(contentPane);
	}

}
