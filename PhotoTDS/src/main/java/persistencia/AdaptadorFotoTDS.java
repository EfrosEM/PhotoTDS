package persistencia;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import dominio.Foto;
import dominio.Usuario;
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
						new Propiedad("descripcion", f.getDescripcion()),
						new Propiedad("likes", String.valueOf(f.getLikes())),
						new Propiedad("fecha", f.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
						new Propiedad("hastags", obtenerCodigoHastags(f.getHashtags())),
						new Propiedad("usuario", String.valueOf(f.getUser().getCodigo()))
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
		
			} else if (prop.getNombre().equals("fecha")) {
				prop.setValor(f.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
				
			} else if (prop.getNombre().equals("descripcion")) {
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
		Usuario usuario = null;
		String ruta;
		String descripcion;
		int likes;
		LocalDate fecha = null;
		List<String> hashtags = null;

		
		eFoto = servPersistencia.recuperarEntidad(codigo);
		
		ruta = servPersistencia.recuperarPropiedadEntidad(eFoto, "ruta");
		descripcion = servPersistencia.recuperarPropiedadEntidad(eFoto, "descripcion");
		likes = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eFoto, "likes"));
		
		try {
			fecha = LocalDate.parse(servPersistencia.recuperarPropiedadEntidad(eFoto, "fecha"));
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		Foto f = new Foto(ruta, descripcion, likes, fecha);
		f.setCodigo(codigo);
		
		PoolDAO.getUnicaInstancia().addObjeto(codigo, f);
		
		AdaptadorUsuarioTDS adaptadorUsuario = AdaptadorUsuarioTDS.getUnicaInstancia();
		int codigoUsuario = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eFoto, "usuario"));
		usuario = adaptadorUsuario.recuperarUsuario(codigoUsuario);
		f.setUser(usuario);
		
		hashtags = obtenerHastagsDesdeCodigo(servPersistencia.recuperarPropiedadEntidad(eFoto, "hashtags"));
		for (String hashtag : hashtags) {
			f.addHastag(hashtag);
		}
				
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

