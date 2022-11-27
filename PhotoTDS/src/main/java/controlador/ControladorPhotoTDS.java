package controlador;

import java.util.Date;

import dominio.CatalogoUsuarios;
import dominio.Usuario;
import persistencia.FactoriaDAO;
import persistencia.IAdaptadorUsuarioDAO;

public class ControladorPhotoTDS {

	private CatalogoUsuarios catalogoUsuarios;
	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private static ControladorPhotoTDS unicaInstancia;
	
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
	
	public boolean registrarUsuario(String nombre, String apellidos, String email, String usuario, String password, Date fecha, String descripcion) {
		if (catalogoUsuarios.getUsuario(usuario) != null) {
			return false;
		}
		
		Usuario u = new Usuario(nombre, usuario, apellidos, email, password, fecha, descripcion, null);
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
	
	public boolean existeUsuario(String usuario) {
		Usuario u = catalogoUsuarios.getUsuario(usuario);
		
		if (u == null) {
			return false;
		}
		
		return true;
	}
	
	private void inicializarAdaptadores() {
		FactoriaDAO factoria = null;
		try {
			factoria = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		adaptadorUsuario = factoria.getUsuarioDAO();
	}
	
	private void inicializarCatalogos() {
		catalogoUsuarios = CatalogoUsuarios.getUnicaInstancia();
	}
}
