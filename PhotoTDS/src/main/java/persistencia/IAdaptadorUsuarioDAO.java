package persistencia;

import java.util.List;

import dominio.Usuario;

public interface IAdaptadorUsuarioDAO {

	public void registrarUsuario(Usuario u);
	public List<Usuario> recuperarTodosUsuarios();
}
