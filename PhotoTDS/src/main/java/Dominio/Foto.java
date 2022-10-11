package Dominio;

import java.time.LocalDate;
import java.util.*;

public class Foto {

	private String ruta;
	private int likes;
	private String descripcion;
	private LocalDate fecha;
	private Usuario user;
	private ArrayList<Comentario> comentarios;
	private ArrayList<Hashtag> hashtags;
	
	public Foto(String ruta, String descripcion, Usuario user) {
		this.ruta = ruta;
		this.likes = 0;
		this.descripcion = descripcion;
		this.user = user;
		this.fecha = LocalDate.now();
		this.comentarios = new ArrayList<Comentario>();
		this.hashtags = new ArrayList<Hashtag>();
	}

	public String getRuta() {
		return ruta;
	}

	public int getLikes() {
		return likes;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public LocalDate getFecha() {
		return fecha;
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
}
