package lanzador;

import controlador.ControladorPhotoTDS;
import ventanas.Login;


public class PhotoTDSLauncher {
	
	public static void main(String[] args) {
		
		ControladorPhotoTDS.getUnicaInstancia();
		Login login = new Login();
		login.setVisible(true);
	}

}
