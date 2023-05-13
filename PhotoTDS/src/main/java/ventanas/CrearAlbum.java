package ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.ControladorPhotoTDS;
import dominio.Usuario;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class CrearAlbum extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_1;
	private JLabel lblNewLabel_error;
	private ControladorPhotoTDS controlador = ControladorPhotoTDS.getUnicaInstancia();

	/**
	 * Create the dialog.
	 * 
	 * @param frmPhototds
	 */
	public CrearAlbum(JFrame perfil, Usuario usuario) {

		setIconImage(Toolkit.getDefaultToolkit().getImage(CrearAlbum.class.getResource("/recursos/image.png")));
		setTitle("Crear álbum");
		setBounds(100, 100, 450, 146);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			{
				JLabel lblNewLabel = new JLabel("Nombre del álbum: ");
				panel.add(lblNewLabel);
			}
			{
				textField_1 = new JTextField();
				textField_1.setColumns(25);
				panel.add(textField_1);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			{
				lblNewLabel_error = new JLabel("* Ya existe un álbum con ese nombre *");
				lblNewLabel_error.setVisible(false);
				lblNewLabel_error.setForeground(Color.RED);
				panel.add(lblNewLabel_error);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);

				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String nombreAlbum = textField_1.getText();
						Boolean exists = controlador.getUsuarioActual().existsAlbum(nombreAlbum);
						if (!exists){
							JEditorPane editorPane = new JEditorPane();
							AñadirFoto af = new AñadirFoto(editorPane, perfil, usuario, nombreAlbum);
							af.frmPhototds.setVisible(true);
						}
					}

				});

			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						volver();
					}
				});

				buttonPane.add(cancelButton);
			}
		}
	}

	private void volver() {
		this.dispose();
	}
	

}
