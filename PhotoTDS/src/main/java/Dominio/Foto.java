package Dominio;

public class Foto extends Publicacion{

	private String ruta;
	
	public Foto(String ruta, String titulo, String descripcion, Usuario user, Hashtag...hashtag) {
		super(titulo, descripcion, user, hashtag);
		this.ruta = ruta;	
	}

	public String getRuta() {
		return ruta;
	}

}
