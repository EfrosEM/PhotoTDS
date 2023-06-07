package persistencia;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import dominio.Album;
import dominio.Foto;
import dominio.Notificacion;
import dominio.Publicacion;
import dominio.Usuario;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorUsuarioTDS implements IAdaptadorUsuarioDAO{

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorUsuarioTDS unicaInstancia;
	private SimpleDateFormat dateFormat;
	
	public static AdaptadorUsuarioTDS getUnicaInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new AdaptadorUsuarioTDS();
		}
		
		return unicaInstancia;
	}
	
	private AdaptadorUsuarioTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	}
	
	public void registrarUsuario(Usuario u) {
		Entidad eUsuario = null;
		
		try {
			eUsuario = servPersistencia.recuperarEntidad(u.getCodigo());
		} catch (Exception e) {
		}
		
		if (eUsuario != null) return;
		
		//Aquí habría que registrar primero los atributos que son objetos
		AdaptadorUsuarioTDS adaptadorU = AdaptadorUsuarioTDS.getUnicaInstancia();
		for (Usuario user : u.getSeguidores()) {
			adaptadorU.registrarUsuario(user);
		}
		
		AdaptadorFotoTDS adaptadorF = AdaptadorFotoTDS.getUnicaInstancia();
		AdaptadorAlbumTDS adaptadorA = AdaptadorAlbumTDS.getUnicaInstancia();
		for (Publicacion publicacion : u.getPublicaciones()) {
			if (publicacion instanceof Foto) {
				adaptadorF.registrarFoto((Foto) publicacion);
			}
			
			if (publicacion instanceof Album) {
				adaptadorA.registrarAlbum((Album) publicacion);
			}
		}
		
		AdaptadorNotificacionTDS adaptadorNotificacion = AdaptadorNotificacionTDS.getUnicaInstancia();
		for (Notificacion notificacion : u.getNotificaciones()) {
			adaptadorNotificacion.registrarNotificacion(notificacion);
		}
		
		eUsuario = new Entidad();
		eUsuario.setNombre("usuario");
		eUsuario.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(
						new Propiedad("nombre", u.getNombre()),
						new Propiedad("apellidos", u.getApellidos()),
						new Propiedad("email", u.getEmail()),
						new Propiedad("usuario", u.getUsuario()),
						new Propiedad("password", u.getPassword()),
						new Propiedad("fechaNacimiento", dateFormat.format(u.getNacimiento())),
						new Propiedad("descripcion", u.getDescripcion()),
						new Propiedad("fotoPerfil", u.getFotoPerfil()),
						new Propiedad("isPremium", u.isPremium().toString()),
						new Propiedad("seguidos", String.valueOf(u.getSeguidos())),
						new Propiedad("seguidores", obtenerCodigosSeguidores(u.getSeguidores())),
						new Propiedad("publicaciones", obtenerCodigosPublicaciones(u.getPublicaciones())),
						new Propiedad("notificaciones", obtenerCodigosNotificaciones(u.getNotificaciones())),
						new Propiedad("codigo", String.valueOf(u.getCodigo()))
				))
		);
		
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		u.setCodigo(eUsuario.getId());
	}
	
	public void borrarUsuario(Usuario u) {
		Entidad eUsuario;
		
		// Borrar las fotos asociadas a dicho usuario
		AdaptadorFotoTDS adaptadorFoto = AdaptadorFotoTDS.getUnicaInstancia();
		for (Foto foto : u.getFotos()) {
			adaptadorFoto.borrarFoto(foto);
		}
		
		// Borrar los albumes asociados a dicho usuario
		AdaptadorAlbumTDS adaptadorAlbum = AdaptadorAlbumTDS.getUnicaInstancia();
		for (Album album : u.getAlbumes()) {
			adaptadorAlbum.borrarAlbum(album);
		}
		
		// Boorar las notificaciones asociadas a dicho usuario
		AdaptadorNotificacionTDS adaptadorNotificacion = AdaptadorNotificacionTDS.getUnicaInstancia();
		for (Notificacion notificacion : u.getNotificaciones()) {
			adaptadorNotificacion.borrarNotificacion(notificacion);
		}
		
		eUsuario = servPersistencia.recuperarEntidad(u.getCodigo());
		servPersistencia.borrarEntidad(eUsuario);
	}
	
	public void modificarUsuario(Usuario u) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(u.getCodigo());
		
		for (Propiedad prop : eUsuario.getPropiedades()) {
			
			if (prop.getNombre().equals("nombre")) {
				prop.setValor(u.getNombre());
				
			} else if (prop.getNombre().equals("apellidos")) {
				prop.setValor(u.getApellidos());
				
			} else if (prop.getNombre().equals("email")) {
				prop.setValor(u.getEmail());
				
			}  else if (prop.getNombre().equals("usuario")) {
				prop.setValor(u.getUsuario());
				
			}  else if (prop.getNombre().equals("password")) {
				prop.setValor(u.getPassword());
				
			}  else if (prop.getNombre().equals("fechaNacimiento")) {
				prop.setValor(dateFormat.format(u.getNacimiento()));
				
			}  else if (prop.getNombre().equals("descripcion")) {
				prop.setValor(u.getDescripcion());
				
			}  else if (prop.getNombre().equals("fotoPerfil")) {
				prop.setValor(u.getFotoPerfil());
				
			} else if (prop.getNombre().equals("isPremium")) {
				prop.setValor(u.isPremium().toString());
				
			} else if (prop.getNombre().equals("seguidos")) {
				prop.setValor(String.valueOf(u.getSeguidos()));
				
			} else if (prop.getNombre().equals("seguidores")) {
				prop.setValor(obtenerCodigosSeguidores(u.getSeguidores()));
				
			} else if (prop.getNombre().equals("publicaciones")) {
				prop.setValor(obtenerCodigosPublicaciones(u.getPublicaciones()));
				
			} else if (prop.getNombre().equals("notificaciones")) {
				prop.setValor(obtenerCodigosNotificaciones(u.getNotificaciones()));
				
			} else if (prop.getNombre().equals("codigo")) {
				prop.setValor(String.valueOf(u.getCodigo()));
				
			} 
			
			servPersistencia.modificarPropiedad(prop);
			
		}
		
	}
	
	public Usuario recuperarUsuario(int codigo) {
		if (PoolDAO.getUnicaInstancia().contiene(codigo))
			return (Usuario) PoolDAO.getUnicaInstancia().getObjeto(codigo);
		
		Entidad eUsuario;
		String nombre;
		String apellidos;
		String email;
		String usuario;
		String password;
		Date fechaNacimiento = null;
		String descripcion;
		String fotoPerfil;
		Boolean isPremium;
		int seguidos;
		List<Usuario> seguidores = null;
		List<Publicacion> publicaciones = null;
		List<Notificacion> notificaciones = null;
		
		
		eUsuario = servPersistencia.recuperarEntidad(codigo);
		
		nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, "nombre");
		apellidos = servPersistencia.recuperarPropiedadEntidad(eUsuario, "apellidos");
		email = servPersistencia.recuperarPropiedadEntidad(eUsuario, "email");
		usuario = servPersistencia.recuperarPropiedadEntidad(eUsuario, "usuario");
		password = servPersistencia.recuperarPropiedadEntidad(eUsuario, "password");
		
		try {
			fechaNacimiento = dateFormat.parse(servPersistencia.recuperarPropiedadEntidad(eUsuario, "fechaNacimiento"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		descripcion = servPersistencia.recuperarPropiedadEntidad(eUsuario, "descripcion");
		fotoPerfil = servPersistencia.recuperarPropiedadEntidad(eUsuario, "fotoPerfil");
		isPremium = Boolean.parseBoolean(servPersistencia.recuperarPropiedadEntidad(eUsuario, "isPremium"));
		seguidos = Integer.valueOf(servPersistencia.recuperarPropiedadEntidad(eUsuario, "seguidos"));
		
		Usuario u = new Usuario(nombre, usuario, apellidos, email, password, fechaNacimiento, descripcion, fotoPerfil);
		u.setCodigo(codigo);
		u.setPremium(isPremium);
		u.setSeguidos(seguidos);
		
		PoolDAO.getUnicaInstancia().addObjeto(codigo, u);
		
		seguidores = obtenerSeguidoresDesdeCodigo(servPersistencia.recuperarPropiedadEntidad(eUsuario, "seguidores"));
		for (Usuario seguidor : seguidores) {
			u.addSeguidor(seguidor);
		}
		
		publicaciones = obtenerPublicacionesDesdeCodigo(servPersistencia.recuperarPropiedadEntidad(eUsuario, "publicaciones"));
		System.out.println("Numero de publicaiones: " + publicaciones.size());
		for (Publicacion publicacion : publicaciones) {
			System.out.println("Publicacion: " + publicacion.getCodigo());
			u.addPublicacion(publicacion);
		}
		
		notificaciones = obtenerNotificacionesDesdeCodigo(servPersistencia.recuperarPropiedadEntidad(eUsuario, "notificaciones"));
		System.out.println("Numero de notificaciones: " + notificaciones.size());
		for (Notificacion notificacion : notificaciones) {
			u.addNotificacion(notificacion);
		}
		
		return u;
	}
	
	public List<Usuario> recuperarTodosUsuarios(){
		List<Usuario> usuarios = new LinkedList<Usuario>();
		List<Entidad> eUsuarios = servPersistencia.recuperarEntidades("usuario");
		
		for (Entidad eUsuario : eUsuarios) {
			usuarios.add(recuperarUsuario(eUsuario.getId()));
			System.out.println("usuario: " + recuperarUsuario(eUsuario.getId()).getUsuario() + " con codigo: " + recuperarUsuario(eUsuario.getId()).getCodigo() + " con publicaciones: " + recuperarUsuario(eUsuario.getId()).getPublicaciones().size());
		}
		
		return usuarios;
	}
	
	private String obtenerCodigosSeguidores(List<Usuario> seguidores) {
		String codigosSeguidores = "";
		
		for (Usuario seguidor : seguidores) {
			codigosSeguidores += seguidor.getCodigo() + " ";
		}
		
		return codigosSeguidores.trim();
	}
	
	private String obtenerCodigosPublicaciones(List<Publicacion> publicaciones) {
		String codigosPublicaciones = "";
		
		for (Publicacion publicacion : publicaciones) {
			codigosPublicaciones += publicacion.getCodigo() + " ";
		}
		
		return codigosPublicaciones.trim();
	}
	
	private List<Usuario> obtenerSeguidoresDesdeCodigo(String codigoSeguidores) {
		List<Usuario> seguidores = new ArrayList<>();
		
		if (codigoSeguidores == null) {
			return seguidores;
		}
		
		StringTokenizer strTok = new StringTokenizer(codigoSeguidores, " ");
		AdaptadorUsuarioTDS adaptadorUsuario = AdaptadorUsuarioTDS.getUnicaInstancia();
		
		while (strTok.hasMoreTokens()) {
			seguidores.add(adaptadorUsuario.recuperarUsuario(Integer.valueOf((String) strTok.nextElement())));
		}
		
		return seguidores;
	}
	
	private List<Publicacion> obtenerPublicacionesDesdeCodigo(String codigoPublicaciones) {
		List<Publicacion> publicaciones = new ArrayList<>();
		
		if (codigoPublicaciones == null || codigoPublicaciones.equals("")) {
			return publicaciones;
		}
		
		StringTokenizer strTok = new StringTokenizer(codigoPublicaciones, " ");
		AdaptadorFotoTDS adaptadorFoto = AdaptadorFotoTDS.getUnicaInstancia();
		AdaptadorAlbumTDS adaptadorAlbum = AdaptadorAlbumTDS.getUnicaInstancia();
		
		while (strTok.hasMoreTokens()) {
			String token = (String) strTok.nextElement();
			try {
				publicaciones.add(adaptadorFoto.recuperarFoto(Integer.valueOf(token)));
			} catch (NumberFormatException e) {
				publicaciones.add(adaptadorAlbum.recuperarAlbum(Integer.valueOf(token)));
			}
			
		}
		
		return publicaciones;
	}
	
	private String obtenerCodigosNotificaciones(List<Notificacion> notificaciones) {
		String codigosNotificaciones = "";
		
		for (Notificacion notificacion : notificaciones) {
			codigosNotificaciones += notificacion.getCodigo() + " ";
		}
		
		return codigosNotificaciones.trim();
	}
	
	private List<Notificacion> obtenerNotificacionesDesdeCodigo(String codigoNotificaciones) {
		List<Notificacion> notificaciones = new ArrayList<>();
		
		if (codigoNotificaciones == null || codigoNotificaciones.equals("")) {
			return notificaciones;
		}
		
		StringTokenizer strTok = new StringTokenizer(codigoNotificaciones, " ");
		AdaptadorNotificacionTDS adaptadorNotificacion = AdaptadorNotificacionTDS.getUnicaInstancia();
		
		while (strTok.hasMoreTokens()) {
			notificaciones.add(adaptadorNotificacion.recuperarNotificacion(Integer.valueOf((String) strTok.nextElement())));
		}
		
		return notificaciones;
	}
	
}
