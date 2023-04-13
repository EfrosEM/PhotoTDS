package dominio;

import java.time.LocalDate;

public class Foto extends Publicacion{

	private String ruta;
	
	public Foto(String ruta, String titulo, String descripcion, Usuario user, String...hashtag) {
		super(titulo, descripcion, user, hashtag);
		this.ruta = ruta;	
	}

	public Foto(String ruta, String titulo, String descripcion, int likes, LocalDate fecha) {
		super(titulo, descripcion, null, likes, fecha);
		this.ruta = ruta;
	}
	
	public String getRuta() {
		return ruta;
	}
	
}
