package persistencia;

import java.util.List;

import dominio.Foto;

public interface IAdaptadorFotoDAO {

	public void registrarFoto(Foto f);
	public void borrarFoto(Foto f);
	public void modificarFoto(Foto f);
	public Foto recuperarFoto(int codigo);
	public List<Foto> recuperarTodasFotos();
}
