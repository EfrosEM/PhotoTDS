package dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import persistencia.DAOException;
import persistencia.FactoriaDAO;
import persistencia.IAdaptadorAlbumDAO;
import persistencia.IAdaptadorFotoDAO;

public class CatalogoPublicaciones {

	private Map<String, Publicacion> publicaciones;
	private static CatalogoPublicaciones unicaInstancia;
	private FactoriaDAO dao;
	private IAdaptadorFotoDAO adaptadorF;
	private IAdaptadorAlbumDAO adaptadorA;
	
	public static CatalogoPublicaciones getUnicaInstancia() {
		if (unicaInstancia == null) unicaInstancia = new CatalogoPublicaciones();
		return unicaInstancia;
	}
	
	private CatalogoPublicaciones() {
		try {
			dao = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
			adaptadorF = dao.getFotoDAO();
			adaptadorA = dao.getAlbumDAO();
			publicaciones = new HashMap<String, Publicacion>();
			cargarCatalogo();
		} catch (DAOException ed) {
			ed.printStackTrace();
		}
		
	}
	
	public void addPublicacion(Publicacion p) {
		if (p instanceof Album) {
			publicaciones.put(p.getTitulo(), p);
		} else if (p instanceof Foto) {
			publicaciones.put(((Foto) p).getRuta() + p.getCodigo(), p);
		}
	}
	
	public List<Publicacion> getFotos(){
		return new ArrayList<Publicacion>(publicaciones.values());
	}
	
	public Publicacion getPublicacion(String key) {
		return publicaciones.get(key);
	}
	
	public void removePublicacion(String key) {
		publicaciones.remove(key);
	}
	
	private void cargarCatalogo() throws DAOException {
		List<Foto> fotosBD = adaptadorF.recuperarTodasFotos();
		
		for (Foto f: fotosBD) {
			this.publicaciones.put(f.getRuta() + f.getCodigo(), f);
		}
		
		List<Album> albumesBD = adaptadorA.recuperarTodosAlbumes();
		
		for (Album a: albumesBD) {
			this.publicaciones.put(a.getTitulo(), a);
		}
	}
}
