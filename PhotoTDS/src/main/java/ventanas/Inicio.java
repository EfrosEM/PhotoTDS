package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import controlador.ControladorPhotoTDS;
import persistencia.AdaptadorFotoTDS;
import dominio.Comentario;
import dominio.Foto;
import dominio.Notificacion;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

public class Inicio {

	/**
	 * 
	 */
	protected JFrame frmPhototds;
	private JTextField textField;
	private ControladorPhotoTDS controlador;
	private AdaptadorFotoTDS adaptadorFoto;
	private String com;

	/**
	 * Create the application.
	 */
	public Inicio() {
		controlador = ControladorPhotoTDS.getUnicaInstancia();
		adaptadorFoto = AdaptadorFotoTDS.getUnicaInstancia();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPhototds = new JFrame();
		frmPhototds.setTitle("PhotoTDS");
		frmPhototds.setResizable(false);
		frmPhototds.setIconImage(
				Toolkit.getDefaultToolkit().getImage(PerfilUsuario.class.getResource("/recursos/image.png")));
		frmPhototds.setBounds(100, 100, 577, 629);
		frmPhototds.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPhototds.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panelNorte = new JPanel();
		frmPhototds.getContentPane().add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new BoxLayout(panelNorte, BoxLayout.X_AXIS));

		Component rigidArea_2 = Box.createRigidArea(new Dimension(10, 10));
		panelNorte.add(rigidArea_2);

