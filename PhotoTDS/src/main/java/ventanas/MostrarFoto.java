package ventanas;

import javax.swing.JFrame;

import dominio.Foto;
import javax.swing.JLabel;
import java.awt.BorderLayout;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class MostrarFoto {

	protected JFrame frame;
	private Foto foto;

	/**
	 * Create the application.
	 */
	public MostrarFoto(Foto foto) {
		this.foto = foto;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MostrarFoto.class.getResource("/recursos/image.png")));
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel();
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(foto.getRuta()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		ImageIcon icon = new ImageIcon(image);
		frame.setBounds(100, 100, image.getWidth()+20, image.getHeight()+80);
		frame.setTitle(foto.getTitulo());
		lblNewLabel.setIcon(icon);
		frame.getContentPane().add(lblNewLabel, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Eliminar");
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EliminarFoto dialog = new EliminarFoto(foto, frame);
				dialog.setVisible(true);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				
			}
		});
	}

}
