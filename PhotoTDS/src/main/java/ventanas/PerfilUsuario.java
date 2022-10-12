package ventanas;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Panel;



public class PerfilUsuario {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PerfilUsuario window = new PerfilUsuario();
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
	public PerfilUsuario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(PerfilUsuario.class.getResource("/recursos/image.png")));
		frame.setBounds(100, 100, 577, 629);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorte = new JPanel();
		frame.getContentPane().add(panelNorte, BorderLayout.NORTH);
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
		btnNewButton_2.setIcon(new ImageIcon(PerfilUsuario.class.getResource("/recursos/usuario (Personalizado).png")));
		panelNorte.add(btnNewButton_2);
		
		Component rigidArea_3 = Box.createRigidArea(new Dimension(10, 10));
		panelNorte.add(rigidArea_3);
		
		JPanel panelCentral = new JPanel();
		panelCentral.setBorder(new LineBorder(new Color(192, 192, 192)));
		frame.getContentPane().add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panelCentral.add(panel, BorderLayout.WEST);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		Component rigidArea_7 = Box.createRigidArea(new Dimension(20, 20));
		panel.add(rigidArea_7);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setSize(new Dimension(5, 5));
		lblNewLabel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel_4.setIcon(new ImageIcon(PerfilUsuario.class.getResource("/recursos/user.png")));
		panel.add(lblNewLabel_4);
		
		Component rigidArea_8 = Box.createRigidArea(new Dimension(20, 20));
		panel.add(rigidArea_8);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panelCentral.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		Panel panel_3 = new Panel();
		panel_3.setMaximumSize(new Dimension(32767, 200));
		panel_1.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel_5 = new JLabel("Nombre de Usuario");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_3.add(lblNewLabel_5);
		
		Component rigidArea_9 = Box.createRigidArea(new Dimension(20, 20));
		panel_3.add(rigidArea_9);
		
		JButton btnNewButton_3 = new JButton("Editar Perfil");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_3.setForeground(Color.WHITE);
		btnNewButton_3.setBackground(new Color(51, 153, 255));
		panel_3.add(btnNewButton_3);
		
		Panel panel_4 = new Panel();
		panel_4.setMaximumSize(new Dimension(32767, 200));
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_4);
		
		JLabel lblNewLabel_6 = new JLabel("0");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_4.add(lblNewLabel_6);
		
		JLabel lblNewLabel_8 = new JLabel("Publicaciones");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_4.add(lblNewLabel_8);
		
		Component rigidArea_10 = Box.createRigidArea(new Dimension(20, 20));
		panel_4.add(rigidArea_10);
		
		JLabel lblNewLabel_9 = new JLabel("0");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_4.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Seguidores");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_4.add(lblNewLabel_10);
		
		Component rigidArea_11 = Box.createRigidArea(new Dimension(20, 20));
		panel_4.add(rigidArea_11);
		
		JLabel lblNewLabel_11 = new JLabel("0");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_4.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("Seguidos");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_4.add(lblNewLabel_12);
		
		Panel panel_5 = new Panel();
		panel_5.setMaximumSize(new Dimension(32767, 200));
		FlowLayout flowLayout_1 = (FlowLayout) panel_5.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_5);
		
		JLabel lblNewLabel_7 = new JLabel("Nombre Completo");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_5.add(lblNewLabel_7);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panelCentral.add(panel_2, BorderLayout.SOUTH);
		
		JButton lblNewLabel_2 = new JButton("FOTOS");
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setBorder(null);
		lblNewLabel_2.setForeground(Color.DARK_GRAY);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_2.add(lblNewLabel_2);
		
		Component rigidArea_6 = Box.createRigidArea(new Dimension(20, 20));
		panel_2.add(rigidArea_6);
		
		JButton lblNewLabel_3 = new JButton("ÁLBUMES");
		lblNewLabel_3.setBackground(Color.WHITE);
		lblNewLabel_3.setBorder(null);
		lblNewLabel_3.setForeground(Color.DARK_GRAY);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_2.add(lblNewLabel_3);
		
		JPanel panelSur = new JPanel();
		frame.getContentPane().add(panelSur, BorderLayout.SOUTH);
		panelSur.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	}

}
