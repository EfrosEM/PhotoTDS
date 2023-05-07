package persistencia;

import java.util.List;

import dominio.Notificacion;

public interface IAdaptadorNotificacionDAO {

	public void registrarNotificacion(Notificacion n);
	public void borrarNotificacion(Notificacion n);
	public void modificarNotificacion(Notificacion n);
	public Notificacion recuperarNotificacion(int codigo);
	public List<Notificacion> recuperarTodasNotificaciones();
}
