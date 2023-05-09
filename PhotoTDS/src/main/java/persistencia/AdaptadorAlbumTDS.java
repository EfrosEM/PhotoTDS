package persistencia;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import dominio.Album;
import dominio.Foto;
import dominio.Usuario;
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
		
		AdaptadorFotoTDS adaptadorF = AdaptadorFotoTDS.getUnicaInstancia();
		for (Foto foto : a.getFotos()) {
			adaptadorF.registrarFoto(foto);
		}
		
		eAlbum = new Entidad();
		eAlbum.setNombre("album");
		eAlbum.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(
						new Propiedad("titulo", a.getTitulo()),
						new Propiedad("fecha", a.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
						new Propiedad("hastags", obtenerCodigoHastags(a.getHashtags())),
						new Propiedad("usuario", String.valueOf(a.getUser().getCodigo())),
						new Propiedad("fotos", obtenerCodigosFotos(a.getFotos()))
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
				
			} else if (prop.getNombre().equals("fecha")) {
				prop.setValor(a.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
									
			} else if (prop.getNombre().equals("hastags")) {
				prop.setValor(obtenerCodigoHastags(a.getHashtags()));
				
			} else if (prop.getNombre().equals("fotos")) {
				prop.setValor(obtenerCodigosFotos(a.getFotos()));
			}
			
			servPersistencia.modificarPropiedad(prop);
			
		}
	}
	
	public Album recuperarAlbum(int codigo) {
		if (PoolDAO.getUnicaInstancia().contiene(codigo))
			return (Album) PoolDAO.getUnicaInstancia().getObjeto(codigo);
		
		Entidad eAlbum = new Entidad();
		Usuario usuario = null;
		String titulo;
		LocalDate fecha = null;
		List<String> hashtags = null;
		List<Foto> fotos = null;
		
		eAlbum = servPersistencia.recuperarEntidad(codigo);
		
		titulo = servPersistencia.recuperarPropiedadEntidad(eAlbum, "titulo");
		
		try {
			fecha = LocalDate.parse(servPersistencia.recuperarPropiedadEntidad(eAlbum, "fecha"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		Album a = new Album(titulo, fecha);
		a.setCodigo(codigo);
		
		PoolDAO.getUnicaInstancia().addObjeto(codigo, a);
		
		AdaptadorUsuarioTDS adaptadorUsuario = AdaptadorUsuarioTDS.getUnicaInstancia();
		int codigoUsuario = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eAlbum, "usuario"));
		usuario = adaptadorUsuario.recuperarUsuario(codigoUsuario);
		a.setUser(usuario);
		
		hashtags = obtenerHastagsDesdeCodigo(servPersistencia.recuperarPropiedadEntidad(eAlbum, "hashtags"));
		for (String hashtag : hashtags) {
			a.addHastag(hashtag);
		}
		
		fotos = obtenerFotosDesdeCodigo(servPersistencia.recuperarPropiedadEntidad(eAlbum, "fotos"));
		for (Foto foto : fotos) {
			a.addFoto(foto);
		}
		
				
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
	
	private String obtenerCodigosFotos(List<Foto> fotos) {
		String codigosFotos = "";
		
		for (Foto foto : fotos) {
			codigosFotos += foto.getCodigo() + " ";
		}
		
		return codigosFotos.trim();
	}
	
	private List<Foto> obtenerFotosDesdeCodigo(String codigoFotos) {
		List<Foto> fotos = new ArrayList<>();
		
		if (codigoFotos == null || codigoFotos.equals("")) {
			return fotos;
		}
		
		StringTokenizer strTok = new StringTokenizer(codigoFotos, " ");
		AdaptadorFotoTDS adaptadorFoto = AdaptadorFotoTDS.getUnicaInstancia();
		
		while (strTok.hasMoreTokens()) {
			fotos.add(adaptadorFoto.recuperarFoto(Integer.valueOf((String) strTok.nextElement())));
		}
		
		return fotos;
	}

}
