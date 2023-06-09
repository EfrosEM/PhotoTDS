package persistencia;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import dominio.Comentario;
import dominio.Usuario;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorComentarioTDS implements IAdaptadorComentarioDAO{

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorComentarioTDS unicaInstancia;
	@SuppressWarnings("unused")
	private SimpleDateFormat dateFormat;
	
	public static AdaptadorComentarioTDS getUnicaInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new AdaptadorComentarioTDS();
		}
		
		return unicaInstancia;
	}
	
	private AdaptadorComentarioTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	public void registrarComentario(Comentario c) {
		Entidad eComentario = null;
		
		try {
			eComentario = servPersistencia.recuperarEntidad(c.getCodigo());
		} catch (Exception e) {
		}
		
		if (eComentario != null) return;
		
		//Aquí habría que registrar primero los atributos que son objetos
		AdaptadorUsuarioTDS adaptadorU = AdaptadorUsuarioTDS.getUnicaInstancia();
		adaptadorU.registrarUsuario(c.getUser());
		
		eComentario = new Entidad();
		eComentario.setNombre("comentario");
		eComentario.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(
						new Propiedad("texto", c.getTexto()),
						new Propiedad("fecha", c.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
						new Propiedad("usuario", String.valueOf(c.getUser().getCodigo()))
				))
		);
		
		eComentario = servPersistencia.registrarEntidad(eComentario);
		c.setCodigo(eComentario.getId());
	}
	
	public void borrarComentario(Comentario c) {
		Entidad eComentario;
		
		eComentario = servPersistencia.recuperarEntidad(c.getCodigo());
		servPersistencia.borrarEntidad(eComentario);
	}
	
	public void modificarComentario(Comentario c) {
		Entidad eComentario = servPersistencia.recuperarEntidad(c.getCodigo());
		
		for (Propiedad prop : eComentario.getPropiedades()) {
			
			if (prop.getNombre().equals("usuario")) {
				prop.setValor(String.valueOf(c.getUser().getCodigo()));
				
			} else if (prop.getNombre().equals("texto")) {
				prop.setValor(c.getTexto());
				
			} else if (prop.getNombre().equals("fecha")) {
				prop.setValor(c.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			}
				
			servPersistencia.modificarPropiedad(prop);
		}
	}
	
	public Comentario recuperarComentario(int codigo) {
		if (PoolDAO.getUnicaInstancia().contiene(codigo))
			return (Comentario) PoolDAO.getUnicaInstancia().getObjeto(codigo);
		
		Entidad eComentario = new Entidad();
		String texto;
		LocalDate fecha = null;
		Usuario usuario = null;
		
		eComentario = servPersistencia.recuperarEntidad(codigo);
		
		texto = servPersistencia.recuperarPropiedadEntidad(eComentario, "texto");
		
		try {
			//fecha = LocalDate.parse(servPersistencia.recuperarPropiedadEntidad(eComentario, "fecha"));
			fecha = LocalDate.parse(servPersistencia.recuperarPropiedadEntidad(eComentario, "fecha"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		Comentario c = new Comentario(texto, fecha);
		c.setCodigo(codigo);
		
		PoolDAO.getUnicaInstancia().addObjeto(codigo, c);
		
		AdaptadorUsuarioTDS adaptadorUsuario = AdaptadorUsuarioTDS.getUnicaInstancia();
		int codigoUsuario = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eComentario, "usuario"));
		usuario = adaptadorUsuario.recuperarUsuario(codigoUsuario);
		c.setUser(usuario);
				
		return c;
	}
	
	public List<Comentario> recuperarTodasComentarios(){
		List<Comentario> comentarios = new LinkedList<Comentario>();
		List<Entidad> eComentarios = servPersistencia.recuperarEntidades("comentario");
		
		for (Entidad eComentario : eComentarios) {
			comentarios.add(recuperarComentario(eComentario.getId()));
		}
		
		return comentarios;
	}
}
