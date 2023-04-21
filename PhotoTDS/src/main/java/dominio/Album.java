package dominio;

import java.util.ArrayList;
import java.util.List;

public class Album extends Publicacion{

	private ArrayList<Foto> fotos;

	public Album(String titulo, Usuario user, String...hashtag) {
		super(titulo, null, user, hashtag);
		this.fotos = new ArrayList<Foto>();
	}

	public List<Foto> getFotos() {
		return new ArrayList<Foto>(fotos);
	}
	
	public void addFoto(Foto f) {
		fotos.add(f);
	}
	
	public void addLikes() {
		for (Foto foto : fotos) {
			foto.addLike();
		}
	}


}
