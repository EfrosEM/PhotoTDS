package persistencia;

import java.util.List;

import dominio.Usuario;

public interface IAdaptadorUsuarioDAO {

	public void registrarUsuario(Usuario u);
	public void borrarUsuario(Usuario u);
	public Usuario recuperarUsuario(int codigo);
	public List<Usuario> recuperarTodosUsuarios();
}
