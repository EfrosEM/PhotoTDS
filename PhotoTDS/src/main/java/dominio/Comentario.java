package dominio;

import java.time.LocalDate;

public class Comentario {

	private String texto;
	private Usuario user;
	private LocalDate fecha;
	
	public Comentario(String texto, Usuario user) {
		this.texto = texto;
		this.user = user;
		this.fecha = LocalDate.now();
	}
	
	public String getTexto() {
		return texto;
	}
	
	public Usuario getUsuario() {
		return user;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	
}
