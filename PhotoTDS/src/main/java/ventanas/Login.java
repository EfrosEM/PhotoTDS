package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Font;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;


public class Login {

	private JFrame frame;
	private JTextField txtNombreDeUsuario;
	private JPasswordField txtContrasea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/recursos/camera.png")));
		frame.setBounds(100, 100, 408, 376);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel Sur = new JPanel();
		frame.getContentPane().add(Sur, BorderLayout.SOUTH);
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
		centro.add(btnNewButton_1);
		
		Component rigidArea_4 = Box.createRigidArea(new Dimension(20, 20));
		centro.add(rigidArea_4);
		
		JPanel Centro = new JPanel();
		Centro.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frame.getContentPane().add(Centro, BorderLayout.CENTER);
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
		txtNombreDeUsuario.setText("Nombre de usuario");
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
		
		txtContrasea = new JPasswordField();
		txtContrasea.setText("Contraseña");
		txtContrasea.setForeground(Color.BLACK);
		txtContrasea.setMaximumSize(new Dimension(2147483647, 70));
		panel_3.add(txtContrasea);
		txtContrasea.setColumns(10);
		
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
		panel.add(btnNewButton);
		
		JPanel este = new JPanel();
		frame.getContentPane().add(este, BorderLayout.EAST);
		
		JPanel oeste = new JPanel();
		frame.getContentPane().add(oeste, BorderLayout.WEST);
		
		JPanel norte = new JPanel();
		frame.getContentPane().add(norte, BorderLayout.NORTH);
	}

}
