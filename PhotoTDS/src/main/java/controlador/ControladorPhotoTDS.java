package controlador;

import java.util.Date;

import dominio.CatalogoFotos;
import dominio.CatalogoUsuarios;
import dominio.Foto;
import dominio.Usuario;
import persistencia.FactoriaDAO;
import persistencia.IAdaptadorFotoDAO;
import persistencia.IAdaptadorUsuarioDAO;

public class ControladorPhotoTDS {

	private CatalogoUsuarios catalogoUsuarios;
	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private static ControladorPhotoTDS unicaInstancia;
	private Usuario usuarioActual;
	private CatalogoFotos catalogoFotos;
	private IAdaptadorFotoDAO adaptadorFoto;
	
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
	
	public boolean registrarUsuario(String nombre, String apellidos, String email, String usuario, String password, Date fecha, String descripcion, String fotoPerfil) {
		if (catalogoUsuarios.getUsuario(usuario) != null) {
			return false;
		}
		
		Usuario u = new Usuario(nombre, usuario, apellidos, email, password, fecha, descripcion, fotoPerfil);
		adaptadorUsuario.registrarUsuario(u);
		catalogoUsuarios.addUsuario(u);
		return true;
	}
	
	public boolean loginUsuario(String usuario, String password) {
		Usuario u = catalogoUsuarios.getUsuario(usuario);
		
		if (u != null && u.getPassword().equals(password)) {
			this.usuarioActual = u;
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
	
	public boolean registrarFoto(String ruta, String titulo, String descripcion, Usuario user, String...hashtags) {
		
		Foto f = new Foto(ruta, titulo, descripcion, user, hashtags);
		adaptadorFoto.registrarFoto(f);
		catalogoFotos.addFoto(f);
		añadirFotoAUsuario(f);
		System.out.println("Foto registrada: " + f.getCodigo());
		return true;
	}
	
	public void añadirFotoAUsuario(Foto f) {
		System.out.println("Codigo de usuario actual: " + usuarioActual.getCodigo());
		Usuario user = adaptadorUsuario.recuperarUsuario(usuarioActual.getCodigo());
		System.out.println("Usuario: " + user.getUsuario() + " tiene " + user.getPublicaciones().size() + " publicaciones");
		user.addPublicacion(f);
		System.out.println("Usuario: " + user.getUsuario() + " tiene " + user.getPublicaciones().size() + " publicaciones");
		adaptadorUsuario.modificarUsuario(user);
		Usuario u = adaptadorUsuario.recuperarUsuario(usuarioActual.getCodigo());
		System.out.println("Funciona?: " + u.getPublicaciones().size());
	}
	
	private void inicializarAdaptadores() {
		FactoriaDAO factoria = null;
		try {
			factoria = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		adaptadorUsuario = factoria.getUsuarioDAO();
		adaptadorFoto = factoria.getFotoDAO();
	}
	
	private void inicializarCatalogos() {
		catalogoUsuarios = CatalogoUsuarios.getUnicaInstancia();
		catalogoFotos = CatalogoFotos.getUnicaInstancia();
	}

	public Usuario getUsuarioActual() {
		return adaptadorUsuario.recuperarUsuario(usuarioActual.getCodigo());
	}

}
