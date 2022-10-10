package Dominio;

public class Comentario {

	private String texto;
	private Usuario user;
	
	public Comentario(String texto, Usuario user) {
		this.texto = texto;
		this.user = user;
	}
	
	public String getTexto() {
		return texto;
	}
	
	public Usuario getUsuario() {
		return user;
	}
	
}
