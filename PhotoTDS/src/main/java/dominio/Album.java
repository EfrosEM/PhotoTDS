package dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Album extends Publicacion{

	private ArrayList<Foto> fotos;

	public Album(String titulo, Usuario user, LocalDate fecha, String...hashtag) {
		super(titulo, user, fecha, hashtag);
		this.fotos = new ArrayList<Foto>();
	}
	
	public Album(String titulo, LocalDate fecha, String...hashtag) {
		super(titulo, fecha);
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
