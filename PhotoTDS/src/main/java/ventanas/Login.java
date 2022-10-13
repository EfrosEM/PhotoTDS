package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;

public class Login {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

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
		frame.setBounds(100, 100, 408, 524);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel Sur = new JPanel();
		frame.getContentPane().add(Sur, BorderLayout.SOUTH);
		Sur.setLayout(new BoxLayout(Sur, BoxLayout.Y_AXIS));
		
		JPanel Centro = new JPanel();
		Centro.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frame.getContentPane().add(Centro, BorderLayout.CENTER);
		Centro.setLayout(new BoxLayout(Centro, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		Centro.add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("PhotoTDS");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		panel_1.add(lblNewLabel_1);
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		Centro.add(rigidArea_2);
		
		JPanel panel_2 = new JPanel();
		Centro.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		panel_2.add(rigidArea);
		
		textField = new JTextField();
		panel_2.add(textField);
		textField.setColumns(10);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		panel_2.add(rigidArea_1);
		
		Component rigidArea_3 = Box.createRigidArea(new Dimension(20, 20));
		Centro.add(rigidArea_3);
		
		JPanel panel_3 = new JPanel();
		Centro.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
		
		Component rigidArea_6 = Box.createRigidArea(new Dimension(20, 20));
		panel_3.add(rigidArea_6);
		
		textField_1 = new JTextField();
		panel_3.add(textField_1);
		textField_1.setColumns(10);
		
		Component rigidArea_8 = Box.createRigidArea(new Dimension(20, 20));
		panel_3.add(rigidArea_8);
		
		Component rigidArea_9 = Box.createRigidArea(new Dimension(20, 20));
		Centro.add(rigidArea_9);
		
		JPanel este = new JPanel();
		frame.getContentPane().add(este, BorderLayout.EAST);
		
		JPanel oeste = new JPanel();
		frame.getContentPane().add(oeste, BorderLayout.WEST);
		
		JPanel norte = new JPanel();
		frame.getContentPane().add(norte, BorderLayout.NORTH);
	}

}
