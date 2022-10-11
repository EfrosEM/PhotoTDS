package dominio;

import java.time.LocalDate;

public class Notificacion {

	private LocalDate fecha;
	private Publicacion publicacion;
	private Usuario user;
	
	public Notificacion(Usuario user, Publicacion publicacion) {
		this.user = user;
		this.publicacion = publicacion;
		fecha = LocalDate.now();
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public Publicacion getPublicacion() {
		return publicacion;
	}

	public Usuario getUser() {
		return user;
	}
	
	
}
