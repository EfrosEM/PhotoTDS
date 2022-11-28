package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import dominio.Foto;
import dominio.Usuario;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorFotoTDS implements IAdaptadorFotoDAO{

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorFotoTDS unicaInstancia;
	
	public static AdaptadorFotoTDS getUnicaInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new AdaptadorFotoTDS();
		}
		
		return unicaInstancia;
	}
	
	private AdaptadorFotoTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	public void registrarFoto(Foto f) {
		Entidad eFoto = null;
		
		try {
			eFoto = servPersistencia.recuperarEntidad(f.getCodigo());
		} catch (Exception e) {
		}
		
		if (eFoto != null) return;
		
		//Aquí habría que registrar primero los atributos que son objetos
		AdaptadorUsuarioTDS adaptadorU = AdaptadorUsuarioTDS.getUnicaInstancia();
		adaptadorU.registrarUsuario(f.getUser());
		
		eFoto = new Entidad();
		eFoto.setNombre("foto");
		eFoto.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(
						new Propiedad("ruta", f.getRuta()),
						new Propiedad("titulo", f.getTitulo()),
						new Propiedad("descripcion", f.getDescripcion())
				))
		);
		
		eFoto = servPersistencia.registrarEntidad(eFoto);
		f.setCodigo(eFoto.getId());
	}
	
	public void borrarFoto(Foto f) {
		Entidad eFoto;
		
		eFoto = servPersistencia.recuperarEntidad(f.getCodigo());
		servPersistencia.borrarEntidad(eFoto);
	}
	
	public void modificarFoto(Foto f) {
		Entidad eFoto = servPersistencia.recuperarEntidad(f.getCodigo());
		
		servPersistencia.eliminarPropiedadEntidad(eFoto, "usuario");
		servPersistencia.anadirPropiedadEntidad(eFoto, "usuario", String.valueOf(f.getUser().getCodigo()));
		
		servPersistencia.eliminarPropiedadEntidad(eFoto, "ruta");
		servPersistencia.anadirPropiedadEntidad(eFoto, "ruta", f.getRuta());
		
		servPersistencia.eliminarPropiedadEntidad(eFoto, "titulo");
		servPersistencia.anadirPropiedadEntidad(eFoto, "titulo", f.getTitulo());
		
		servPersistencia.eliminarPropiedadEntidad(eFoto, "descripcion");
		servPersistencia.anadirPropiedadEntidad(eFoto, "descripcion", f.getDescripcion());
	}
	
	public Foto recuperarFoto(int codigo) {
		if (PoolDAO.getUnicaInstancia().contiene(codigo))
			return (Foto) PoolDAO.getUnicaInstancia().getObjeto(codigo);
		
		Entidad eFoto = new Entidad();
		String ruta;
		String titulo;
		String descripcion;
		
		eFoto = servPersistencia.recuperarEntidad(codigo);
		
		ruta = servPersistencia.recuperarPropiedadEntidad(eFoto, "ruta");
		titulo = servPersistencia.recuperarPropiedadEntidad(eFoto, "titulo");
		descripcion = servPersistencia.recuperarPropiedadEntidad(eFoto, "descripcion");
				
		Foto f = new Foto(ruta, titulo, descripcion);
		f.setCodigo(codigo);
		
		PoolDAO.getUnicaInstancia().addObjeto(codigo, f);
		
		AdaptadorUsuarioTDS adaptadorUsuario = AdaptadorUsuarioTDS.getUnicaInstancia();
		int codigoUsuario = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eFoto, "usuario"));
		Usuario u = adaptadorUsuario.recuperarUsuario(codigoUsuario);
		
		f.setUser(u);
		
		return f;
	}
	
	public List<Foto> recuperarTodasFotos(){
		List<Foto> fotos = new LinkedList<Foto>();
		List<Entidad> eFotos = servPersistencia.recuperarEntidades("foto");
		
		for (Entidad eFoto : eFotos) {
			fotos.add(recuperarFoto(eFoto.getId()));
		}
		
		return fotos;
	}
}

