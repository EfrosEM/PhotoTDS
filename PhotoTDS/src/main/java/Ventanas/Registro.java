package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.Dimension;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.JPasswordField;

public class Registro {

	private JFrame frmRegistroUsuario;
	private JTextField txtEmail;
	private JTextField txtNombreCompleto;
	private JTextField txtNombreDeUsuario;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro window = new Registro();
					window.frmRegistroUsuario.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Registro() {
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
		
		JPanel titulo = new JPanel();
		titulo.setBackground(Color.WHITE);
		norte.add(titulo);
		
		JLabel lbltitulo = new JLabel("PhotoTDS");
		lbltitulo.setFont(new Font("Arial", Font.BOLD, 20));
		titulo.add(lbltitulo);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setSize(new Dimension(0, 500));
		separator_1.setPreferredSize(new Dimension(0, 10));
		separator_1.setForeground(Color.WHITE);
		norte.add(separator_1);
		
		JPanel texto = new JPanel();
		texto.setBackground(Color.WHITE);
		norte.add(texto);
		
		JTextArea txtrSiTeRegistras = new JTextArea();
		txtrSiTeRegistras.setWrapStyleWord(true);
		txtrSiTeRegistras.setEditable(false);
		txtrSiTeRegistras.setColumns(26);
		txtrSiTeRegistras.setLineWrap(true);
		txtrSiTeRegistras.setFont(new Font("Arial", Font.PLAIN, 15));
		txtrSiTeRegistras.setText("Si te registras podr\u00E1s compartir fotos y ver las fotos de tus amigos");
		texto.add(txtrSiTeRegistras);
		
		JPanel sur = new JPanel();
		sur.setBackground(Color.WHITE);
		frmRegistroUsuario.getContentPane().add(sur, BorderLayout.SOUTH);
		sur.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		sur.add(panel_4);
		
		JButton btnNewButton = new JButton("OK");
		panel_4.add(btnNewButton);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.WHITE);
		sur.add(panel_8);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		panel_8.add(btnNewButton_1);
		
		JPanel centro = new JPanel();
		centro.setBackground(Color.WHITE);
		frmRegistroUsuario.getContentPane().add(centro, BorderLayout.CENTER);
		centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 5));
		panel.setMinimumSize(new Dimension(10, 5));
		panel.setBackground(Color.WHITE);
		centro.add(panel);
		FlowLayout fl_panel = new FlowLayout(FlowLayout.RIGHT, 5, 5);
		panel.setLayout(fl_panel);
		
		JLabel lblNewLabel_3 = new JLabel("Email: ");
		lblNewLabel_3.setToolTipText("");
		panel.add(lblNewLabel_3);
		
		txtEmail = new JTextField();
		txtEmail.setPreferredSize(new Dimension(4, 20));
		txtEmail.setToolTipText("");
		txtEmail.setForeground(Color.DARK_GRAY);
		panel.add(txtEmail);
		txtEmail.setColumns(20);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_1.getLayout();
		flowLayout_4.setAlignment(FlowLayout.RIGHT);
		panel_1.setPreferredSize(new Dimension(10, 5));
		panel_1.setBackground(Color.WHITE);
		centro.add(panel_1);
		
		JLabel lblNewLabel_4 = new JLabel("Nombre completo: ");
		panel_1.add(lblNewLabel_4);
		
		txtNombreCompleto = new JTextField();
		txtNombreCompleto.setToolTipText("wfjgnwkejbnl");
		txtNombreCompleto.setForeground(Color.DARK_GRAY);
		panel_1.add(txtNombreCompleto);
		txtNombreCompleto.setColumns(20);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_2.getLayout();
		flowLayout_5.setAlignment(FlowLayout.RIGHT);
		panel_2.setPreferredSize(new Dimension(10, 5));
		panel_2.setBackground(Color.WHITE);
		centro.add(panel_2);
		
		JLabel lblNewLabel_5 = new JLabel("Nombre de usuario: ");
		panel_2.add(lblNewLabel_5);
		
		txtNombreDeUsuario = new JTextField();
		txtNombreDeUsuario.setForeground(Color.DARK_GRAY);
		panel_2.add(txtNombreDeUsuario);
		txtNombreDeUsuario.setColumns(20);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) panel_3.getLayout();
		flowLayout_6.setAlignment(FlowLayout.RIGHT);
		panel_3.setPreferredSize(new Dimension(10, 5));
		panel_3.setBackground(Color.WHITE);
		centro.add(panel_3);
		
		JLabel lblNewLabel_6 = new JLabel("Contrase\u00F1a: ");
		panel_3.add(lblNewLabel_6);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(20);
		panel_3.add(passwordField);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setForeground(Color.WHITE);
		centro.add(separator_7);
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_5.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_5.setBackground(Color.WHITE);
		centro.add(panel_5);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.WHITE);
		panel_5.add(separator_2);
		separator_2.setPreferredSize(new Dimension(3, 20));
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Color.WHITE);
		panel_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.add(panel_9);
		panel_9.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Fecha de Nacimiento");
		panel_9.add(lblNewLabel);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setForeground(Color.WHITE);
		separator_5.setPreferredSize(new Dimension(64, 2));
		panel_9.add(separator_5);
		
		JButton btnNewButton_2 = new JButton("New button");
		panel_9.add(btnNewButton_2);
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_6.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_6.setBackground(Color.WHITE);
		centro.add(panel_6);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.WHITE);
		separator_3.setPreferredSize(new Dimension(3, 20));
		panel_6.add(separator_3);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_10.setBackground(Color.WHITE);
		panel_6.add(panel_10);
		
		JLabel lblNewLabel_1 = new JLabel("A\u00F1adir foto del usuario (opcional)");
		panel_10.add(lblNewLabel_1);
		
		JButton btnNewButton_3 = new JButton("New button");
		panel_10.add(btnNewButton_3);
		
		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_7.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_7.setBackground(Color.WHITE);
		centro.add(panel_7);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(Color.WHITE);
		separator_4.setPreferredSize(new Dimension(3, 20));
		panel_7.add(separator_4);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(Color.WHITE);
		panel_11.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7.add(panel_11);
		
		JLabel lblNewLabel_2 = new JLabel("A\u00F1adir presentacion (opcional)");
		panel_11.add(lblNewLabel_2);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setForeground(Color.WHITE);
		separator_6.setPreferredSize(new Dimension(8, 2));
		panel_11.add(separator_6);
		
		JButton btnNewButton_4 = new JButton("New button");
		panel_11.add(btnNewButton_4);
	}

}
