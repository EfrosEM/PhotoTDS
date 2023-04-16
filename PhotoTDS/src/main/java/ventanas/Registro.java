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

import com.toedter.calendar.JDateChooser;

//import com.toedter.calendar.JCalendar;

import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JFileChooser;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Insets;


public class Registro {

	private static final File FOTO_PERFIL_DEFAULT = new File("src/main/java/recursos/defaultUser.png");
	/**
	 * 
	 */
	protected JFrame frmRegistroUsuario;
	private JTextField txtEmail;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtNombreDeUsuario;
	private JPasswordField passwordField;
	private JDateChooser dateChooser;
	private Login loginWindow;
	private JPasswordField passwordField_1;
	private JLabel lblWarningEmail;
	private JLabel lblWarningNombre;
	private JLabel lblWarningApellidos;
	private JLabel lblWarningUsuario;
	private JLabel lblWarningContraseña;
	private JLabel lblWarningRepetirContraseña;
	private JLabel lblWarningFechaNacimiento;
	private JLabel lblWarningDescripcion;
	
	private String descripcion = null;
	private String fotoPerfil = FOTO_PERFIL_DEFAULT.getAbsolutePath();
	
	private JTextArea ta = null;
	private JScrollPane sp = null;
	/**
	 * Create the application.
	 */
	public Registro(Login lw) {
		this.loginWindow = lw;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmRegistroUsuario = new JFrame();
		frmRegistroUsuario.setResizable(false);
		frmRegistroUsuario.setBackground(Color.WHITE);
		frmRegistroUsuario.setTitle("Registro Usuario");
		frmRegistroUsuario.setIconImage(Toolkit.getDefaultToolkit().getImage(Registro.class.getResource("/Recursos/image.png")));
		frmRegistroUsuario.setBounds(100, 100, 450, 570); //376 y 538
		frmRegistroUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel norte = new JPanel();
		norte.setBackground(Color.WHITE);
		frmRegistroUsuario.getContentPane().add(norte, BorderLayout.NORTH);
		norte.setLayout(new BoxLayout(norte, BoxLayout.Y_AXIS));
		
		JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(0, 20));
		separator.setSize(new Dimension(0, 500));
		separator.setForeground(Color.WHITE);
		norte.add(separator);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBackground(Color.WHITE);
		norte.add(panelTitulo);
		
		JLabel lbltitulo = new JLabel("PhotoTDS");
		lbltitulo.setFont(new Font("Arial", Font.BOLD, 20));
		panelTitulo.add(lbltitulo);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setSize(new Dimension(0, 500));
		separator_1.setPreferredSize(new Dimension(0, 10));
		separator_1.setForeground(Color.WHITE);
		norte.add(separator_1);
		
		JPanel panelTexto = new JPanel();
		panelTexto.setBackground(Color.WHITE);
		norte.add(panelTexto);
		
		JTextArea txtrSiTeRegistras = new JTextArea();
		txtrSiTeRegistras.setWrapStyleWord(true);
		txtrSiTeRegistras.setEditable(false);
		txtrSiTeRegistras.setColumns(26);
		txtrSiTeRegistras.setLineWrap(true);
		txtrSiTeRegistras.setFont(new Font("Arial", Font.PLAIN, 15));
		txtrSiTeRegistras.setText("Si te registras podr\u00E1s compartir fotos y ver las fotos de tus amigos");
		panelTexto.add(txtrSiTeRegistras);
		
