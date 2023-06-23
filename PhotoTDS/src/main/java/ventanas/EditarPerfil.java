package ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;
import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.Dimension;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import controlador.ControladorPhotoTDS;

import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JFileChooser;

public class EditarPerfil {

	/**
	 * 
	 */
	private ControladorPhotoTDS controlador;

	protected JFrame frmEditarPerfil;
	private JTextField txtEmail;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtNombreDeUsuario;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JLabel lblWarningEmail;
	private JLabel lblWarningNombre;
	private JLabel lblWarningApellidos;
	private JLabel lblWarningUsuario;
	private JLabel lblWarningContraseña;
	private JLabel lblWarningRepetirContraseña;
	private JLabel lblWarningDescripcion;

	private String descripcion = null;
	private String fotoPerfil = null;

	private JTextArea ta = null;
	private JScrollPane sp = null;

	/**
	 * Create the application.
	 */
	public EditarPerfil() {
		controlador = ControladorPhotoTDS.getUnicaInstancia();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmEditarPerfil = new JFrame();
		frmEditarPerfil.setSize(new Dimension(342, 484));
		frmEditarPerfil.setPreferredSize(new Dimension(450, 500));
		frmEditarPerfil.setResizable(false);
		frmEditarPerfil.setBackground(Color.WHITE);
		frmEditarPerfil.setTitle("Editar Perfil");
		frmEditarPerfil.setIconImage(
				Toolkit.getDefaultToolkit().getImage(EditarPerfil.class.getResource("/Recursos/image.png")));
		frmEditarPerfil.setBounds(100, 100, 449, 570); // 376 y 538
		frmEditarPerfil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel sur = new JPanel();
		sur.setBackground(Color.WHITE);
		frmEditarPerfil.getContentPane().add(sur, BorderLayout.SOUTH);
		sur.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		JPanel panelBotonRegistrar = new JPanel();
		panelBotonRegistrar.setBackground(Color.WHITE);
		FlowLayout fl_panelBotonRegistrar = (FlowLayout) panelBotonRegistrar.getLayout();
		fl_panelBotonRegistrar.setAlignment(FlowLayout.RIGHT);
		sur.add(panelBotonRegistrar);

		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (comprobarCampos()) {
					String nombre = txtNombre.getText();
					String usuario = txtNombreDeUsuario.getText();
					String apellidos = txtApellidos.getText();
					String password = String.valueOf(passwordField.getPassword());
					String email = txtEmail.getText();
					Date fecha = controlador.getUsuarioActual().getNacimiento();
					if (fotoPerfil == null)
						fotoPerfil = controlador.getUsuarioActual().getFotoPerfil();
					if (descripcion == null)
						descripcion = controlador.getUsuarioActual().getDescripcion();

					modificarUsuario(nombre, usuario, apellidos, password, email, fotoPerfil, descripcion, fecha);
					volverPerfil();
				}
			}

