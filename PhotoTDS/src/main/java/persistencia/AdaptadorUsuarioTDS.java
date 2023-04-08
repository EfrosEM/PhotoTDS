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
import dominio.Foto;
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
						new Propiedad("isPremium", u.isPremium().toString()),
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
		
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "nombre");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "nombre", u.getNombre());
		
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "apellidos");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "apellidos", u.getApellidos());
		
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "email");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "email", u.getEmail());
		
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "usuario");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "usuario", u.getUsuario());
		
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "password");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "password", u.getPassword());
		
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "fechaNacimiento");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "fechaNacimiento", dateFormat.format(u.getNacimiento()));
		
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "descripcion");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "descripcion", u.getDescripcion());
		
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "isPremium");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "isPremium", u.isPremium().toString());
		
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "seguidores");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "seguidores", obtenerCodigosSeguidores(u.getSeguidores()));
		
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "publicaciones");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "publicaciones", obtenerCodigosPublicaciones(u.getPublicaciones()));
		
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "codigo");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "codigo", String.valueOf(u.getCodigo()));
		
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
		Boolean isPremium;
		
		
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
		isPremium = Boolean.parseBoolean(servPersistencia.recuperarPropiedadEntidad(eUsuario, "isPremium"));
		
		Usuario u = new Usuario(nombre, usuario, apellidos, email, password, fechaNacimiento, descripcion, null);
		u.setCodigo(codigo);
		u.setPremium(isPremium);
		
		PoolDAO.getUnicaInstancia().addObjeto(codigo, u);
		
		List<Usuario> seguidores = obtenerSeguidoresDesdeCodigo(servPersistencia.recuperarPropiedadEntidad(eUsuario, "seguidores"));
		
		for (Usuario seguidor : seguidores) {
			u.addSeguidor(seguidor);
		}
		
		// Falta la parte de las publicaciones
		
		return u;
	}
	
	public List<Usuario> recuperarTodosUsuarios(){
		List<Usuario> usuarios = new LinkedList<Usuario>();
		List<Entidad> eUsuarios = servPersistencia.recuperarEntidades("usuario");
		
		for (Entidad eUsuario : eUsuarios) {
			usuarios.add(recuperarUsuario(eUsuario.getId()));
		}
		
		return usuarios;
	}
	
	private String obtenerCodigosSeguidores(List<Usuario> seguidores) {
		String codigosSeguidores = "";
		
		for (Usuario seguidor : seguidores) {
			codigosSeguidores += seguidor.getCodigo() + " ";
		}
		
		return codigosSeguidores;
	}
	
	private String obtenerCodigosPublicaciones(List<Publicacion> publicaciones) {
		String codigosPublicaciones = "";
		
		for (Publicacion publicacion : publicaciones) {
			codigosPublicaciones += publicacion.getCodigo() + " ";
		}
		
		return codigosPublicaciones;
	}
	
	private List<Usuario> obtenerSeguidoresDesdeCodigo(String codigoSeguidores) {
		List<Usuario> seguidores = new ArrayList<>();
		
		if (codigoSeguidores.equals("") || codigoSeguidores == null) {
			return seguidores;
		}
		
		StringTokenizer strTok = new StringTokenizer(codigoSeguidores, " ");
		AdaptadorUsuarioTDS adaptadorUsuario = AdaptadorUsuarioTDS.getUnicaInstancia();
		
		while (strTok.hasMoreTokens()) {
			seguidores.add(adaptadorUsuario.recuperarUsuario(Integer.valueOf(strTok.nextToken(codigoSeguidores))));
		}
		
		return seguidores;
	}
	
}