		JPanel sur = new JPanel();
		sur.setBackground(Color.WHITE);
		frmRegistroUsuario.getContentPane().add(sur, BorderLayout.SOUTH);
		sur.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JPanel panelBotonRegistrar = new JPanel();
		panelBotonRegistrar.setBackground(Color.WHITE);
		FlowLayout fl_panelBotonRegistrar = (FlowLayout) panelBotonRegistrar.getLayout();
		fl_panelBotonRegistrar.setAlignment(FlowLayout.RIGHT);
		sur.add(panelBotonRegistrar);
		
		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comprobarCampos()) {
					String nombre = txtNombre.getText();
					String usuario = txtNombreDeUsuario.getText();
					String apellidos = txtApellidos.getText();
					String password = String.valueOf(passwordField.getPassword());
					String email = txtEmail.getText();
					Date fecha = dateChooser.getDate();
				
					boolean isRegistered = loginWindow.registrar(nombre, apellidos, usuario, password, email, fecha, descripcion, fotoPerfil);
				
					if (isRegistered) {
						volverLogin();
					}
				}
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
				volverLogin();
			}
		});
		panelBotonCancel.add(btnNewButton_1);
		
		JPanel centro = new JPanel();
		centro.setBackground(Color.WHITE);
		frmRegistroUsuario.getContentPane().add(centro, BorderLayout.CENTER);
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
		
		JPanel panelUsuario = new JPanel();
		FlowLayout fl_panelUsuario = (FlowLayout) panelUsuario.getLayout();
		fl_panelUsuario.setAlignment(FlowLayout.RIGHT);
		panelUsuario.setPreferredSize(new Dimension(10, 5));
		panelUsuario.setBackground(Color.WHITE);
		centro.add(panelUsuario);
		
		lblWarningUsuario = new JLabel("*Campo vacío*");
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
		
		JPanel panelContraseña = new JPanel();
		FlowLayout fl_panelContraseña = (FlowLayout) panelContraseña.getLayout();
		fl_panelContraseña.setAlignment(FlowLayout.RIGHT);
		panelContraseña.setPreferredSize(new Dimension(10, 5));
		panelContraseña.setBackground(Color.WHITE);
		centro.add(panelContraseña);
		
		lblWarningContraseña = new JLabel("*Campo vacío*");
		lblWarningContraseña.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblWarningContraseña.setVisible(false);
		lblWarningContraseña.setForeground(Color.RED);
		panelContraseña.add(lblWarningContraseña);
		
		JLabel lblNewLabel_6 = new JLabel("Contrase\u00F1a: ");
		panelContraseña.add(lblNewLabel_6);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(20);
		panelContraseña.add(passwordField);
		
		JPanel panelRepetirContraseña = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelRepetirContraseña.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelRepetirContraseña.setPreferredSize(new Dimension(10, 5));
		panelRepetirContraseña.setBackground(Color.WHITE);
		centro.add(panelRepetirContraseña);
		
		lblWarningRepetirContraseña = new JLabel("*Campo vacío*");
		lblWarningRepetirContraseña.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblWarningRepetirContraseña.setVisible(false);
		lblWarningRepetirContraseña.setForeground(Color.RED);
		panelRepetirContraseña.add(lblWarningRepetirContraseña);
		
		JLabel lblNewLabel_6_1 = new JLabel("Repetir Contraseña:");
		panelRepetirContraseña.add(lblNewLabel_6_1);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setColumns(20);
		panelRepetirContraseña.add(passwordField_1);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		centro.add(rigidArea);
		
		JPanel panelFechaNac = new JPanel();
		FlowLayout fl_panelFechaNac = (FlowLayout) panelFechaNac.getLayout();
		fl_panelFechaNac.setAlignment(FlowLayout.LEFT);
		panelFechaNac.setBackground(Color.WHITE);
		centro.add(panelFechaNac);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.WHITE);
		panelFechaNac.add(separator_2);
		separator_2.setPreferredSize(new Dimension(3, 20));
		
		JPanel panel_9 = new JPanel();
		panel_9.setPreferredSize(new Dimension(250, 35));
		panel_9.setSize(new Dimension(200, 100));
		panel_9.setBackground(Color.WHITE);
		panel_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelFechaNac.add(panel_9);
		panel_9.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Fecha de nacimiento");
		panel_9.add(lblNewLabel);
		
		JSeparator separator_4_3 = new JSeparator();
		separator_4_3.setPreferredSize(new Dimension(10, 10));
		separator_4_3.setForeground(Color.WHITE);
		panel_9.add(separator_4_3);
		
		dateChooser = new JDateChooser();
		dateChooser.setPreferredSize(new Dimension(95, 20));
		dateChooser.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_9.add(dateChooser);
		
		lblWarningFechaNacimiento = new JLabel("*Campo vacío*");
		lblWarningFechaNacimiento.setVisible(false);
		lblWarningFechaNacimiento.setForeground(Color.RED);
		panelFechaNac.add(lblWarningFechaNacimiento);
				
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
		FlowLayout flowLayout_2 = (FlowLayout) panel_10.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_10.setPreferredSize(new Dimension(250, 35));
		panel_10.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_10.setBackground(Color.WHITE);
		panelAddFoto.add(panel_10);
		
		JLabel lblNewLabel_1 = new JLabel("A\u00F1adir foto del usuario (opcional)");
		panel_10.add(lblNewLabel_1);
		
