package ventanas;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.awt.Font;

import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;

import controlador.ControladorPhotoTDS;

public class Login {

	protected JFrame frmPhototds;
	private JTextField txtNombreDeUsuario;
	private JPasswordField txtPassword;
	private ControladorPhotoTDS controlador;

	/**
	 * Create the application.
	 */
	public Login() {
		controlador = ControladorPhotoTDS.getUnicaInstancia();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPhototds = new JFrame();
		frmPhototds.setTitle("PhotoTDS");
		frmPhototds.setResizable(false);
		frmPhototds.setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/recursos/camera.png")));
		frmPhototds.setBounds(100, 100, 408, 376);
		frmPhototds.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPhototds.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel Sur = new JPanel();
		frmPhototds.getContentPane().add(Sur, BorderLayout.SOUTH);
		Sur.setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		Sur.add(panel_4, BorderLayout.NORTH);

		JPanel panel_5 = new JPanel();
		Sur.add(panel_5, BorderLayout.SOUTH);

		JPanel panel_6 = new JPanel();
		Sur.add(panel_6, BorderLayout.WEST);

		JPanel panel_7 = new JPanel();
		Sur.add(panel_7, BorderLayout.EAST);

		JPanel centro = new JPanel();
		centro.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		Sur.add(centro, BorderLayout.CENTER);
		centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));

		Component rigidArea_10 = Box.createRigidArea(new Dimension(20, 20));
		rigidArea_10.setPreferredSize(new Dimension(5, 5));
		centro.add(rigidArea_10);

		JLabel lblNewLabel = new JLabel("¿No tienes una cuenta?");
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setForeground(Color.RED);
		centro.add(lblNewLabel);

		Component rigidArea_7 = Box.createRigidArea(new Dimension(20, 20));
		rigidArea_7.setPreferredSize(new Dimension(5, 5));
		centro.add(rigidArea_7);

		JButton btnNewButton_1 = new JButton("Crear una cuenta");
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setPreferredSize(new Dimension(325, 23));
		btnNewButton_1.setMaximumSize(new Dimension(325, 23));
		btnNewButton_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				abrirRegistro();
			}
		});
		centro.add(btnNewButton_1);

		Component rigidArea_4 = Box.createRigidArea(new Dimension(20, 20));
		centro.add(rigidArea_4);

		JPanel Centro = new JPanel();
		Centro.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frmPhototds.getContentPane().add(Centro, BorderLayout.CENTER);
		Centro.setLayout(new BoxLayout(Centro, BoxLayout.Y_AXIS));

		JPanel panel_1 = new JPanel();
		Centro.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		Component rigidArea_5 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_5);

		JLabel lblNewLabel_1 = new JLabel("PhotoTDS");
		lblNewLabel_1.setBorder(null);
		lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		panel_1.add(lblNewLabel_1);

		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_2);

		JPanel panel_2 = new JPanel();
		Centro.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));

		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		panel_2.add(rigidArea);

		txtNombreDeUsuario = new JTextField();
		txtNombreDeUsuario.setForeground(Color.BLACK);
		txtNombreDeUsuario.setText("");
		txtNombreDeUsuario.setMaximumSize(new Dimension(2147483647, 70));
		panel_2.add(txtNombreDeUsuario);
		txtNombreDeUsuario.setColumns(10);

		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		panel_2.add(rigidArea_1);

		Component rigidArea_3 = Box.createRigidArea(new Dimension(20, 20));
		Centro.add(rigidArea_3);

		JPanel panel_3 = new JPanel();
		Centro.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));

		Component rigidArea_6 = Box.createRigidArea(new Dimension(20, 20));
		panel_3.add(rigidArea_6);

		txtPassword = new JPasswordField();
		txtPassword.setText("");
		txtPassword.setForeground(Color.BLACK);
		txtPassword.setMaximumSize(new Dimension(2147483647, 70));
		panel_3.add(txtPassword);
		txtPassword.setColumns(10);

		Component rigidArea_8 = Box.createRigidArea(new Dimension(20, 20));
		panel_3.add(rigidArea_8);

		Component rigidArea_9 = Box.createRigidArea(new Dimension(20, 20));
		Centro.add(rigidArea_9);

		JPanel panel = new JPanel();
		panel.setBorder(null);
		Centro.add(panel);

		JButton btnNewButton = new JButton("Iniciar Sesión");
		btnNewButton.setBorder(new LineBorder(Color.GRAY));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(0, 153, 255));
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewButton.setPreferredSize(new Dimension(325, 40));
		btnNewButton.setMinimumSize(new Dimension(200, 60));
		btnNewButton.setMaximumSize(new Dimension(200, 60));
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String usuario = txtNombreDeUsuario.getText();
				String password = String.valueOf(txtPassword.getPassword());
				boolean isLogged = controlador.loginUsuario(usuario, password);

				if (isLogged) {
					abrirInicio();
				} else {
					JOptionPane.showMessageDialog(frmPhototds, "Nombre de usuario o contraseña incorrectos", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		panel.add(btnNewButton);

		JPanel este = new JPanel();
		frmPhototds.getContentPane().add(este, BorderLayout.EAST);

		JPanel oeste = new JPanel();
		frmPhototds.getContentPane().add(oeste, BorderLayout.WEST);

		JPanel norte = new JPanel();
		frmPhototds.getContentPane().add(norte, BorderLayout.NORTH);
	}

	private void abrirRegistro() {
		frmPhototds.setVisible(false);
		Registro r = new Registro(this);
		r.frmRegistroUsuario.setVisible(true);
	}

	public boolean registrar(String nombre, String apellidos, String usuario, String password, String email, Date fecha,
			String descripcion, String fotoPerfil) {
		return controlador.registrarUsuario(nombre, apellidos, email, usuario, password, fecha, descripcion,
				fotoPerfil);
	}

	public boolean existeUsuario(String usuario) {
		return controlador.existeUsuario(usuario);
	}

	private void abrirInicio() {
		frmPhototds.dispose();
		Inicio i = new Inicio();
		i.frmPhototds.setVisible(true);
	}

	public void setVisible(Boolean bool) {
		this.frmPhototds.setVisible(bool);
	}
}
