package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import controlador.ControladorPhotoTDS;
import dominio.Album;
import dominio.Foto;
import dominio.Publicacion;
import dominio.Usuario;

import java.awt.CardLayout;
import javax.swing.ScrollPaneConstants;

public class PerfilUsuario {

	protected JFrame frmPhototds;
	private JTextField textField;
	private ControladorPhotoTDS controlador;
	private AñadirFoto af;
	private Usuario usuario;
	@SuppressWarnings("unused")
	private JPanel panelAñadirFoto;
	private JLabel seguidores;
	private JPanel panelPublicaciones;
	private JPanel panelFotos;
	private JPanel panelAlbumes;
	private CardLayout cardlayout;
	private JScrollPane scrollPane_Fotos;
	private JScrollPane scrollPane_Albumes;
	private JButton botonFotos;
	private JButton botonAlbumes;
	

	/**
	 * Create the application.
	 */
	public PerfilUsuario(Usuario usuario) {
		controlador = ControladorPhotoTDS.getUnicaInstancia();
		this.usuario = usuario;
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
		frmPhototds.setBounds(100, 100, 586, 663); // 577 629
		frmPhototds.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPhototds.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panelSur = new JPanel();
		panelSur.setPreferredSize(new Dimension(10, 400));
		frmPhototds.getContentPane().add(panelSur, BorderLayout.SOUTH);
		FlowLayout fl_panelSur = new FlowLayout(FlowLayout.CENTER, 5, 5);
		panelSur.setLayout(fl_panelSur);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(null);
		panel_6.setPreferredSize(new Dimension(580, 30));
		panelSur.add(panel_6);
		
		botonFotos = new JButton("FOTOS");
		botonFotos.setFont(new Font("Tahoma", Font.BOLD, 12));
		botonFotos.setContentAreaFilled(false);
		botonFotos.setOpaque(false);
		botonFotos.setBorder(null);
		botonFotos.setBorderPainted(false);
		panel_6.add(botonFotos);
		botonFotos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				botonFotos.setFont(new Font(botonFotos.getFont().getName(), Font.BOLD, botonFotos.getFont().getSize()));
				botonAlbumes.setFont(new Font(botonFotos.getFont().getName(), Font.PLAIN, botonFotos.getFont().getSize()));
				cardlayout.show(panelPublicaciones, "panelFotos");
			}
		});
		
		Component rigidArea_2_1 = Box.createRigidArea(new Dimension(10, 10));
		rigidArea_2_1.setPreferredSize(new Dimension(30, 10));
		panel_6.add(rigidArea_2_1);
		
		botonAlbumes = new JButton("ÁLBUMES");
		botonAlbumes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		botonAlbumes.setOpaque(false);
		botonAlbumes.setBorder(null);
		botonAlbumes.setContentAreaFilled(false);
		botonAlbumes.setBorderPainted(false);
		panel_6.add(botonAlbumes);
		botonAlbumes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				botonFotos.setFont(new Font(botonFotos.getFont().getName(), Font.PLAIN, botonFotos.getFont().getSize()));
				botonAlbumes.setFont(new Font(botonFotos.getFont().getName(), Font.BOLD, botonFotos.getFont().getSize()));
				cardlayout.show(panelPublicaciones, "panelAlbumes");
				
			}
		});
		
		panelPublicaciones = new JPanel();
		panelPublicaciones.setPreferredSize(new Dimension(575, 360));
		panelSur.add(panelPublicaciones);
		panelPublicaciones.setLayout(new CardLayout(0, 0));
		
		scrollPane_Fotos = new JScrollPane();
		scrollPane_Fotos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_Fotos.setSize(new Dimension(400, 400));
		scrollPane_Fotos.setPreferredSize(new Dimension(575, 360));
		panelPublicaciones.add(scrollPane_Fotos, "panelFotos");
		
		scrollPane_Albumes = new JScrollPane();
		scrollPane_Albumes.setSize(new Dimension(400, 400));
		scrollPane_Albumes.setPreferredSize(new Dimension(580, 400));
		panelPublicaciones.add(scrollPane_Albumes, "panelAlbumes");
		
		cardlayout = (CardLayout) panelPublicaciones.getLayout(); 
		cargarFotos();
		cargarAlbumes();
		cardlayout.show(panelPublicaciones, "panelFotos");
		
		JPanel panelNorte = new JPanel();
		frmPhototds.getContentPane().add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new BoxLayout(panelNorte, BoxLayout.X_AXIS));

		Component rigidArea_2 = Box.createRigidArea(new Dimension(10, 10));
		panelNorte.add(rigidArea_2);

		JButton btnNewButton_4 = new JButton("PhotoTDS");
		btnNewButton_4.setContentAreaFilled(false);
		btnNewButton_4.setBorder(null);
		btnNewButton_4.setMargin(new Insets(2, 5, 2, 5));
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton_4.setBorderPainted(false);
		btnNewButton_4.setOpaque(false);

		btnNewButton_4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frmPhototds.dispose();
				Inicio inicio = new Inicio();
				inicio.frmPhototds.setVisible(true);

			}
		});

		panelNorte.add(btnNewButton_4);

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
				af = new AñadirFoto(editorPane, frmPhototds, usuario);
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
				
				CrearAlbum dialog = new CrearAlbum(frmPhototds, usuario);
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

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(PerfilUsuario.class.getResource("/recursos/lupa.png")));
		panelNorte.add(lblNewLabel_1);

		Component rigidArea_1 = Box.createRigidArea(new Dimension(50, 30));
		rigidArea_1.setPreferredSize(new Dimension(50, 40));
		panelNorte.add(rigidArea_1);

		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		btnNewButton_2.setBackground(new Color(255, 255, 255));

		btnNewButton_2.setIcon(redimensionarImagen(controlador.getUsuarioActual().getFotoPerfil(), 30, 30));
		panelNorte.add(btnNewButton_2);

		Component rigidArea_3 = Box.createRigidArea(new Dimension(10, 10));
		panelNorte.add(rigidArea_3);

		JPanel panelCentral = new JPanel();
		panelCentral.setMinimumSize(new Dimension(850, 500));
		panelCentral.setPreferredSize(new Dimension(850, 200));
		panelCentral.setMaximumSize(new Dimension(850, 300));
		panelCentral.setBorder(new LineBorder(new Color(192, 192, 192)));
		frmPhototds.getContentPane().add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(150, 50));
		panel.setMaximumSize(new Dimension(200, 100));
		panel.setBackground(Color.WHITE);
		panelCentral.add(panel, BorderLayout.WEST);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		Component rigidArea_7 = Box.createRigidArea(new Dimension(20, 20));
		panel.add(rigidArea_7);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setAlignmentY(Component.TOP_ALIGNMENT);
		lblNewLabel_4.setSize(new Dimension(5, 5));
		lblNewLabel_4.setBorder(new LineBorder(new Color(0, 0, 0)));

		lblNewLabel_4.setIcon(redimensionarImagen(usuario.getFotoPerfil(), 135, 135));
		panel.add(lblNewLabel_4);

		Component rigidArea_8 = Box.createRigidArea(new Dimension(20, 20));
		panel.add(rigidArea_8);

		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(650, 400));
		panel_1.setMaximumSize(new Dimension(650, 400));
		panel_1.setBackground(Color.WHITE);
		panelCentral.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		Component rigidArea_9_1 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_9_1);

		Panel panel_3 = new Panel();
		panel_3.setMaximumSize(new Dimension(650, 100));
		panel_1.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		Component rigidArea_6 = Box.createRigidArea(new Dimension(20, 20));
		panel_3.add(rigidArea_6);

		JLabel lblNewLabel_5 = new JLabel(usuario.getUsuario());
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_3.add(lblNewLabel_5);

		Component rigidArea_9 = Box.createRigidArea(new Dimension(20, 20));
		panel_3.add(rigidArea_9);

		JButton btnNewButton_3 = new JButton("Editar Perfil");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_3.setForeground(Color.WHITE);
		btnNewButton_3.setBackground(new Color(51, 153, 255));
		panel_3.add(btnNewButton_3);
		
		JButton btnNewButton_3_1 = new JButton("Seguir");
		btnNewButton_3_1.setForeground(Color.WHITE);
		btnNewButton_3_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_3_1.setBackground(new Color(51, 153, 255));
		panel_3.add(btnNewButton_3_1);
		
		JButton btnNewButton_3_2 = new JButton("Dejar de seguir");
		btnNewButton_3_2.setForeground(Color.WHITE);
		btnNewButton_3_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_3_2.setBackground(SystemColor.desktop);
		panel_3.add(btnNewButton_3_2);
		
		
		
		if (usuario.getUsuario().equals(controlador.getUsuarioActual().getUsuario())) {
			btnNewButton_3_1.setVisible(false);
			btnNewButton_3_2.setVisible(false);
			
		}
		else {
			btnNewButton_3.setVisible(false);
			if (usuario.esSeguidor(controlador.getUsuarioActual().getUsuario())) {
				btnNewButton_3_2.setVisible(true);
				btnNewButton_3_1.setVisible(false);
			}
			else {
				btnNewButton_3_2.setVisible(false);
				btnNewButton_3_1.setVisible(true);
			}
			
			btnNewButton_2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					frmPhototds.dispose();
					PerfilUsuario perfilUsuario = new PerfilUsuario(controlador.getUsuarioActual());
					perfilUsuario.frmPhototds.setVisible(true);
				}
			});
			
		}
		
		

		btnNewButton_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				abrirEditar();

			}
		});

		btnNewButton_3_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				usuario.addSeguidor(controlador.getUsuarioActual());
				controlador.getUsuarioActual().addSeguidos();
				controlador.modificarUsuario(usuario);
				controlador.modificarUsuario(controlador.getUsuarioActual());
				seguidores.setText(String.valueOf(usuario.getSeguidores().size()));
				btnNewButton_3_1.setVisible(false);
				btnNewButton_3_2.setVisible(true);
			}
		});
		
		btnNewButton_3_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				usuario.removeSeguidor(controlador.getUsuarioActual());
				controlador.getUsuarioActual().removeSeguidos();
				controlador.modificarUsuario(usuario);
				controlador.modificarUsuario(controlador.getUsuarioActual());
				seguidores.setText(String.valueOf(usuario.getSeguidores().size()));
				btnNewButton_3_1.setVisible(true);
				btnNewButton_3_2.setVisible(false);
			}
		});

		Panel panel_4 = new Panel();
		panel_4.setMaximumSize(new Dimension(650, 100));
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_4);
		
		Component rigidArea_12 = Box.createRigidArea(new Dimension(20, 20));
		panel_4.add(rigidArea_12);

		JLabel lblNewLabel_6 = new JLabel(String.valueOf(usuario.getPublicaciones().size()));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_4.add(lblNewLabel_6);

		JLabel lblNewLabel_8 = new JLabel("Publicaciones");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_4.add(lblNewLabel_8);

		Component rigidArea_10 = Box.createRigidArea(new Dimension(20, 20));
		panel_4.add(rigidArea_10);

		seguidores = new JLabel(String.valueOf(usuario.getSeguidores().size()));
		seguidores.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_4.add(seguidores);

		JLabel lblNewLabel_10 = new JLabel("Seguidores");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_4.add(lblNewLabel_10);

		Component rigidArea_11 = Box.createRigidArea(new Dimension(20, 20));
		panel_4.add(rigidArea_11);

		JLabel lblNewLabel_11 = new JLabel(String.valueOf(usuario.getSeguidos()));
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_4.add(lblNewLabel_11);

		JLabel lblNewLabel_12 = new JLabel("Seguidos");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_4.add(lblNewLabel_12);

		Panel panel_5 = new Panel();
		panel_5.setMaximumSize(new Dimension(650, 100));
		FlowLayout flowLayout_1 = (FlowLayout) panel_5.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_5);
		
		Component rigidArea_13 = Box.createRigidArea(new Dimension(20, 20));
		panel_5.add(rigidArea_13);

		JLabel lblNewLabel_7 = new JLabel(
				usuario.getNombre() + " " + usuario.getApellidos());
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_5.add(lblNewLabel_7);
	}

	private void abrirEditar() {

		frmPhototds.dispose();;
		EditarPerfil editarPerfil = new EditarPerfil();
		editarPerfil.frmEditarPerfil.setVisible(true);
	}

	private ImageIcon redimensionarImagen(String imagen, int x, int y) {
		ImageIcon icon = new ImageIcon(imagen);
		Image img = icon.getImage();
		Image otraimg = img.getScaledInstance(x, y, java.awt.Image.SCALE_DEFAULT);
		ImageIcon otroicon = new ImageIcon(otraimg);

		return otroicon;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	private int getNumFotos() {
		int numFotos = 0;
		for (Publicacion p: usuario.getPublicaciones()) {
			if (p instanceof Foto) {
				numFotos++;
			} 
		}
		
		return numFotos;
	}
	
	private int getNumAlbumes() {
		int numAlbumes = 0;
		for (Publicacion p: usuario.getPublicaciones()) {
			if (p instanceof Album) {
				numAlbumes++;
			}
		}
		
		return numAlbumes;
	}
	
	private void cargarFotos() {
		int numFotos = getNumFotos();
		
		int filas = 0;
		if (numFotos % 3 == 0) {
			filas = numFotos / 3;
		} else {
			filas = numFotos / 3 + 1;
		}
		
		panelFotos = new JPanel();
		panelFotos.setPreferredSize(new Dimension(560, 450));
		scrollPane_Fotos.setViewportView(panelFotos);
		panelFotos.setLayout(new GridLayout(filas, 3, 1, 1));
		
		System.out.println("Fotos: " + numFotos);
		if (numFotos > 0) {
			for (Publicacion p : usuario.getPublicaciones()) {
				if (p instanceof Foto) {
					BufferedImage image = null;
					try {
						image = ImageIO.read(new File(((Foto) p).getRuta()));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Image scaledImage = image.getScaledInstance(170, 170, Image.SCALE_SMOOTH);
					ImageIcon icon = new ImageIcon(scaledImage);
					JLabel label = new JLabel(icon);
					panelFotos.add(label);
				
					
					label.addMouseListener(new PopMenuFotoListener());
				}
			}
		}
		
		for (int i = numFotos; i < 3 * filas; i++) {
			panelFotos.add(new JLabel());
		}

	}
	
	private void cargarAlbumes() {		
		int numAlbumes = getNumAlbumes();
		
		int filas = 0;
		if (numAlbumes % 3 == 0) {
			filas = numAlbumes / 3;
		} else {
			filas = numAlbumes / 3 + 1;
		}
		
		panelAlbumes = new JPanel();
		panelAlbumes.setPreferredSize(new Dimension(200, 10));
		scrollPane_Albumes.setViewportView(panelAlbumes);
		panelAlbumes.setLayout(new GridLayout(filas, 3, 1, 1));
		
		System.out.println("Albumes: " + numAlbumes);
		if (numAlbumes > 0) {
			for (Publicacion p : usuario.getPublicaciones()) {
				if (p instanceof Album) {
					BufferedImage image = null;
					try {
						image = ImageIO.read(new File(((Album) p).getFotoAlbum().getRuta()));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Image scaledImage = image.getScaledInstance(170, 170, Image.SCALE_SMOOTH);
					ImageIcon icon = new ImageIcon(scaledImage);
					JLabel label = new JLabel(icon);
					panelAlbumes.add(label);
				
					
					label.addMouseListener(new PopMenuFotoListener());
				}
			}
		}
		
		for (int i = numAlbumes; i < 3 * filas; i++) {
			panelAlbumes.add(new JLabel());
		}

	}


}
