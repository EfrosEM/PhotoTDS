package persistencia;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import dominio.Foto;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorFotoTDS implements IAdaptadorFotoDAO{

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorFotoTDS unicaInstancia;
	@SuppressWarnings("unused")
	private SimpleDateFormat dateFormat;
	
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
						new Propiedad("descripcion", f.getDescripcion()),
						new Propiedad("likes", String.valueOf(f.getLikes())),
						//new Propiedad("fecha", dateFormat.format(f.getFecha())),
						new Propiedad("hastags", f.getHashtags().toString())
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
		
		for (Propiedad prop : eFoto.getPropiedades()) {
			
			if (prop.getNombre().equals("usuario")) {
				prop.setValor(String.valueOf(f.getUser().getCodigo()));
				
			} else if (prop.getNombre().equals("ruta")) {
				prop.setValor(f.getRuta());
				
			} else if (prop.getNombre().equals("titulo")) {
				prop.setValor(f.getTitulo());
				
			}  else if (prop.getNombre().equals("descripcion")) {
				prop.setValor(f.getDescripcion());
				
			}  else if (prop.getNombre().equals("likes")) {
				prop.setValor(String.valueOf(f.getLikes()));
				
			} else if (prop.getNombre().equals("hastags")) {
				prop.setValor(f.getHashtags().toString());
				
			} 
			
			servPersistencia.modificarPropiedad(prop);
			
		}
	}
	
	public Foto recuperarFoto(int codigo) {
		if (PoolDAO.getUnicaInstancia().contiene(codigo))
			return (Foto) PoolDAO.getUnicaInstancia().getObjeto(codigo);
		
		Entidad eFoto = new Entidad();
		String ruta;
		String titulo;
		String descripcion;
		int likes;
		//LocalDate fecha = null;
		
		eFoto = servPersistencia.recuperarEntidad(codigo);
		
		ruta = servPersistencia.recuperarPropiedadEntidad(eFoto, "ruta");
		titulo = servPersistencia.recuperarPropiedadEntidad(eFoto, "titulo");
		descripcion = servPersistencia.recuperarPropiedadEntidad(eFoto, "descripcion");
		likes = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eFoto, "likes"));
		
		/*try {
			fecha = LocalDate.parse(servPersistencia.recuperarPropiedadEntidad(eFoto, "fecha"));
		} catch (Exception e) {
			e.printStackTrace();
		}*/
				
		Foto f = new Foto(ruta, titulo, descripcion, likes, null);
		f.setCodigo(codigo);
		
		PoolDAO.getUnicaInstancia().addObjeto(codigo, f);
		
		/*
		AdaptadorFotoTDS adaptadorFoto = AdaptadorFotoTDS.getUnicaInstancia();
		int codigoFoto = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eFoto, "foto"));
		Foto f = adaptadorFoto.recuperarFoto(codigoFoto);
		*/
				
		return f;
	}
	
	public List<Foto> recuperarTodasFotos(){
		List<Foto> fotos = new LinkedList<Foto>();
		List<Entidad> eFotos = servPersistencia.recuperarEntidades("foto");
		
		for (Entidad eFoto : eFotos) {
			fotos.add(recuperarFoto(eFoto.getId()));
			System.out.println("foto: " + recuperarFoto(eFoto.getId()).getRuta() + " con codigo: " +  recuperarFoto(eFoto.getId()).getCodigo());
		}
		
		return fotos;
	}
}