		JLabel lblNewLabel = new JLabel("PhotoTDS");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 18));
		panelNorte.add(lblNewLabel);

		Component rigidArea = Box.createRigidArea(new Dimension(50, 30));
		rigidArea.setPreferredSize(new Dimension(50, 40));
		panelNorte.add(rigidArea);

		JButton btnNewButton = new JButton("F+");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		btnNewButton.setPreferredSize(new Dimension(25, 25));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JEditorPane editorPane = new JEditorPane();
				AñadirFoto af = new AñadirFoto(editorPane, null, controlador.getUsuarioActual(), null);
				af.frmPhototds.setVisible(true);

			}

		});
		btnNewButton.setMaximumSize(new Dimension(25, 25));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelNorte.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("A+");
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setPreferredSize(new Dimension(25, 25));
		btnNewButton_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setMaximumSize(new Dimension(25, 25));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CrearAlbum dialog = new CrearAlbum(null, controlador.getUsuarioActual());
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});

		Component rigidArea_4 = Box.createRigidArea(new Dimension(20, 20));
		rigidArea_4.setMaximumSize(new Dimension(10, 10));
		rigidArea_4.setPreferredSize(new Dimension(5, 5));
		panelNorte.add(rigidArea_4);
		panelNorte.add(btnNewButton_1);

		Component rigidArea_5 = Box.createRigidArea(new Dimension(20, 20));
		rigidArea_5.setPreferredSize(new Dimension(5, 5));
		rigidArea_5.setMinimumSize(new Dimension(10, 10));
		rigidArea_5.setMaximumSize(new Dimension(10, 10));
		panelNorte.add(rigidArea_5);

		textField = new JTextField();
		textField.setMaximumSize(new Dimension(2147483647, 20));
		textField.setMinimumSize(new Dimension(7, 15));
		textField.setPreferredSize(new Dimension(7, 15));
		panelNorte.add(textField);
		textField.setColumns(10);

		Component rigidArea_5_1 = Box.createRigidArea(new Dimension(20, 20));
		rigidArea_5_1.setPreferredSize(new Dimension(5, 5));
		rigidArea_5_1.setMinimumSize(new Dimension(10, 10));
		rigidArea_5_1.setMaximumSize(new Dimension(10, 10));
		panelNorte.add(rigidArea_5_1);

		// JLabel lblNewLabel_1 = new JLabel("");
		// lblNewLabel_1.setIcon(new
		// ImageIcon(PerfilUsuario.class.getResource("/recursos/lupa.png")));
		// panelNorte.add(lblNewLabel_1);

		JButton lupa = new JButton("");
		lupa.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lupa.setBackground(new Color(255, 255, 255));

		ImageIcon icon = new ImageIcon(Inicio.class.getResource("/recursos/lupa.png"));
		Image img = icon.getImage();
		Image otraimg = img.getScaledInstance(20, 20, java.awt.Image.SCALE_DEFAULT);
		ImageIcon otroicon = new ImageIcon(otraimg);
		lupa.setIcon(otroicon);
		lupa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String busqueda = textField.getText();
				
				if (busqueda.contains("#")) {
					String[] busqHashtag = busqueda.split(" ");
					ArrayList<String> hashtags = new ArrayList<String>();
					
					for (String hashtag : busqHashtag) {
						System.out.println("Hashtag: " + hashtag);
						hashtags.add(hashtag);
					}
					BusquedaHashtags bH = new BusquedaHashtags(hashtags, frmPhototds);
					bH.busquedaHashtags.setVisible(true);
				} else {
					BusquedaUsuarios bu = new BusquedaUsuarios(busqueda, frmPhototds);
					bu.busquedaUsuarios.setVisible(true);
				}
				
			}
		});

		panelNorte.add(lupa);

		Component rigidArea_1 = Box.createRigidArea(new Dimension(50, 30));
		rigidArea_1.setPreferredSize(new Dimension(50, 40));
		panelNorte.add(rigidArea_1);

		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		btnNewButton_2.setBackground(new Color(255, 255, 255));

		ImageIcon ic = new ImageIcon(controlador.getUsuarioActual().getFotoPerfil());
		Image im = ic.getImage();
		Image oim = im.getScaledInstance(30, 30, java.awt.Image.SCALE_DEFAULT);
		ImageIcon oic = new ImageIcon(oim);
		btnNewButton_2.setIcon(oic);
		btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frmPhototds.dispose();
				PerfilUsuario perfilUsuario = new PerfilUsuario(controlador.getUsuarioActual());
				perfilUsuario.frmPhototds.setVisible(true);

			}
		});
		panelNorte.add(btnNewButton_2);

		Component rigidArea_3 = Box.createRigidArea(new Dimension(10, 10));
		panelNorte.add(rigidArea_3);

		JButton btnNewButton_2_1 = new JButton("");
		btnNewButton_2_1.setIcon(new ImageIcon(Inicio.class.getResource("/recursos/icono-menu.png")));
		btnNewButton_2_1.setBorder(null);
		btnNewButton_2_1.setContentAreaFilled(false);
		btnNewButton_2_1.setBorderPainted(false);
		btnNewButton_2_1.setOpaque(false);
		btnNewButton_2_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				OpcionesPremium dialog = new OpcionesPremium();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);

			}
		});
		panelNorte.add(btnNewButton_2_1);

		Component rigidArea_3_1 = Box.createRigidArea(new Dimension(10, 10));
		panelNorte.add(rigidArea_3_1);

		JPanel panelCentro = new JPanel();
		frmPhototds.getContentPane().add(panelCentro, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setSize(new Dimension(570, 400));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(570, 550));
		panelCentro.add(scrollPane);

		JPanel panelPublicaciones = new JPanel();
		panelPublicaciones.setSize(new Dimension(550, 400));
		panelPublicaciones.setPreferredSize(new Dimension(550, 2000));
		scrollPane.setViewportView(panelPublicaciones);
		panelPublicaciones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		List<Notificacion> notificaciones = controlador.getUsuarioActual().getNotificaciones();
		Collections.reverse(notificaciones);

		System.out.println("Notificaciones: " + notificaciones.size());
		for (Notificacion notificacion : notificaciones) {
			Foto foto = (Foto) notificacion.getPublicacion();
			JPanel panelPublicacion = new JPanel();
			panelPublicacion.setBackground(Color.WHITE);
			panelPublicacion.setBorder(new LineBorder(new Color(0, 0, 0)));
			panelPublicacion.setPreferredSize(new Dimension(555, 80));
			panelPublicacion.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

			JPanel panelFoto = new JPanel();
			panelFoto.setPreferredSize(new Dimension(170, 70));
			panelPublicacion.add(panelFoto);

			JLabel lblFoto = new JLabel("");
			lblFoto.setAlignmentY(Component.TOP_ALIGNMENT);
			lblFoto.setSize(new Dimension(5, 5));
			lblFoto.setBorder(new LineBorder(new Color(0, 0, 0)));

			lblFoto.setIcon(redimensionarImagen(((Foto) foto).getRuta(), 160, 60));
			panelFoto.add(lblFoto);

			JPanel panelInteract = new JPanel();
			panelInteract.setBackground(Color.WHITE);
			FlowLayout flowLayout = (FlowLayout) panelInteract.getLayout();
			flowLayout.setVgap(10);
			flowLayout.setAlignment(FlowLayout.LEFT);
			panelInteract.setPreferredSize(new Dimension(240, 70));
			panelPublicacion.add(panelInteract);

			JLabel lblLikes = new JLabel(String.valueOf(foto.getLikes()));

			JButton btnLikes = new JButton("Like");
			panelInteract.add(btnLikes);
			
			JButton btnquitarLikes = new JButton("Like");
			panelInteract.add(btnquitarLikes);
			btnquitarLikes.setBackground(new Color(51, 153, 255));
			btnquitarLikes.setVisible(false);
			
			btnLikes.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					foto.addLike();
					adaptadorFoto.modificarFoto((Foto) foto);
					lblLikes.setText(String.valueOf(foto.getLikes()));
					
					btnLikes.setVisible(false);
					btnquitarLikes.setVisible(true);
				}
			});
			
			
			
			btnquitarLikes.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					foto.removeLike();
					adaptadorFoto.modificarFoto((Foto) foto);
					lblLikes.setText(String.valueOf(foto.getLikes()));
					
					btnLikes.setVisible(true);
					btnquitarLikes.setVisible(false);
				}
			});

			
			JButton btnComentario = new JButton("Comentario");
			panelInteract.add(btnComentario);
			btnComentario.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					com = JOptionPane.showInputDialog("Escribe un comentario");
					Comentario comentario = new Comentario(com, controlador.getUsuarioActual());
					foto.addComentario(comentario);
				}
			});

			panelInteract.add(lblLikes);
			
			JLabel lblMeGusta = new JLabel("Me gusta");
			lblMeGusta.setFont(new Font("Tahoma", Font.ITALIC, 11));
			panelInteract.add(lblMeGusta);

			JLabel lblFotoUsuario = new JLabel("");
			lblFotoUsuario.setAlignmentY(Component.TOP_ALIGNMENT);
			lblFotoUsuario.setSize(new Dimension(5, 5));
			lblFotoUsuario.setBorder(new LineBorder(new Color(0, 0, 0)));

			lblFotoUsuario.setIcon(redimensionarImagen(notificacion.getUser().getFotoPerfil(), 20, 20));
			panelInteract.add(lblFotoUsuario);

			JLabel lblNombreUsuario = new JLabel(notificacion.getUser().getUsuario());
			panelInteract.add(lblNombreUsuario);

			panelPublicaciones.add(panelPublicacion);
		}
	}

	private ImageIcon redimensionarImagen(String imagen, int x, int y) {
		ImageIcon icon = new ImageIcon(imagen);
		Image img = icon.getImage();
		Image otraimg = img.getScaledInstance(x, y, java.awt.Image.SCALE_DEFAULT);
		ImageIcon otroicon = new ImageIcon(otraimg);

		return otroicon;
	}

}