//		JFileChooser fileChooser = new JFileChooser();
//		fileChooser.setVisible(false);
		
		JButton btnFileChooser = new JButton("+");
		btnFileChooser.setMargin(new Insets(0, 0, 0, 0));
		btnFileChooser.setPreferredSize(new Dimension(25, 25));
		btnFileChooser.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnFileChooser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int seleccion = fileChooser.showOpenDialog(panelAddFoto);
				
				if (seleccion == JFileChooser.APPROVE_OPTION) {
					File fichero = fileChooser.getSelectedFile();
					fotoPerfil = fichero.getAbsolutePath();
					System.out.println(fotoPerfil);
				}
			}
		});
		
		JSeparator separator_4_3_1 = new JSeparator();
		separator_4_3_1.setPreferredSize(new Dimension(10, 10));
		separator_4_3_1.setForeground(Color.WHITE);
		panel_10.add(separator_4_3_1);
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
		FlowLayout flowLayout_3 = (FlowLayout) panel_11.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_11.setPreferredSize(new Dimension(250, 35));
		panel_11.setBackground(Color.WHITE);
		panel_11.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelAddPresent.add(panel_11);
		
		JLabel lblNewLabel_2 = new JLabel("A\u00F1adir presentacion (opcional)");
		panel_11.add(lblNewLabel_2);
		
		JButton btnPresentacion = new JButton("+");
		btnPresentacion.setMargin(new Insets(0, 0, 0, 0));
		btnPresentacion.setPreferredSize(new Dimension(25, 25));
		btnPresentacion.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		btnPresentacion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (ta == null) {
					ta = new JTextArea(20, 20);
					sp = new JScrollPane(ta);
				}
				switch (JOptionPane.showConfirmDialog(null, sp, "Descripcion", 1)) {
				    case JOptionPane.OK_OPTION:
				        System.out.println(ta.getText().length());
				        descripcion = ta.getText();
				        break;
				}
			}
		});
		
		JSeparator separator_4_1 = new JSeparator();
		separator_4_1.setPreferredSize(new Dimension(23, 10));
		separator_4_1.setForeground(Color.WHITE);
		panel_11.add(separator_4_1);
		panel_11.add(btnPresentacion);
		
		lblWarningDescripcion = new JLabel("*Máximo 200 caracteres*");
		lblWarningDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblWarningDescripcion.setVisible(false);
		lblWarningDescripcion.setForeground(Color.RED);
		panelAddPresent.add(lblWarningDescripcion);
	
	}
	
	private void volverLogin() {
		frmRegistroUsuario.dispose();
		loginWindow.frmPhototds.setVisible(true);
	}
	
	private void ocultarWarnings() {
		lblWarningEmail.setVisible(false);
		lblWarningNombre.setVisible(false);
		lblWarningUsuario.setVisible(false);
		lblWarningContraseña.setVisible(false);
		lblWarningRepetirContraseña.setVisible(false);
		lblWarningFechaNacimiento.setVisible(false);
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
		
		if (loginWindow.existeUsuario(txtNombreDeUsuario.getText().trim())) {
			lblWarningUsuario.setText("*Ya existe*");
			lblWarningUsuario.setVisible(true);
			b = false;
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
		
		if (dateChooser.getDate() == null) {
			lblWarningFechaNacimiento.setVisible(true);
			b = false;
		}
		
		if (descripcion != null && descripcion.length() > 200) {
			lblWarningDescripcion.setVisible(true);
			b = false;
		}
		
		return b;
	}

}
