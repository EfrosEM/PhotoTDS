package Dominio;

import java.util.ArrayList;
import java.util.List;

public class Album extends Publicacion{

	private ArrayList<Foto> fotos;

	public Album(String titulo, String descripcion, Usuario user, Hashtag...hashtag) {
		super(titulo, descripcion, user, hashtag);
		this.fotos = new ArrayList<Foto>();
	}

	public List<Foto> getFotos() {
		return new ArrayList<Foto>(fotos);
	}

}
