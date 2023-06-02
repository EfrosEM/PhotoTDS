package dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Album extends Publicacion{

	private ArrayList<Foto> fotos;

	public Album(String titulo, Foto foto, Usuario user, LocalDate fecha, String...hashtag) {
		super(titulo, user, fecha, hashtag);
		this.fotos = new ArrayList<Foto>();
		this.fotos.add(foto);
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
	
	public Foto getFotoAlbum() {
		return this.fotos.get(0);
	}
	
	public void eliminarFoto(Foto foto) {
		Iterator<Foto> iterator = fotos.iterator();
		while (iterator.hasNext()) {
		    Foto f = iterator.next();
		    if (f.getCodigo() == foto.getCodigo()) {
		        iterator.remove(); // Eliminar el elemento de forma segura
		    }
		}
	}


}
