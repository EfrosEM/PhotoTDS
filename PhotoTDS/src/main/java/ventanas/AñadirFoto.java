package ventanas;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.util.List;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import java.awt.FlowLayout;

public class AñadirFoto {

	/**
	 * 
	 */
	protected JFrame frmPhototds;
	private JFrame perfil;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public AñadirFoto(JEditorPane editorPane, JFrame perfil) {
		this.perfil = perfil;
		initialize(editorPane);
	}
	
	@SuppressWarnings("serial")
	private void initialize(JEditorPane editorPane) {
		frmPhototds = new JFrame();
		frmPhototds.setTitle("PhotoTDS");
		frmPhototds.setResizable(false);
		frmPhototds.setIconImage(Toolkit.getDefaultToolkit().getImage(PerfilUsuario.class.getResource("/recursos/image.png")));
		frmPhototds.setBounds(100, 100, 300, 300);
		frmPhototds.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmPhototds.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		frmPhototds.getContentPane().add(editorPane);
		
		editorPane.setContentType("text/html");
		editorPane.setText("<h1>Agregar Foto</h1><p>Anímate a compartir una foto con tus amigos."
				+ "<br> Puedes arrastrar el fichero aquí. </p>");
		editorPane.setEditable(false);
		editorPane.setDropTarget(new DropTarget() {
			public synchronized void drop(DropTargetDropEvent evt) {
				try {
					evt.acceptDrop(DnDConstants.ACTION_COPY);
					@SuppressWarnings("unchecked")
					List<File> droppedFiles = (List<File>) evt.getTransferable()
							.getTransferData(DataFlavor.javaFileListFlavor);
					for (File file : droppedFiles) {
						String ruta = file.getPath();
						System.out.println(file.getPath());
						frmPhototds.dispose();

						PublicarFoto pb = new PublicarFoto(ruta, perfil);
						pb.publicarFoto.setVisible(true);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
	}

}
