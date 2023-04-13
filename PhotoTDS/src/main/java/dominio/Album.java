package dominio;

import java.util.ArrayList;
import java.util.List;

public class Album extends Publicacion{

	private ArrayList<Foto> fotos;

	public Album(String titulo, String descripcion, Usuario user, String...hashtag) {
		super(titulo, descripcion, user, hashtag);
		this.fotos = new ArrayList<Foto>();
	}

	public List<Foto> getFotos() {
		return new ArrayList<Foto>(fotos);
	}

}