			private void modificarUsuario(String nombre, String usuario, String apellidos, String password,
					String email, String fotoPerfil, String descripcion, Date fecha) {

				controlador.modificarUsuario(nombre, apellidos, email, usuario, password, fecha, descripcion,
						fotoPerfil);
			}
		});
		panelBotonRegistrar.add(btnNewButton);

		JPanel panelBotonCancel = new JPanel();
		panelBotonCancel.setBackground(Color.WHITE);
		sur.add(panelBotonCancel);

		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				volverPerfil();
			}
		});
		panelBotonCancel.add(btnNewButton_1);

		JPanel centro = new JPanel();
		centro.setBackground(Color.WHITE);
		frmEditarPerfil.getContentPane().add(centro, BorderLayout.CENTER);
		centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));

		JPanel panelEmail = new JPanel();
		panelEmail.setPreferredSize(new Dimension(10, 5));
		panelEmail.setMinimumSize(new Dimension(10, 5));
		panelEmail.setBackground(Color.WHITE);
		centro.add(panelEmail);
		FlowLayout fl_panelEmail = new FlowLayout(FlowLayout.RIGHT, 5, 5);
		panelEmail.setLayout(fl_panelEmail);

		lblWarningEmail = new JLabel("*Campo vacío*");
		lblWarningEmail.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblWarningEmail.setVisible(false);
		lblWarningEmail.setForeground(Color.RED);
		panelEmail.add(lblWarningEmail);

		JLabel lblNewLabel_3 = new JLabel("Email: ");
		lblNewLabel_3.setToolTipText("");
		panelEmail.add(lblNewLabel_3);

		txtEmail = new JTextField();
		txtEmail.setPreferredSize(new Dimension(4, 20));
		txtEmail.setToolTipText("");
		txtEmail.setForeground(Color.DARK_GRAY);
		panelEmail.add(txtEmail);
		txtEmail.setColumns(20);
		txtEmail.setText(controlador.getUsuarioActual().getEmail());

		JPanel panelFullName = new JPanel();
		FlowLayout fl_panelFullName = (FlowLayout) panelFullName.getLayout();
		fl_panelFullName.setAlignment(FlowLayout.RIGHT);
		panelFullName.setPreferredSize(new Dimension(10, 5));
		panelFullName.setBackground(Color.WHITE);
		centro.add(panelFullName);

		lblWarningNombre = new JLabel("*Campo vacío*");
		lblWarningNombre.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblWarningNombre.setVisible(false);
		lblWarningNombre.setForeground(Color.RED);
		panelFullName.add(lblWarningNombre);

		JLabel lblNewLabel_4 = new JLabel("Nombre:");
		panelFullName.add(lblNewLabel_4);

		txtNombre = new JTextField();
		txtNombre.setForeground(Color.DARK_GRAY);
		panelFullName.add(txtNombre);
		txtNombre.setColumns(20);
		txtNombre.setText(controlador.getUsuarioActual().getNombre());

		JPanel panelApellidos = new JPanel();
		panelApellidos.setBackground(Color.WHITE);
		panelApellidos.setPreferredSize(new Dimension(10, 5));
		FlowLayout flowLayout_1 = (FlowLayout) panelApellidos.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		centro.add(panelApellidos);

		lblWarningApellidos = new JLabel("*Campo vacío*");
		lblWarningApellidos.setVisible(false);
		lblWarningApellidos.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblWarningApellidos.setForeground(Color.RED);
		panelApellidos.add(lblWarningApellidos);

		JLabel lblApellidos = new JLabel("Apellidos:");
		panelApellidos.add(lblApellidos);

		txtApellidos = new JTextField();
		panelApellidos.add(txtApellidos);
		txtApellidos.setColumns(20);
		txtApellidos.setText(controlador.getUsuarioActual().getApellidos());

		JPanel panelUsuario = new JPanel();
		FlowLayout fl_panelUsuario = (FlowLayout) panelUsuario.getLayout();
		fl_panelUsuario.setAlignment(FlowLayout.RIGHT);
		panelUsuario.setPreferredSize(new Dimension(10, 5));
		panelUsuario.setBackground(Color.WHITE);
		centro.add(panelUsuario);

		lblWarningUsuario = new JLabel("");
		lblWarningUsuario.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblWarningUsuario.setVisible(false);
		lblWarningUsuario.setForeground(Color.RED);
		panelUsuario.add(lblWarningUsuario);

		JLabel lblNewLabel_5 = new JLabel("Nombre de usuario: ");
		panelUsuario.add(lblNewLabel_5);

		txtNombreDeUsuario = new JTextField();
		txtNombreDeUsuario.setForeground(Color.DARK_GRAY);
		panelUsuario.add(txtNombreDeUsuario);
		txtNombreDeUsuario.setColumns(20);
		txtNombreDeUsuario.setText(controlador.getUsuarioActual().getUsuario());

		JPanel panelContraseña = new JPanel();
		FlowLayout fl_panelContraseña = (FlowLayout) panelContraseña.getLayout();
		fl_panelContraseña.setAlignment(FlowLayout.RIGHT);
		panelContraseña.setPreferredSize(new Dimension(10, 5));
		panelContraseña.setBackground(Color.WHITE);
		centro.add(panelContraseña);

		lblWarningContraseña = new JLabel("");
		lblWarningContraseña.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblWarningContraseña.setVisible(false);
		lblWarningContraseña.setForeground(Color.RED);
		panelContraseña.add(lblWarningContraseña);

		JLabel lblNewLabel_6 = new JLabel("Contrase\u00F1a: ");
		panelContraseña.add(lblNewLabel_6);

		passwordField = new JPasswordField();
		passwordField.setColumns(20);
		panelContraseña.add(passwordField);
		passwordField.setText(controlador.getUsuarioActual().getPassword());

		JPanel panelRepetirContraseña = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelRepetirContraseña.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelRepetirContraseña.setPreferredSize(new Dimension(10, 5));
		panelRepetirContraseña.setBackground(Color.WHITE);
		centro.add(panelRepetirContraseña);

		lblWarningRepetirContraseña = new JLabel("");
		lblWarningRepetirContraseña.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblWarningRepetirContraseña.setVisible(false);
		lblWarningRepetirContraseña.setForeground(Color.RED);
		panelRepetirContraseña.add(lblWarningRepetirContraseña);

		JLabel lblNewLabel_6_1 = new JLabel("Repetir Contraseña:");
		panelRepetirContraseña.add(lblNewLabel_6_1);

		passwordField_1 = new JPasswordField();
		passwordField_1.setColumns(20);
		panelRepetirContraseña.add(passwordField_1);
		passwordField_1.setText(controlador.getUsuarioActual().getPassword());

		JPanel panelAddFoto = new JPanel();
		FlowLayout fl_panelAddFoto = (FlowLayout) panelAddFoto.getLayout();
		fl_panelAddFoto.setAlignment(FlowLayout.LEFT);
		panelAddFoto.setBackground(Color.WHITE);
		centro.add(panelAddFoto);

		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.WHITE);
		separator_3.setPreferredSize(new Dimension(3, 20));
		panelAddFoto.add(separator_3);

		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_10.setBackground(Color.WHITE);
		panelAddFoto.add(panel_10);

		JLabel lblNewLabel_1 = new JLabel("Editar foto de usuario");
		panel_10.add(lblNewLabel_1);

