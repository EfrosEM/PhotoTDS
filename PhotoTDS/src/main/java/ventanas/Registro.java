package ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.Dimension;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

//import com.toedter.calendar.JCalendar;

import javax.swing.JPasswordField;

public class Registro extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JFrame frmRegistroUsuario;
	private JTextField txtEmail;
	private JTextField txtNombreCompleto;
	private JTextField txtNombreDeUsuario;
	private JPasswordField passwordField;
	private Login loginWindow;
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
		frmRegistroUsuario.setBounds(100, 100, 376, 538);
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
		
		JPanel panelBotonOk = new JPanel();
		panelBotonOk.setBackground(Color.WHITE);
		FlowLayout fl_panelBotonOk = (FlowLayout) panelBotonOk.getLayout();
		fl_panelBotonOk.setAlignment(FlowLayout.RIGHT);
		sur.add(panelBotonOk);
		
		JButton btnNewButton = new JButton("OK");
		panelBotonOk.add(btnNewButton);
		
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
		
		JLabel lblNewLabel_4 = new JLabel("Nombre completo: ");
		panelFullName.add(lblNewLabel_4);
		
		txtNombreCompleto = new JTextField();
		txtNombreCompleto.setToolTipText("wfjgnwkejbnl");
		txtNombreCompleto.setForeground(Color.DARK_GRAY);
		panelFullName.add(txtNombreCompleto);
		txtNombreCompleto.setColumns(20);
		
		JPanel panelUsuario = new JPanel();
		FlowLayout fl_panelUsuario = (FlowLayout) panelUsuario.getLayout();
		fl_panelUsuario.setAlignment(FlowLayout.RIGHT);
		panelUsuario.setPreferredSize(new Dimension(10, 5));
		panelUsuario.setBackground(Color.WHITE);
		centro.add(panelUsuario);
		
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
		
		JLabel lblNewLabel_6 = new JLabel("Contrase\u00F1a: ");
		panelContraseña.add(lblNewLabel_6);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(20);
		panelContraseña.add(passwordField);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setForeground(Color.WHITE);
		centro.add(separator_7);
		
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
		panel_9.setBackground(Color.WHITE);
		panel_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelFechaNac.add(panel_9);
		panel_9.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Fecha de nacimiento");
		panel_9.add(lblNewLabel);

		JSeparator separator_5 = new JSeparator();
		separator_5.setForeground(Color.WHITE);
		separator_5.setPreferredSize(new Dimension(64, 2));
		panel_9.add(separator_5);
		
		JButton btnNewButton_2 = new JButton("New button");
		panel_9.add(btnNewButton_2);
		
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
		
		JLabel lblNewLabel_1 = new JLabel("A\u00F1adir foto del usuario (opcional)");
		panel_10.add(lblNewLabel_1);
		
		JButton btnNewButton_3 = new JButton("New button");
		panel_10.add(btnNewButton_3);
		
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
		
		JLabel lblNewLabel_2 = new JLabel("A\u00F1adir presentacion (opcional)");
		panel_11.add(lblNewLabel_2);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setForeground(Color.WHITE);
		separator_6.setPreferredSize(new Dimension(8, 2));
		panel_11.add(separator_6);
		
		JButton btnNewButton_4 = new JButton("New button");
		panel_11.add(btnNewButton_4);
	
	}
	
	private void volverLogin() {
		frmRegistroUsuario.setVisible(false);
		loginWindow.frame.setVisible(true);
	}

}
