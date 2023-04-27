package dominio;

import java.time.LocalDate;

public class Comentario {

	private int codigo;
	
	private String texto;
	private Usuario user;
	private LocalDate fecha;
	
	public Comentario(String texto, Usuario user) {
		this.texto = texto;
		this.user = user;
		this.fecha = LocalDate.now();
	}
	
	public Comentario(String texto, LocalDate fecha) {
		this.texto = texto;
		this.fecha = fecha;
		this.codigo = 0;
	}
	
	public String getTexto() {
		return texto;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	
	public int getCodigo() {
		return this.codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
}
