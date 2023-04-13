package dominio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import persistencia.DAOException;
import persistencia.FactoriaDAO;
import persistencia.IAdaptadorFotoDAO;

public class CatalogoFotos {

	private Map<String, Foto> fotos;
	private static CatalogoFotos unicaInstancia;
	private FactoriaDAO dao;
	private IAdaptadorFotoDAO adaptadorF;
	
	public static CatalogoFotos getUnicaInstancia() {
		if (unicaInstancia == null) unicaInstancia = new CatalogoFotos();
		return unicaInstancia;
	}
	
	private CatalogoFotos() {
		try {
			dao = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
			adaptadorF = dao.getFotoDAO();
			fotos = new HashMap<String, Foto>();
			cargarCatalogo();
		} catch (DAOException ed) {
			ed.printStackTrace();
		}
		
	}
	
	public void addFoto(Foto f) {
		fotos.put(f.getRuta(), f);
	}
	
	private void cargarCatalogo() throws DAOException {
		List<Foto> fotosBD = adaptadorF.recuperarTodasFotos();
		
		for (Foto f: fotosBD) {
			this.fotos.put(f.getRuta(), f);
		}
	}
}
