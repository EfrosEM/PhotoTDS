package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;


public class Login {

	private JFrame frame;
;

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
		frame.setBounds(100, 100, 450, 380);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setMinimumSize(new Dimension(32767, 100));
		panelTitulo.setMaximumSize(new Dimension(32767, 100));
		panelTitulo.setPreferredSize(new Dimension(10, 80));
		frame.getContentPane().add(panelTitulo);
		panelTitulo.setLayout(new BoxLayout(panelTitulo, BoxLayout.Y_AXIS));
		
		Component rigidArea = Box.createRigidArea(new Dimension(25, 25));
		panelTitulo.add(rigidArea);
		
		JLabel lblPhotoTDS = new JLabel("PhotoTDS");
		lblPhotoTDS.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblPhotoTDS.setFont(new Font("Arial", Font.BOLD, 30));
		panelTitulo.add(lblPhotoTDS);
		
	}

}
