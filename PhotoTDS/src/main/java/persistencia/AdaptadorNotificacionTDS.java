package persistencia;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import dominio.Notificacion;
import dominio.Foto;
import dominio.Usuario;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorNotificacionTDS implements IAdaptadorNotificacionDAO {

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorNotificacionTDS unicaInstancia;

	public static AdaptadorNotificacionTDS getUnicaInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new AdaptadorNotificacionTDS();
		}

		return unicaInstancia;
	}

	private AdaptadorNotificacionTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	public void registrarNotificacion(Notificacion n) {
		Entidad eNotificacion = null;

		try {
			eNotificacion = servPersistencia.recuperarEntidad(n.getCodigo());
		} catch (Exception e) {
		}

		if (eNotificacion != null)
			return;

		// Aquí habría que registrar primero los atributos que son objetos
		AdaptadorUsuarioTDS adaptadorU = AdaptadorUsuarioTDS.getUnicaInstancia();
		adaptadorU.registrarUsuario(n.getUser());

		AdaptadorFotoTDS adaptadorF = AdaptadorFotoTDS.getUnicaInstancia();
		adaptadorF.registrarFoto((Foto) n.getPublicacion());

		eNotificacion = new Entidad();
		eNotificacion.setNombre("notificacion");
		eNotificacion.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(
						new Propiedad("usuario", String.valueOf(n.getUser().getCodigo())),
						new Propiedad("foto", String.valueOf(n.getPublicacion().getCodigo())),
						new Propiedad("fecha", n.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))))
				));

		eNotificacion = servPersistencia.registrarEntidad(eNotificacion);
		n.setCodigo(eNotificacion.getId());
	}

	public void borrarNotificacion(Notificacion n) {
		Entidad eNotificacion;

		eNotificacion = servPersistencia.recuperarEntidad(n.getCodigo());
		servPersistencia.borrarEntidad(eNotificacion);
	}

	public void modificarNotificacion(Notificacion n) {
		Entidad eNotificacion = servPersistencia.recuperarEntidad(n.getCodigo());
		
		for (Propiedad prop : eNotificacion.getPropiedades()) {
			
			if (prop.getNombre().equals("usuario")) {
				prop.setValor(String.valueOf(n.getUser().getCodigo()));
				
			} else if (prop.getNombre().equals("foto")) {
				prop.setValor(String.valueOf(n.getPublicacion().getCodigo()));
				
			} else if (prop.getNombre().equals("fecha")) {
				prop.setValor(n.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			}
			
			servPersistencia.modificarPropiedad(prop);
			
		}
	}

	public Notificacion recuperarNotificacion(int codigo) {
		if (PoolDAO.getUnicaInstancia().contiene(codigo))
			return (Notificacion) PoolDAO.getUnicaInstancia().getObjeto(codigo);

		Entidad eNotificacion = new Entidad();
		Usuario usuario = null;
		Foto foto = null;
		LocalDate fecha = null;
		
		eNotificacion = servPersistencia.recuperarEntidad(codigo);

		try {
			fecha = LocalDate.parse(servPersistencia.recuperarPropiedadEntidad(eNotificacion, "fecha"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		} catch (Exception e) { 
			fecha = null;
		}

		Notificacion n = new Notificacion(fecha);
		n.setCodigo(codigo);

		PoolDAO.getUnicaInstancia().addObjeto(codigo, n);

		AdaptadorUsuarioTDS adaptadorUsuario = AdaptadorUsuarioTDS.getUnicaInstancia();
		int codigoUsuario = Integer.valueOf(servPersistencia.recuperarPropiedadEntidad(eNotificacion, "usuario"));
		usuario = adaptadorUsuario.recuperarUsuario(codigoUsuario);
		n.setUser(usuario);

		AdaptadorFotoTDS adaptadorFoto = AdaptadorFotoTDS.getUnicaInstancia();
		int codigoFoto = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eNotificacion, "foto"));
		foto = adaptadorFoto.recuperarFoto(codigoFoto);
		n.setPublicacion(foto);

		return n;
	}

	public List<Notificacion> recuperarTodasNotificaciones() {
		List<Notificacion> notificaciones = new LinkedList<Notificacion>();
		List<Entidad> eNotificaciones = servPersistencia.recuperarEntidades("notificacion");

		for (Entidad eNotificacion : eNotificaciones) {
			notificaciones.add(recuperarNotificacion(eNotificacion.getId()));
		}

		return notificaciones;
	}

}
