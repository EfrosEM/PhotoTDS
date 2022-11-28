package dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Publicacion {

	private int codigo;
	
	private String titulo;
	private LocalDate fecha;
	private String descripcion;
	private int likes;
	private Usuario user;
	private ArrayList<Hashtag> hashtags;
	private ArrayList<Comentario> comentarios;
		
	public Publicacion(String titulo, String descripcion, Usuario user, Hashtag...hashtag) {
		this.titulo = titulo;
		this.likes = 0;
		this.descripcion = descripcion;
		this.user = user;
		this.fecha = LocalDate.now();
		this.comentarios = new ArrayList<Comentario>();
		this.hashtags = new ArrayList<Hashtag>();
		
		for(Hashtag h : hashtag) {
			hashtags.add(h);
		}
		
		this.codigo = 0;
	}

	public String getTitulo() {
		return titulo;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	
	public int getLikes() {
		return likes;
	}
	
	public Usuario getUser() {
		return user;
	}
	
	public List<Comentario> getComentarios() {
		return new ArrayList<Comentario>(comentarios);
	}
	
	public List<Hashtag> getHashtags() {
		return new ArrayList<Hashtag>(hashtags);
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setUser(Usuario u) {
		this.user = u;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
}
