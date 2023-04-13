package persistencia;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
		
		servPersistencia.eliminarPropiedadEntidad(eFoto, "usuario");
		servPersistencia.anadirPropiedadEntidad(eFoto, "usuario", String.valueOf(f.getUser().getCodigo()));
		
		servPersistencia.eliminarPropiedadEntidad(eFoto, "ruta");
		servPersistencia.anadirPropiedadEntidad(eFoto, "ruta", f.getRuta());
		
		servPersistencia.eliminarPropiedadEntidad(eFoto, "titulo");
		servPersistencia.anadirPropiedadEntidad(eFoto, "titulo", f.getTitulo());
		
		servPersistencia.eliminarPropiedadEntidad(eFoto, "descripcion");
		servPersistencia.anadirPropiedadEntidad(eFoto, "descripcion", f.getDescripcion());
		
		servPersistencia.eliminarPropiedadEntidad(eFoto, "likes");
		servPersistencia.anadirPropiedadEntidad(eFoto, "likes", String.valueOf(f.getLikes()));
		
		//servPersistencia.eliminarPropiedadEntidad(eFoto, "fecha");
		//servPersistencia.anadirPropiedadEntidad(eFoto, "fecha", dateFormat.format(f.getFecha()));
		
		servPersistencia.eliminarPropiedadEntidad(eFoto, "hastags");
		servPersistencia.anadirPropiedadEntidad(eFoto, "hastags", f.getHashtags().toString());
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

