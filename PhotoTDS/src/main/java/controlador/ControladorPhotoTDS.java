package controlador;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventObject;
import java.util.List;
import java.util.Optional;

import dominio.Album;
import dominio.CatalogoPublicaciones;
import dominio.CatalogoUsuarios;
import dominio.Comentario;
import dominio.Foto;
import dominio.Notificacion;
import dominio.Publicacion;
import dominio.Usuario;
import dominio.Descuento;
import dominio.DescuentoEdad;
import dominio.DescuentoPopularidad;
import persistencia.FactoriaDAO;
import persistencia.IAdaptadorAlbumDAO;
import persistencia.IAdaptadorComentarioDAO;
import persistencia.IAdaptadorFotoDAO;
import persistencia.IAdaptadorNotificacionDAO;
import persistencia.IAdaptadorUsuarioDAO;
import umu.tds.fotos.CargadorFotos;
import umu.tds.fotos.FotosEvent;
import umu.tds.fotos.FotosListener;

import utils.GeneradorArchivos;

public class ControladorPhotoTDS implements FotosListener{

	private CatalogoUsuarios catalogoUsuarios;
	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private static ControladorPhotoTDS unicaInstancia;
	private Usuario usuarioActual;
	private CatalogoPublicaciones catalogoPublicaciones;
	private IAdaptadorFotoDAO adaptadorFoto;
	private IAdaptadorNotificacionDAO adaptadorNotificacion;
	private IAdaptadorAlbumDAO adaptadorAlbum;
	private IAdaptadorComentarioDAO adaptadorComentario;
	private CargadorFotos cargadorFotos;

