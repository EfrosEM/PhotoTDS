package lanzador;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.Date;

import javax.swing.JFrame;

import controlador.ControladorPhotoTDS;
import ventanas.Inicio;
import ventanas.Login;
import ventanas.Registro;

public class AppLauncher extends JFrame{

	private static final long serialVersionUID = 1L;
	private static AppLauncher unicaInstancia = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					// Registramos al usuario administrador en el sistema
					ControladorPhotoTDS.getUnicaInstancia().registrarUsuario("admin", null, "admin@photods.es", "admin", "admin", new Date(), null);
					AppLauncher frame = AppLauncher.getUnicaInstancia();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// CONSTRUCTOR:
	private AppLauncher() {

		setTitle("AppVideo - PNG");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 800);
		setMinimumSize(new Dimension(1100, 800));

		// Abrimos AppVideo mostrando el login
		showPanelLogin();
	}

	// METODOS:
	public static AppLauncher getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new AppLauncher();
		return unicaInstancia;
	}

	public void showPanelLogin() {
		setContentPane(new Login());
		revalidate();
		repaint();
	}

	public void showPanelRegistro() {
		setContentPane(new Registro(null));
		revalidate();
		repaint();
	}

	public void showPanelInicio() {
		setContentPane(new Inicio(null));
		revalidate();
		repaint();
	}
}
