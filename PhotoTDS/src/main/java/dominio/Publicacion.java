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
	private ArrayList<String> hashtags;
	private ArrayList<Comentario> comentarios;
		
	public Publicacion(String titulo, String descripcion, Usuario user, String...hashtags) {
		this.titulo = titulo;
		this.likes = 0;
		this.descripcion = descripcion;
		this.user = user;
		this.fecha = LocalDate.now();
		this.comentarios = new ArrayList<Comentario>();
		this.hashtags = new ArrayList<String>();
		
		for(String hastag : hashtags) {
			this.hashtags.add(hastag);
		}
		
		this.codigo = 0;
	}
	
	public Publicacion(String descripcion, Usuario user, String...hashtag) {
		this.likes = 0;
		this.descripcion = descripcion;
		this.user = user;
		this.fecha = LocalDate.now();
		this.comentarios = new ArrayList<Comentario>();
		this.hashtags = new ArrayList<String>();
		
		for(String hastag : hashtags) {
			this.hashtags.add(hastag);
		}
		
		this.codigo = 0;	
	}
	
	// Constructor para FOTO
	public Publicacion(String descripcion, int likes, LocalDate fecha) {
		this.likes = likes;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.comentarios = new ArrayList<Comentario>();
		this.hashtags = new ArrayList<String>();
		
		this.codigo = 0;
	}
	
	// Constructor para FOTO
	public Publicacion(String titulo, Usuario user, LocalDate fecha, String...hashtags) {
		this.titulo = titulo;
		this.user = user;
		this.fecha = fecha;
		this.hashtags = new ArrayList<String>();
		this.comentarios = new ArrayList<Comentario>();

		for(String hastag : hashtags) {
			this.hashtags.add(hastag);
		}
		
		this.codigo = 0;
		
	}
	
	public Publicacion(String titulo, LocalDate fecha) {
		this.titulo = titulo;
		this.fecha = fecha;
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
	
	public List<String> getHashtags() {
		return new ArrayList<String>(hashtags);
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
	
	public void addLike() {
		likes++;
	}
	
	public void addHastag(String hashtag) {
		hashtags.add(hashtag);
	}
	
	public void addComentario(Comentario comentario) {
		comentarios.add(comentario);
	}
	
}