//		JFileChooser fileChooser = new JFileChooser();
//		fileChooser.setVisible(false);

		JButton btnFileChooser = new JButton("+");
		btnFileChooser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int seleccion = fileChooser.showOpenDialog(panelAddFoto);

				if (seleccion == JFileChooser.APPROVE_OPTION) {
					File fichero = fileChooser.getSelectedFile();
					fotoPerfil = fichero.getAbsolutePath();
				}
			}
		});
		panel_10.add(btnFileChooser);
//		panel_10.add(fileChooser);

		JPanel panelAddPresent = new JPanel();
		FlowLayout fl_panelAddPresent = (FlowLayout) panelAddPresent.getLayout();
		fl_panelAddPresent.setAlignment(FlowLayout.LEFT);
		panelAddPresent.setBackground(Color.WHITE);
		centro.add(panelAddPresent);

		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(Color.WHITE);
		separator_4.setPreferredSize(new Dimension(3, 20));
		panelAddPresent.add(separator_4);

		JPanel panel_11 = new JPanel();
		panel_11.setBackground(Color.WHITE);
		panel_11.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelAddPresent.add(panel_11);

		JLabel lblNewLabel_2 = new JLabel("Editar presentación");
		panel_11.add(lblNewLabel_2);

		JSeparator separator_6 = new JSeparator();
		separator_6.setForeground(Color.WHITE);
		separator_6.setPreferredSize(new Dimension(8, 2));
		panel_11.add(separator_6);

		JButton btnPresentacion = new JButton("+");

		btnPresentacion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (ta == null) {
					ta = new JTextArea(20, 20);
					ta.setText(controlador.getUsuarioActual().getDescripcion());
					sp = new JScrollPane(ta);
				}
				switch (JOptionPane.showConfirmDialog(null, sp, "Descripcion", 1)) {
				case JOptionPane.OK_OPTION:
					
					descripcion = ta.getText();
					break;
				}
			}
		});
		panel_11.add(btnPresentacion);

		lblWarningDescripcion = new JLabel("*Máximo 200 caracteres*");
		lblWarningDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblWarningDescripcion.setVisible(false);
		lblWarningDescripcion.setForeground(Color.RED);
		panelAddPresent.add(lblWarningDescripcion);

	}

	private void volverPerfil() {
		frmEditarPerfil.dispose();
		PerfilUsuario perfilUsuario = new PerfilUsuario(controlador.getUsuarioActual());
		perfilUsuario.frmPhototds.setVisible(true);
	}

	private void ocultarWarnings() {
		lblWarningEmail.setVisible(false);
		lblWarningNombre.setVisible(false);
		lblWarningUsuario.setVisible(false);
		lblWarningContraseña.setVisible(false);
		lblWarningRepetirContraseña.setVisible(false);
		lblWarningDescripcion.setVisible(false);
	}

	private boolean comprobarCampos() {

		boolean b = true;
		ocultarWarnings();

		if (txtEmail.getText().trim().isEmpty()) {
			lblWarningEmail.setVisible(true);
			b = false;
		}

		if (txtNombre.getText().trim().isEmpty()) {
			lblWarningNombre.setVisible(true);
			b = false;
		}

		if (txtApellidos.getText().trim().isEmpty()) {
			lblWarningApellidos.setVisible(true);
			b = false;
		}

		if (!txtNombreDeUsuario.getText().trim().equals(controlador.getUsuarioActual().getUsuario())) {
			if (existeUsuario(txtNombreDeUsuario.getText().trim())) {
				lblWarningUsuario.setText("*Ya existe*");
				lblWarningUsuario.setVisible(true);
				b = false;
			}
		}

		if (txtNombreDeUsuario.getText().trim().isEmpty()) {
			lblWarningUsuario.setText("*Campo vacío*");
			lblWarningUsuario.setVisible(true);
			b = false;
		}

		String passwd = String.valueOf(passwordField.getPassword());
		String passwd2 = String.valueOf(passwordField_1.getPassword());

		if (passwd.equals("")) {
			lblWarningContraseña.setVisible(true);
			lblWarningContraseña.setText("*Campo vacío*");
			b = false;
		}

		if (passwd2.equals("")) {
			lblWarningRepetirContraseña.setVisible(true);
			lblWarningRepetirContraseña.setText("*Campo vacío*");
			b = false;
		}

		if (!passwd.equals(passwd2)) {
			lblWarningContraseña.setVisible(true);
			lblWarningContraseña.setText("*No coinciden*");
			lblWarningRepetirContraseña.setVisible(true);
			lblWarningRepetirContraseña.setText("*No coinciden*");
			b = false;
		}

		if (descripcion != null && descripcion.length() > 200) {
			lblWarningDescripcion.setVisible(true);
			b = false;
		}

		return b;
	}

	private boolean existeUsuario(String usuario) {
		return controlador.existeUsuario(usuario);
	}

}
