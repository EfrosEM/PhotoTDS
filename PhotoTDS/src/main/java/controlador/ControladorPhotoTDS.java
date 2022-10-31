package controlador;

import dominio.CatalogoUsuarios;
import dominio.Usuario;
import persistencia.AdaptadorUsuarioTDS;
import persistencia.FactoriaDAO;
import persistencia.IAdaptadorUsuarioDAO;

public class ControladorPhotoTDS {

	private CatalogoUsuarios catalogoUsuarios;
	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private static ControladorPhotoTDS unicaInstancia = null;
	
	public static ControladorPhotoTDS getUnicaInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new ControladorPhotoTDS();
		}
		
		return unicaInstancia;
	}
	
	private ControladorPhotoTDS() {
		inicializarAdaptadores();
		inicializarCatalogos();
	}
	
	public boolean registrarUsuario(String nombre, String apellidos, String email, String usuario, String password) {
		if (catalogoUsuarios.getUsuario(usuario) != null) {
			return false;
		}
		
		Usuario u = new Usuario(nombre, usuario, apellidos, email, password, null, null, null);
		adaptadorUsuario.registrarUsuario(u);
		catalogoUsuarios.addUsuario(u);
		return true;
	}
	
	public boolean loginUsuario(String usuario, String password) {
		Usuario u = catalogoUsuarios.getUsuario(usuario);
		
		if (u != null && u.getPassword().equals(password)) {
			return true;
		}
		
		return false;
	}
	
	private void inicializarAdaptadores() {
		FactoriaDAO factoria = null;
		try {
			factoria = FactoriaDAO.getInstancia();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		adaptadorUsuario = factoria.getUsuarioDAO();
	}
	
	private void inicializarCatalogos() {
		catalogoUsuarios = CatalogoUsuarios.getUnicaInstancia();
	}
}
