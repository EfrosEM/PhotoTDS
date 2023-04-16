package ventanas;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controlador.ControladorPhotoTDS;

public class PublicarFoto {

	protected JFrame publicarFoto;
	private ControladorPhotoTDS controlador;
	private String imagen;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public PublicarFoto(String imagen) {
		this.imagen = imagen;
		controlador = ControladorPhotoTDS.getUnicaInstancia();
		initialize();
	}
	
	private void initialize() {
		publicarFoto = new JFrame();
		publicarFoto.setTitle("PhotoTDS");
		publicarFoto.setResizable(false);
		publicarFoto.setIconImage(Toolkit.getDefaultToolkit().getImage(PerfilUsuario.class.getResource("/recursos/image.png")));
		publicarFoto.setBounds(100, 100, 879, 396);
		publicarFoto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		publicarFoto.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JPanel panelFoto = new JPanel();
		panelFoto.setPreferredSize(new Dimension(400, 320));
		publicarFoto.getContentPane().add(panelFoto);
		panelFoto.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lbl_Foto = new JLabel("");
		ImageIcon icon = new ImageIcon(imagen);
		Image img = icon.getImage();
		Image otraimg = img.getScaledInstance(400, 320, java.awt.Image.SCALE_DEFAULT);
		ImageIcon otroicon = new ImageIcon(otraimg);
		lbl_Foto.setIcon(otroicon);
		panelFoto.add(lbl_Foto);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setPreferredSize(new Dimension(430, 320));
		publicarFoto.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("Escribe un comentario (máximo 200 caracteres)");
		lblNewLabel.setPreferredSize(new Dimension(420, 25));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblNewLabel);
		
		JTextArea textArea = new JTextArea();
		textArea.setPreferredSize(new Dimension(420, 285));
		panel.add(textArea);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(840, 30));
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		publicarFoto.getContentPane().add(panel_1);
		
		JButton btnNewButton = new JButton("Compartir");
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String comentario = textArea.getText();
				System.out.println(comentario);
				
				ArrayList<String> hastags = new ArrayList<>();
				Pattern pattern = Pattern.compile("#\\w+");
		        Matcher matcher = pattern.matcher(comentario);
		        
		        while (matcher.find()) {
		        	hastags.add(matcher.group());
		            System.out.println("Hashtag encontrado: " + matcher.group());
		        }
		        
		        boolean isAñadida = controlador.registrarFoto(imagen, "Publicacion", comentario, controlador.getUsuarioActual(), hastags.toArray(new String[0]));
		        
		        if (isAñadida) {
		        	publicarFoto.dispose();
		        	PerfilUsuario pu = new PerfilUsuario(controlador.getUsuarioActual());
		        	pu.frmPhototds.setVisible(true);
		        }
			}
		});
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		panel_1.add(btnNewButton_1);

		
		
	}

}
