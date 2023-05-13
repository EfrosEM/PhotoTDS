package ventanas;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;

import dominio.Album;
import dominio.Foto;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;

public class MostrarFotosAlbum {

	protected JFrame albumTDS;
	private Album album;
	private JPanel panelFotos;

	/**
	 * Create the application.
	 */
	public MostrarFotosAlbum(Album album) {
		this.album = album;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		albumTDS = new JFrame();
		albumTDS.setTitle("PhotoTDS");
		albumTDS.setResizable(false);
		albumTDS.setIconImage(
				Toolkit.getDefaultToolkit().getImage(PerfilUsuario.class.getResource("/recursos/image.png")));
		albumTDS.setBounds(100, 100, 458, 357);
		//albumTDS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

		JButton btnAddPhoto = new JButton("+");
		albumTDS.getContentPane().add(btnAddPhoto);
		btnAddPhoto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JEditorPane editorPane = new JEditorPane();
				AñadirFoto af = new AñadirFoto(editorPane, albumTDS, album.getUser(), album.getTitulo());
				af.frmPhototds.setVisible(true);
			}
		});

		JButton btnDeletePhoto = new JButton("Delete");
		albumTDS.getContentPane().add(btnDeletePhoto);
		
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

		System.out.println("Fotos: " + numFotos);
		if (numFotos > 0) {
			for (Foto f : album.getFotos()) {
				BufferedImage image = null;
				try {
					image = ImageIO.read(new File(((Foto) f).getRuta()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Image scaledImage = image.getScaledInstance(142, 142, Image.SCALE_SMOOTH);
				ImageIcon icon = new ImageIcon(scaledImage);
				JLabel label = new JLabel(icon);
				panelFotos.add(label);

				label.addMouseListener(new PopMenuFotoListener());
			}
		}

		for (int i = numFotos; i < 3 * filas; i++) {
			panelFotos.add(new JLabel());
		}

	}
}
