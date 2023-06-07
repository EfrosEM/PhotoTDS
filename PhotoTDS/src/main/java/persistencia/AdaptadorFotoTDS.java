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
import dominio.Comentario;
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
		
		AdaptadorComentarioTDS adaptadorC = AdaptadorComentarioTDS.getUnicaInstancia();
		for (Comentario comentario : f.getComentarios()) {
			adaptadorC.registrarComentario(comentario);
		}
		
		eFoto = new Entidad();
		eFoto.setNombre("foto");
		eFoto.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(
						new Propiedad("ruta", f.getRuta()),
						new Propiedad("descripcion", f.getDescripcion()),
						new Propiedad("likes", String.valueOf(f.getLikes())),
						new Propiedad("fecha", f.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
						new Propiedad("hashtags", obtenerCodigoHashtags(f.getHashtags())),
						new Propiedad("usuario", String.valueOf(f.getUser().getCodigo())),
						new Propiedad("comentarios", obtenerCodigoComentarios(f.getComentarios()))
				))
		);
		
		eFoto = servPersistencia.registrarEntidad(eFoto);
		f.setCodigo(eFoto.getId());
	}
	
	public void borrarFoto(Foto f) {
		Entidad eFoto;
		
		// Borrar los comentarios asociados a dicha foto
		AdaptadorComentarioTDS adaptadorComentario = AdaptadorComentarioTDS.getUnicaInstancia();
		for (Comentario comentario : f.getComentarios()) {
			adaptadorComentario.borrarComentario(comentario);
		}
		
		eFoto = servPersistencia.recuperarEntidad(f.getCodigo());
		System.out.println("Codigo de la fotoa a ser borrada: " + f.getCodigo());
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
				
			} else if (prop.getNombre().equals("hashtags")) {
				prop.setValor(f.getHashtags().toString());
				
			} else if (prop.getNombre().equals("comentarios")) {
				prop.setValor(obtenerCodigoComentarios(f.getComentarios()));
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
		LocalDate fecha;
		List<String> hashtags = null;
		List<Comentario> comentarios = null;

		
		eFoto = servPersistencia.recuperarEntidad(codigo);
		
		ruta = servPersistencia.recuperarPropiedadEntidad(eFoto, "ruta");
		descripcion = servPersistencia.recuperarPropiedadEntidad(eFoto, "descripcion");
		likes = Integer.valueOf(servPersistencia.recuperarPropiedadEntidad(eFoto, "likes"));
		
		try {
			fecha = LocalDate.parse(servPersistencia.recuperarPropiedadEntidad(eFoto, "fecha"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		} catch (Exception e) {
			fecha = null;
		}
				
		Foto f = new Foto(ruta, descripcion, likes, fecha);
		f.setCodigo(codigo);
		
		PoolDAO.getUnicaInstancia().addObjeto(codigo, f);
		
		AdaptadorUsuarioTDS adaptadorUsuario = AdaptadorUsuarioTDS.getUnicaInstancia();
		int codigoUsuario = Integer.valueOf(servPersistencia.recuperarPropiedadEntidad(eFoto, "usuario"));
		usuario = adaptadorUsuario.recuperarUsuario(codigoUsuario);
		f.setUser(usuario);
		
		hashtags = obtenerHashtagsDesdeCodigo(servPersistencia.recuperarPropiedadEntidad(eFoto, "hashtags"));
		for (String hashtag : hashtags) {
			f.addHastag(hashtag);
		}
		
		comentarios = obtenerComentariosDesdeCodigo(servPersistencia.recuperarPropiedadEntidad(eFoto, "comentarios"));
		for (Comentario comentario : comentarios) {
			f.addComentario(comentario);
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
	
	private String obtenerCodigoHashtags(List<String> hashtags) {
		String codigoHashtags = "";
		
		for (String hashtag : hashtags) {
			codigoHashtags += hashtag + " ";
		}
		
		return codigoHashtags.trim();
	}
	
	private List<String> obtenerHashtagsDesdeCodigo(String codigoHashtags) {
		List<String> hashtags = new ArrayList<>();
		
		if (codigoHashtags == null || codigoHashtags.equals("")) {
			return hashtags;
		}
		
		StringTokenizer strTok = new StringTokenizer(codigoHashtags, " ");
		
		while (strTok.hasMoreTokens()) {
			hashtags.add((String) strTok.nextElement());
		}
		
		return hashtags;
	}
	
	private String obtenerCodigoComentarios(List<Comentario> comentarios) {
		String codigoComentarios = "";
		
		for (Comentario comentario : comentarios) {
			codigoComentarios += comentario.getCodigo() + " ";
		}
		
		return codigoComentarios.trim();
	}
	
	private List<Comentario> obtenerComentariosDesdeCodigo(String codigoComentarios) {
		List<Comentario> comentarios = new ArrayList<>();
		
		if (codigoComentarios == null || codigoComentarios.equals("")) {
			return comentarios;
		}
		
		StringTokenizer strTok = new StringTokenizer(codigoComentarios, " ");
		AdaptadorComentarioTDS adaptadorC = AdaptadorComentarioTDS.getUnicaInstancia();
		
		while (strTok.hasMoreTokens()) {
			comentarios.add(adaptadorC.recuperarComentario(Integer.valueOf((String) strTok.nextElement())));
		}
		
		return comentarios;
	}
}

