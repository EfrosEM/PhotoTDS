package dominio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import persistencia.AdaptadorUsuarioTDS;
import persistencia.DAOException;
import persistencia.FactoriaDAO;
import persistencia.IAdaptadorUsuarioDAO;

public class CatalogoUsuarios {

	private Map<String, Usuario> usuarios;
	private static CatalogoUsuarios unicaInstancia = null;
	private FactoriaDAO dao;
	private IAdaptadorUsuarioDAO adaptadorU;
	
	
	public static CatalogoUsuarios getUnicaInstancia() {
		if (unicaInstancia == null) unicaInstancia = new CatalogoUsuarios();
		return unicaInstancia;
	}
	
	private CatalogoUsuarios() {
		try {
			dao = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
			adaptadorU = dao.getUsuarioDAO();
			usuarios = new HashMap<String, Usuario>();
			cargarCatalogo();
		} catch (DAOException ed) {
			ed.printStackTrace();
		}
		
	}
	
	public Usuario getUsuario(String username) {
		return usuarios.get(username);
	}
	
	public void addUsuario(Usuario u) {
		usuarios.put(u.getUsuario(), u);
	}
	
	private void cargarCatalogo() throws DAOException {
		List<Usuario> usuariosBD = adaptadorU.recuperarTodosUsuarios();
		
		for (Usuario u: usuariosBD) {
			this.usuarios.put(u.getUsuario(), u);
		}
	}
}
