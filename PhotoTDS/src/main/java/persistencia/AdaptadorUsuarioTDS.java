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
						new Propiedad("codigo", String.valueOf(u.getCodigo()))
				))
		);
		
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		u.setCodigo(eUsuario.getId());
	}
	
	public void borrarUsuario(Usuario u) {
		Entidad eUsuario;
		
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
		
		List<Usuario> seguidores = obtenerSeguidoresDesdeCodigo(servPersistencia.recuperarPropiedadEntidad(eUsuario, "seguidores"));
		
		for (Usuario seguidor : seguidores) {
			u.addSeguidor(seguidor);
		}
		
		List<Publicacion> publicaciones = obtenerPublicacionesDesdeCodigo(servPersistencia.recuperarPropiedadEntidad(eUsuario, "publicaciones"));
		System.out.println("Numero de publicaiones: " + publicaciones.size());
		for (Publicacion publicacion : publicaciones) {
			System.out.println("Publicacion: " + publicacion.getCodigo());
			u.addPublicacion(publicacion);
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
		AdaptadorFotoTDS adaptadorPublicacion = AdaptadorFotoTDS.getUnicaInstancia();
		
		while (strTok.hasMoreTokens()) {
			publicaciones.add(adaptadorPublicacion.recuperarFoto(Integer.valueOf((String) strTok.nextElement())));
		}
		
		return publicaciones;
	}
	
}
