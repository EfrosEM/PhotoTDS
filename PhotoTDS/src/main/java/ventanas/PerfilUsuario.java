package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
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
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(PerfilUsuario.class.getResource("/recursos/image.png")));
		frame.setBounds(100, 100, 577, 616);
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
		panelNorte.add(rigidArea);
		
		JButton btnNewButton = new JButton("+");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setMaximumSize(new Dimension(20, 20));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelNorte.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("A+");
		btnNewButton_1.setMaximumSize(new Dimension(20, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panelNorte.add(btnNewButton_1);
		
		textField = new JTextField();
		textField.setMaximumSize(new Dimension(2147483647, 20));
		textField.setMinimumSize(new Dimension(7, 15));
		textField.setPreferredSize(new Dimension(7, 15));
		panelNorte.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(PerfilUsuario.class.getResource("/recursos/lupa.png")));
		panelNorte.add(lblNewLabel_1);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(50, 30));
		panelNorte.add(rigidArea_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.setIcon(new ImageIcon(PerfilUsuario.class.getResource("/recursos/usuario (Personalizado).png")));
		panelNorte.add(btnNewButton_2);
		
		Component rigidArea_3 = Box.createRigidArea(new Dimension(10, 10));
		panelNorte.add(rigidArea_3);
		
		JPanel panelCentral = new JPanel();
		frame.getContentPane().add(panelCentral, BorderLayout.CENTER);
		
		JPanel panelSur = new JPanel();
		frame.getContentPane().add(panelSur, BorderLayout.SOUTH);
	}

}
