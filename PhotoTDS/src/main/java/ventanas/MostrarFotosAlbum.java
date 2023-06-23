package ventanas;

import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import dominio.Album;
import dominio.Foto;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import controlador.ControladorPhotoTDS;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;

public class MostrarFotosAlbum {

	protected JFrame albumTDS;
	private Album album;
	private JFrame perfil;
	private JPanel panelFotos;
	private ControladorPhotoTDS controlador;

	private final static int MAX_FOTOS = 16;

	/**
	 * Create the application.
	 */
	public MostrarFotosAlbum(Album album, JFrame perfil) {
		this.album = album;
		this.perfil = perfil;
		controlador = ControladorPhotoTDS.getUnicaInstancia();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		albumTDS = new JFrame();
		albumTDS.setTitle(album.getTitulo());
		albumTDS.setResizable(false);
		albumTDS.setIconImage(
				Toolkit.getDefaultToolkit().getImage(PerfilUsuario.class.getResource("/recursos/image.png")));
		albumTDS.setBounds(100, 100, 458, 357);
		albumTDS.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		albumTDS.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(450, 275));
		albumTDS.getContentPane().add(scrollPane);

		panelFotos = new JPanel();
		panelFotos.setSize(new Dimension(400, 300));
		panelFotos.setPreferredSize(new Dimension(430, 275));
		scrollPane.setViewportView(panelFotos);
		panelFotos.setLayout(new GridLayout(0, 3, 1, 1));

		if (album.getUser().equals(controlador.getUsuarioActual())) {
			JButton btnAddPhoto = new JButton("+");
			albumTDS.getContentPane().add(btnAddPhoto);
			btnAddPhoto.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					if (album.getFotos().size() < MAX_FOTOS) {
						JEditorPane editorPane = new JEditorPane();
						AñadirFoto af = new AñadirFoto(editorPane, perfil, album.getUser(), album.getTitulo());
						af.frmPhototds.setVisible(true);
						albumTDS.dispose();
					} else {
						JOptionPane.showMessageDialog(albumTDS, "No se pueden añadir más de 16 fotos a un album.",
								"Error", JOptionPane.ERROR_MESSAGE);

					}
				}
			});

			JButton btnDeletePhoto = new JButton("Eliminar");
			albumTDS.getContentPane().add(btnDeletePhoto);
			btnDeletePhoto.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					EliminarAlbum dialog = new EliminarAlbum(album, albumTDS, perfil);
					dialog.setVisible(true);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				}
			});
		}
		else {
			JButton btnLike = new JButton("Me gusta");
			albumTDS.getContentPane().add(btnLike);

			JButton btnquitarLikes = new JButton("Me gusta");
			albumTDS.getContentPane().add(btnquitarLikes);
			btnquitarLikes.setBackground(new Color(51, 153, 255));
			btnquitarLikes.setVisible(false);
			
			btnLike.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					controlador.addLikeAlbum(album);
					
					btnLike.setVisible(false);
					btnquitarLikes.setVisible(true);
				}
			});
			
			
			
			btnquitarLikes.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					controlador.removeLikeAlbum(album);
					
					btnLike.setVisible(true);
					btnquitarLikes.setVisible(false);
				}
			});
		}

		cargarFotos();

	}

	private void cargarFotos() {
		int numFotos = album.getFotos().size();

		int filas = 0;
		if (numFotos % 3 == 0) {
			filas = numFotos / 3;
		} else {
			filas = numFotos / 3 + 1;
		}

		if (numFotos > 0) {
			for (Foto f : album.getFotos()) {
				BufferedImage image = null;
				try {
					image = ImageIO.read(new File(((Foto) f).getRuta()));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Image scaledImage = image.getScaledInstance(142, 142, Image.SCALE_SMOOTH);
				ImageIcon icon = new ImageIcon(scaledImage);
				JButton boton = new JButton();
				boton.setSize(142, 142);
				boton.setContentAreaFilled(false);
				boton.setOpaque(false);
				boton.setBorder(null);
				boton.setBorderPainted(false);
				boton.setIcon(icon);
				boton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// frmPhototds.dispose();
						MostrarFoto foto = new MostrarFoto(f, perfil);
						foto.frame.setVisible(true);
					}
				});
				panelFotos.add(boton);
				// JLabel label = new JLabel(icon);
				// panelFotos.add(label);

				// label.addMouseListener(new PopMenuFotoListener());
			}
		}

		for (int i = numFotos; i < 3 * filas; i++) {
			panelFotos.add(new JLabel());
		}

	}
}
