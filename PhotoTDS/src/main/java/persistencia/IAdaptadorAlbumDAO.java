package persistencia;

import java.util.List;

import dominio.Album;

public interface IAdaptadorAlbumDAO {

	public void registrarAlbum(Album a);
	public void borrarAlbum(Album a);
	public void modificarAlbum(Album a);
	public Album recuperarAlbum(int codigo);
	public List<Album> recuperarTodosAlbumes();
}
