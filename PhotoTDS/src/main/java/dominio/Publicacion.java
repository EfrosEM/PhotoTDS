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
	
	public Publicacion(String titulo, String descripcion, Usuario user, int likes, LocalDate fecha, String...hashtags) {
		this.titulo = titulo;
		this.likes = likes;
		this.descripcion = descripcion;
		this.user = user;
		this.fecha = fecha;
		this.comentarios = new ArrayList<Comentario>();
		this.hashtags = new ArrayList<String>();
		
		for(String hastag : hashtags) {
			this.hashtags.add(hastag);
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
	
}
