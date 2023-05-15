package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.ControladorPhotoTDS;
import dominio.Album;

public class EliminarAlbum extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 * @param ventanaAlbum 
	 * @param album2 
	 */
	public EliminarAlbum(Album album, JFrame ventanaAlbum) {
		
		ControladorPhotoTDS controlador = ControladorPhotoTDS.getUnicaInstancia();
		
		setBounds(100, 100, 450, 143);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblNewLabel = new JLabel("Estás a punto de eliminar el album y todas sus fotos.");
			lblNewLabel.setForeground(new Color(255, 0, 0));
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("¿Estás de acuerdo?");
			lblNewLabel_1.setForeground(new Color(255, 0, 0));
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
			contentPanel.add(lblNewLabel_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						//controlador.eliminarAlbum(album);
						salir();
						ventanaAlbum.dispose();
					}
				});
				
			}
			{
				JButton cancelButton = new JButton("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						salir();
					}

					
				});
			}
		}
	}
	
	private void salir() {
		
		this.dispose();
	}

}
