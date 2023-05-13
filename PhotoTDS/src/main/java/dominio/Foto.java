package dominio;

import java.time.LocalDate;

public class Foto extends Publicacion{

	private String ruta;
	
	public Foto(String ruta, String descripcion, Usuario user, LocalDate fechaSubida, String...hashtags) {
		super(descripcion, user, fechaSubida, hashtags);
		this.ruta = ruta;	
	}

	public Foto(String ruta, String descripcion, int likes, LocalDate fecha) {
		super(descripcion, likes, fecha);
		this.ruta = ruta;
	}
	
	public String getRuta() {
		return ruta;
	}
	
}
