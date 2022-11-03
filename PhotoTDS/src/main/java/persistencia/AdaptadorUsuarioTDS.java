package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import dominio.Usuario;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorUsuarioTDS implements IAdaptadorUsuarioDAO{

	private static ServicioPersistencia servidorPers;
	private static AdaptadorUsuarioTDS unicaInstancia;
	
	public static AdaptadorUsuarioTDS getUnicaInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new AdaptadorUsuarioTDS();
		}
		
		return unicaInstancia;
	}
	
	private AdaptadorUsuarioTDS() {
		servidorPers = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	public void registrarUsuario(Usuario u) {
		Entidad eUsuario = null;
		
		System.out.println("Entra al adaptadorDAO");
		try {
			eUsuario = servidorPers.recuperarEntidad(u.getCodigo());
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
						new Propiedad("password", u.getPassword())
				))
		);
		
		eUsuario = servidorPers.registrarEntidad(eUsuario);
		u.setCodigo(eUsuario.getId());
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
		
		eUsuario = servidorPers.recuperarEntidad(codigo);
		
		nombre = servidorPers.recuperarPropiedadEntidad(eUsuario, "nombre");
		apellidos = servidorPers.recuperarPropiedadEntidad(eUsuario, "apellidos");
		email = servidorPers.recuperarPropiedadEntidad(eUsuario, "email");
		usuario = servidorPers.recuperarPropiedadEntidad(eUsuario, "usuario");
		password = servidorPers.recuperarPropiedadEntidad(eUsuario, "password");
		
		Usuario u = new Usuario(nombre, usuario, apellidos, email, password, null,null, null);
		u.setCodigo(codigo);
		
		PoolDAO.getUnicaInstancia().addObjeto(codigo, u);
		
		return u;
	}
	
	public List<Usuario> recuperarTodosUsuarios(){
		List<Usuario> usuarios = new LinkedList<Usuario>();
		List<Entidad> eUsuarios = servidorPers.recuperarEntidades("usuario");
		
		for (Entidad eUsuario : eUsuarios) {
			usuarios.add(recuperarUsuario(eUsuario.getId()));
		}
		
		return usuarios;
	}
}
