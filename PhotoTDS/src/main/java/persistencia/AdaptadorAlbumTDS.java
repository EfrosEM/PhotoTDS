package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import dominio.Album;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorAlbumTDS implements IAdaptadorAlbumDAO{

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorAlbumTDS unicaInstancia;
	
	public static AdaptadorAlbumTDS getUnicaInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new AdaptadorAlbumTDS();
		}
		
		return unicaInstancia;
	}
	
	private AdaptadorAlbumTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	public void registrarAlbum(Album a) {
		Entidad eAlbum = null;
		
		try {
			eAlbum = servPersistencia.recuperarEntidad(a.getCodigo());
		} catch (Exception e) {
		}
		
		if (eAlbum != null) return;
		
		//Aquí habría que registrar primero los atributos que son objetos
		AdaptadorUsuarioTDS adaptadorU = AdaptadorUsuarioTDS.getUnicaInstancia();
		adaptadorU.registrarUsuario(a.getUser());
		
		eAlbum = new Entidad();
		eAlbum.setNombre("album");
		eAlbum.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(
						new Propiedad("titulo", a.getTitulo()),
						new Propiedad("descripcion", a.getDescripcion()),
						//new Propiedad("fecha", dateFormat.format(a.getFecha())),
						new Propiedad("hastags", obtenerCodigoHastags(a.getHashtags()))
						// Falta registrar Albumes
				))
		);
		
		eAlbum = servPersistencia.registrarEntidad(eAlbum);
		a.setCodigo(eAlbum.getId());
	}
	
	public void borrarAlbum(Album a) {
		Entidad eAlbum;
		
		eAlbum = servPersistencia.recuperarEntidad(a.getCodigo());
		servPersistencia.borrarEntidad(eAlbum);
	}
	
	public void modificarAlbum(Album a) {
		Entidad eAlbum = servPersistencia.recuperarEntidad(a.getCodigo());
		
		for (Propiedad prop : eAlbum.getPropiedades()) {
			
			if (prop.getNombre().equals("usuario")) {
				prop.setValor(String.valueOf(a.getUser().getCodigo()));
				
			} else if (prop.getNombre().equals("titulo")) {
				prop.setValor(a.getTitulo());
				
			}  else if (prop.getNombre().equals("descripcion")) {
				prop.setValor(a.getDescripcion());
				
			} else if (prop.getNombre().equals("hastags")) {
				prop.setValor(obtenerCodigoHastags(a.getHashtags()));
				
			} 
			
			servPersistencia.modificarPropiedad(prop);
			
		}
	}
	
	public Album recuperarAlbum(int codigo) {
		if (PoolDAO.getUnicaInstancia().contiene(codigo))
			return (Album) PoolDAO.getUnicaInstancia().getObjeto(codigo);
		
		Entidad eAlbum = new Entidad();
		String titulo;
		//LocalDate fecha = null;
		
		eAlbum = servPersistencia.recuperarEntidad(codigo);
		
		titulo = servPersistencia.recuperarPropiedadEntidad(eAlbum, "titulo");
		
		/*try {
			fecha = LocalDate.parse(servPersistencia.recuperarPropiedadEntidad(eAlbum, "fecha"));
		} catch (Exception e) {
			e.printStackTrace();
		}*/
				
		Album a = new Album(titulo);
		a.setCodigo(codigo);
		
		PoolDAO.getUnicaInstancia().addObjeto(codigo, a);
		
		AdaptadorUsuarioTDS adaptadorUsuario = AdaptadorUsuarioTDS.getUnicaInstancia();
		a.setUser(adaptadorUsuario.recuperarUsuario(Integer.valueOf(servPersistencia.recuperarPropiedadEntidad(eAlbum, "usuario"))));
		
		List<String> hastags = obtenerHastagsDesdeCodigo(servPersistencia.recuperarPropiedadEntidad(eAlbum, "hashtags"));
		for (String hastag : hastags) {
			a.addHastag(hastag);
		}
		
		/*
		AdaptadorAlbumTDS adaptadorAlbum = AdaptadorAlbumTDS.getUnicaInstancia();
		int codigoAlbum = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eAlbum, "album"));
		Album a = adaptadorAlbum.recuperarAlbum(codigoAlbum);
		*/
				
		return a;
	}
	
	public List<Album> recuperarTodosAlbumes(){
		List<Album> albumes = new LinkedList<Album>();
		List<Entidad> eAlbumes = servPersistencia.recuperarEntidades("album");
		
		for (Entidad eAlbum : eAlbumes) {
			albumes.add(recuperarAlbum(eAlbum.getId()));
			System.out.println("album: " + recuperarAlbum(eAlbum.getId()).getTitulo() + " con codigo: " +  recuperarAlbum(eAlbum.getId()).getCodigo());
		}
		
		return albumes;
	}
	
	private String obtenerCodigoHastags(List<String> hastags) {
		String codigoHastags = "";
		
		for (String hastag : hastags) {
			codigoHastags += hastag + " ";
		}
		
		return codigoHastags.trim();
	}
	
	private List<String> obtenerHastagsDesdeCodigo(String codigoHastags) {
		List<String> hastags = new ArrayList<>();
		
		if (codigoHastags == null || codigoHastags.equals("")) {
			return hastags;
		}
		
		StringTokenizer strTok = new StringTokenizer(codigoHastags, " ");
		
		while (strTok.hasMoreTokens()) {
			hastags.add((String) strTok.nextElement());
		}
		
		return hastags;
	}

}
