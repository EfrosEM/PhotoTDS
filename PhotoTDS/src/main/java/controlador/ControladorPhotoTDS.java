package controlador;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import dominio.CatalogoFotos;
import dominio.CatalogoUsuarios;
import dominio.Foto;
import dominio.Notificacion;
import dominio.Usuario;
import persistencia.FactoriaDAO;
import persistencia.IAdaptadorFotoDAO;
import persistencia.IAdaptadorNotificacionDAO;
import persistencia.IAdaptadorUsuarioDAO;

public class ControladorPhotoTDS {

	private CatalogoUsuarios catalogoUsuarios;
	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private static ControladorPhotoTDS unicaInstancia;
	private Usuario usuarioActual;
	private CatalogoFotos catalogoFotos;
	private IAdaptadorFotoDAO adaptadorFoto;
	private IAdaptadorNotificacionDAO adaptadorNotificacion;
	
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
	
	public void modificarUsuario(String nombre, String apellidos, String email, String usuario, String password, Date fecha, String descripcion, String fotoPerfil) {
		Usuario u = getUsuarioActual();
		catalogoUsuarios.removeUsuario(u.getUsuario());
		
		u.setNombre(nombre);
		u.setApellidos(apellidos);
		u.setEmail(email);
		u.setUsuario(usuario);
		u.setPassword(password);
		u.setNacimiento(fecha);
		u.setDescripcion(descripcion);
		u.setFotoPerfil(fotoPerfil);
		
		adaptadorUsuario.modificarUsuario(u);
		catalogoUsuarios.addUsuario(u);
	}
	
	public void modificarUsuario(Usuario user) {
		catalogoUsuarios.removeUsuario(user.getUsuario());
		adaptadorUsuario.modificarUsuario(user);
		catalogoUsuarios.addUsuario(user);
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
	
	public boolean registrarFoto(String ruta, String descripcion, LocalDate fechaSubida, Usuario user, String...hashtags) {
		Foto foto = usuarioActual.registrarFoto(ruta, descripcion, fechaSubida, user, hashtags);
		adaptadorFoto.registrarFoto(foto);
		catalogoFotos.addFoto(foto);
		adaptadorUsuario.modificarUsuario(user);
		
		List<Usuario> seguidores = usuarioActual.getSeguidores();
		Notificacion notificacion = new Notificacion(user, foto);
		adaptadorNotificacion.registrarNotificacion(notificacion);
		for (Usuario seguidor : seguidores) {
			seguidor.addNotificacion(notificacion);
			adaptadorUsuario.modificarUsuario(seguidor);
			System.out.println("Usuario: " + seguidor.getUsuario() + "actualizado con las notificaciones:");
			for (Notificacion n : seguidor.getNotificaciones()) {
				System.out.println(n.getCodigo());
			}
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
		adaptadorFoto = factoria.getFotoDAO();
		adaptadorNotificacion = factoria.getNotificacionDAO();
	}
	
	private void inicializarCatalogos() {
		catalogoUsuarios = CatalogoUsuarios.getUnicaInstancia();
		catalogoFotos = CatalogoFotos.getUnicaInstancia();
	}

	public Usuario getUsuarioActual() {
		return adaptadorUsuario.recuperarUsuario(usuarioActual.getCodigo());
	}

}