	public static ControladorPhotoTDS getUnicaInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new ControladorPhotoTDS();
		}

		return unicaInstancia;
	}

	private ControladorPhotoTDS() {
		inicializarAdaptadores();
		inicializarCatalogos();
		
		cargadorFotos = new CargadorFotos();
		cargadorFotos.addFotosListener(this);
	}
	
	// Metodos de Usuario
	public boolean registrarUsuario(String nombre, String apellidos, String email, String usuario, String password,
			Date fecha, String descripcion, String fotoPerfil) {
		if (catalogoUsuarios.getUsuario(usuario) != null) {
			return false;
		}

		Usuario u = new Usuario(nombre, usuario, apellidos, email, password, fecha, descripcion, fotoPerfil);
		adaptadorUsuario.registrarUsuario(u);
		catalogoUsuarios.addUsuario(u);
		return true;
	}

	public void modificarUsuario(String nombre, String apellidos, String email, String usuario, String password,
			Date fecha, String descripcion, String fotoPerfil) {
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
	
	public Usuario getUsuarioActual() {
		return usuarioActual;
	}
	
	public double calcularDescuento(Usuario usuario, double precio, String descuento) {
		double precioDescuento = 0.0;
		if (descuento.equals("Descuento por edad")) {
			Descuento desc = new DescuentoEdad();
			usuario.setDescuento(desc);
			precioDescuento = usuario.calcularDescuento(precio);
		} else if (descuento.equals("Descuento Popularidad")) {
			Descuento desc = new DescuentoPopularidad();
			usuario.setDescuento(desc);
			precioDescuento = usuario.calcularDescuento(precio);
		}

		return precioDescuento;
	}

	public void makeUserPremium(Usuario usuario) {
		usuario.makePremium();
		modificarUsuario(usuario);
	}

	public void cancelSuscription(Usuario usuario) {
		usuario.cancelPremium();
		modificarUsuario(usuario);
	}
	
	public void generarExcel() {
		try {
			GeneradorArchivos.generarExcel(usuarioActual);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void generarPDF() {
		try {
			GeneradorArchivos.generarPDF(usuarioActual);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Usuario> recuperarTodosUsuarios() {
		return adaptadorUsuario.recuperarTodosUsuarios();
	}
	
	public void addSeguidor(Usuario usuario) {
		usuario.addSeguidor(getUsuarioActual());
		getUsuarioActual().addSeguidos();
		modificarUsuario(usuario);
		modificarUsuario(getUsuarioActual());
	}

	public void removeSeguidor(Usuario usuario) {
		usuario.removeSeguidor(getUsuarioActual());
		getUsuarioActual().removeSeguidos();
		modificarUsuario(usuario);
		modificarUsuario(getUsuarioActual());
	}


	// Metodos de Foto
	public Foto registrarFoto(String ruta, String descripcion, LocalDate fechaSubida, Usuario user,
			String... hashtags) {
		Foto foto = usuarioActual.registrarFoto(ruta, descripcion, fechaSubida, user, hashtags);
		adaptadorFoto.registrarFoto(foto);
		catalogoPublicaciones.addPublicacion(foto);
		adaptadorUsuario.modificarUsuario(user);

		List<Usuario> seguidores = usuarioActual.getSeguidores();
		Notificacion notificacion = new Notificacion(user, foto);
		adaptadorNotificacion.registrarNotificacion(notificacion);
		user.addNotificacion(notificacion);
		adaptadorUsuario.modificarUsuario(user);
		for (Usuario seguidor : seguidores) {
			seguidor.addNotificacion(notificacion);
			adaptadorUsuario.modificarUsuario(seguidor);
		}
		return foto;
	}
	
	public void eliminarFoto(Foto foto) {
		catalogoPublicaciones.removePublicacion(foto.getRuta() + foto.getCodigo());
		usuarioActual.deletePublicacion(foto);
		for (Album a : usuarioActual.getAlbumes()) {
			a.eliminarFoto(foto);
			if (a.getFotos().size() == 0) {
				eliminarAlbum(a);
			}
		}
		
		Optional<Notificacion> not = usuarioActual.deleteNotificacion(foto);
		if (not.isPresent()) {
			adaptadorNotificacion.borrarNotificacion(not.get());
			modificarUsuario(usuarioActual);
		}
		
		for (Usuario u : usuarioActual.getSeguidores()) {
			Optional<Notificacion> n = u.deleteNotificacion(foto);
			if (n.isPresent()) {
				adaptadorNotificacion.borrarNotificacion(n.get());
				modificarUsuario(u);
			}
		}
		adaptadorFoto.borrarFoto(foto);
		modificarUsuario(usuarioActual);
	}
	
	public void addComentario(Foto foto, Comentario comentario) {

		foto.addComentario(comentario);
		adaptadorComentario.registrarComentario(comentario);
		adaptadorFoto.modificarFoto(foto);

	}
	
	public Foto getFoto(String ruta, int codigo) {
		return (Foto) catalogoPublicaciones.getPublicacion(ruta + codigo);
	}
	
	public void addLike(Foto foto) {
		foto.addLike();
		adaptadorFoto.modificarFoto(foto);
	}
	
	public void removeLike(Foto foto) {
		foto.removeLike();
		adaptadorFoto.modificarFoto(foto);
	}
	
	public List<Foto> recuperarTodasFotos() {	
		return adaptadorFoto.recuperarTodasFotos();
	}

	
	//Metodos de Album
	public boolean registrarAlbum(String titulo, Foto foto, Usuario user, LocalDate fecha, String... hashtags) {
		Album album = usuarioActual.registrarAlbum(titulo, foto, user, fecha, hashtags);
		catalogoPublicaciones.addPublicacion(album);
		adaptadorAlbum.registrarAlbum(album);
		adaptadorUsuario.modificarUsuario(user);

		return true;
	}

	public Album getAlbum(Usuario user, String nombreAlbum) {
		for (Publicacion p : user.getPublicaciones()) {
			if (p instanceof Album) {
				if (p.getTitulo().equals(nombreAlbum)) {
					return (Album) p;
				}
			}
		}

		return null;
	}
	
	public void addPhotoToAlbum(Foto foto, Album album) {
		album.addFoto(foto);
		adaptadorAlbum.modificarAlbum(album);
	}
	
	public void eliminarAlbum(Album album) {
		for (Foto f : album.getFotos()) {
			usuarioActual.deletePublicacion(f);
			for (Usuario u : usuarioActual.getSeguidores()) {
				Optional<Notificacion> n = u.deleteNotificacion(f);
				if (n.isPresent()) {
					adaptadorNotificacion.borrarNotificacion(n.get());
					modificarUsuario(u);
				}
			}
		}
		usuarioActual.deletePublicacion(album);
		adaptadorAlbum.borrarAlbum(album);
		modificarUsuario(usuarioActual);
	}
	
	public void addLikeAlbum(Album album) {
		for (Foto foto : album.getFotos()) {
			foto.addLike();
			adaptadorFoto.modificarFoto(foto);
		}
	}

	public void removeLikeAlbum(Album album) {
		for (Foto foto : album.getFotos()) {
			foto.removeLike();
			adaptadorFoto.modificarFoto(foto);
		}
	}


	// Metodos inicializadores
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
		adaptadorAlbum = factoria.getAlbumDAO();
		adaptadorComentario = factoria.getComentarioDAO();
	}

	private void inicializarCatalogos() {
		catalogoUsuarios = CatalogoUsuarios.getUnicaInstancia();
		catalogoPublicaciones = CatalogoPublicaciones.getUnicaInstancia();
	}
	
	//Metodos JavaBean
	public void cargarFotos(File fileXML) {
		cargadorFotos.cargarFotos(fileXML);
	}
	
	@Override
	public void enteradoNuevasFotos(EventObject arg0) {
		if (arg0 instanceof FotosEvent) {
			for (umu.tds.fotos.Foto foto: cargadorFotos.getFotos().getFoto()) {
				if (getFoto(foto.getTitulo(), 0) == null) {
					ArrayList<String> hashtagsFoto = new ArrayList<>();
					for (umu.tds.fotos.HashTag hashtags : foto.getHashTags()) {
						for (String hashtag : hashtags.getHashTag()) {
							hashtagsFoto.add(hashtag);
						}
					}
					this.registrarFoto(foto.getPath(), foto.getDescripcion(), LocalDate.now(), usuarioActual, hashtagsFoto.toArray(new String[foto.getHashTags().size()]));
				}
				
			}
		}
	}
}
