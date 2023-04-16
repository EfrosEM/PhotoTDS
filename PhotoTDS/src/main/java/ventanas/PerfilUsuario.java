package ventanas;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;

import controlador.ControladorPhotoTDS;
import dominio.Publicacion;
import dominio.Usuario;
import dominio.Foto;

import javax.swing.border.LineBorder;

public class PerfilUsuario {

	protected JFrame frmPhototds;
	private JTextField textField;
	private ControladorPhotoTDS controlador;
	private AñadirFoto af;
	private String rutaFoto;
	private Usuario usuario;

	private JPanel panelFotos;
	@SuppressWarnings("unused")
	private JPanel panelAñadirFoto;
	private JLabel seguidores;

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
		frmPhototds.setBounds(100, 100, 800, 850); // 577 629
		frmPhototds.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPhototds.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panelSur = new JPanel();
		panelSur.setPreferredSize(new Dimension(10, 400));
		frmPhototds.getContentPane().add(panelSur, BorderLayout.SOUTH);
		panelSur.setLayout(new GridLayout());

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setForeground(Color.DARK_GRAY);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelSur.add(tabbedPane);

		panelFotos = new JPanel();
		panelFotos.setPreferredSize(new Dimension(800, 400));
		JPanel panelAlbumes = new JPanel();

		tabbedPane.addTab("FOTOS", null, panelFotos, null);
		panelFotos.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(790, 390));
		panelFotos.add(scrollPane);

		JPanel panelFotos = new JPanel();
		panelFotos.setPreferredSize(new Dimension(790, 700));
		scrollPane.setViewportView(panelFotos);
		panelFotos.setLayout(new GridLayout(0, 3, 3, 3));
		int publicaciones = usuario.getPublicaciones().size();
		System.out.println("Publicaciones: " + publicaciones);
		if (publicaciones > 0) {
			for (Publicacion p : usuario.getPublicaciones()) {
				if (p instanceof Foto)
					panelFotos.add(new JLabel(new ImageIcon(((Foto) p).getRuta()))).setSize(new Dimension(100, 100));
				;

			}
		}
		/*
		 * for (int i = 0; i < 12; i++) { //panelFotos.add(new JLabel("Hola"));
		 * panelFotos.add(new JLabel(new
		 * ImageIcon(this.getClass().getResource("/recursos/nano.jpg"))));
		 * //.setSize(new Dimension(100, 100)); }
		 */

		tabbedPane.addTab("ÁLBUMES", null, panelAlbumes, null);
		panelAlbumes.setLayout(new GridLayout(3, 3, 0, 0));

		JLabel lbl5 = new JLabel("New label");
		panelAlbumes.add(lbl5);
		JLabel lbl6 = new JLabel("New label");
		panelAlbumes.add(lbl6);
		JLabel lbl7 = new JLabel("New label");
		panelAlbumes.add(lbl7);

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
			@SuppressWarnings("serial")
			public void actionPerformed(ActionEvent e) {
				JEditorPane editorPane = new JEditorPane();
				af = new AñadirFoto(editorPane);
				af.frmPhototds.setVisible(true);
				editorPane.setContentType("text/html");
				editorPane.setText("<h1>Agregar Foto</h1><p>Anímate a compartir una foto con tus amigos."
						+ "<br> Puedes arrastrar el fichero aquí. </p>");
				editorPane.setEditable(false);
				editorPane.setDropTarget(new DropTarget() {
					public synchronized void drop(DropTargetDropEvent evt) {
						try {
							evt.acceptDrop(DnDConstants.ACTION_COPY);
							@SuppressWarnings("unchecked")
							List<File> droppedFiles = (List<File>) evt.getTransferable()
									.getTransferData(DataFlavor.javaFileListFlavor);
							for (File file : droppedFiles) {
								rutaFoto = file.getPath();
								System.out.println(file.getPath());
								frmPhototds.dispose();
								af.frmPhototds.dispose();

								PublicarFoto pb = new PublicarFoto(rutaFoto);
								pb.publicarFoto.setVisible(true);
							}
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				});
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

		JLabel lblNewLabel_7 = new JLabel(
				controlador.getUsuarioActual().getNombre() + " " + usuario.getApellidos());
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_5.add(lblNewLabel_7);

		/*
		 * JPanel panel_2 = new JPanel(); panel_2.setBackground(Color.WHITE);
		 * panelCentral.add(panel_2, BorderLayout.SOUTH);
		 * 
		 * JButton lblNewLabel_2 = new JButton("FOTOS");
		 * lblNewLabel_2.setBackground(Color.WHITE); lblNewLabel_2.setBorder(null);
		 * lblNewLabel_2.setForeground(Color.DARK_GRAY); lblNewLabel_2.setFont(new
		 * Font("Tahoma", Font.PLAIN, 15)); panel_2.add(lblNewLabel_2);
		 * 
		 * Component rigidArea_6 = Box.createRigidArea(new Dimension(20, 20));
		 * panel_2.add(rigidArea_6);
		 * 
		 * JButton lblNewLabel_3 = new JButton("ÁLBUMES");
		 * lblNewLabel_3.setBackground(Color.WHITE); lblNewLabel_3.setBorder(null);
		 * lblNewLabel_3.setForeground(Color.DARK_GRAY); lblNewLabel_3.setFont(new
		 * Font("Tahoma", Font.PLAIN, 15)); panel_2.add(lblNewLabel_3);
		 */
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

}
