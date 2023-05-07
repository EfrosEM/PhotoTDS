package dominio;

import java.time.LocalDate;

public class Notificacion {

	private LocalDate fecha;
	private Publicacion publicacion;
	private Usuario user;
	
	private int codigo;
	
	public Notificacion(Usuario user, Publicacion publicacion) {
		this.user = user;
		this.publicacion = publicacion;
		fecha = LocalDate.now();
	}
	
	public Notificacion(LocalDate fecha) {
		this.fecha = fecha;
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
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setUser(Usuario u) {
		this.user = u;
	}
	
	public void setPublicacion(Foto f) {
		this.publicacion = f;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	
}
