package ventanas;

import java.awt.Toolkit;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import java.awt.FlowLayout;

public class AñadirFoto {

	/**
	 * 
	 */
	protected JFrame frmPhototds;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public AñadirFoto(JEditorPane editorPane) {
		initialize(editorPane);
	}
	
	private void initialize(JEditorPane editorPane) {
		frmPhototds = new JFrame();
		frmPhototds.setTitle("PhotoTDS");
		frmPhototds.setResizable(false);
		frmPhototds.setIconImage(Toolkit.getDefaultToolkit().getImage(PerfilUsuario.class.getResource("/recursos/image.png")));
		frmPhototds.setBounds(100, 100, 300, 300);
		frmPhototds.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPhototds.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		
		frmPhototds.getContentPane().add(editorPane);
	}

}
