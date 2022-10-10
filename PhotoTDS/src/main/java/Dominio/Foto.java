package Dominio;

import java.util.*;

public class Foto {

	private String ruta;
	private int likes;
	private ArrayList<String> comentarios;
	private ArrayList<Hashtag> hashtags;
	
	public Foto(String ruta) {
		this.ruta = ruta;
		this.likes = 0;
		this.comentarios = new ArrayList<String>();
		this.hashtags = new ArrayList<Hashtag>();
	}

	public String getRuta() {
		return ruta;
	}

	public int getLikes() {
		return likes;
	}

	public List<String> getComentarios() {
		return new ArrayList<String>(comentarios);
	}
	
	public List<Hashtag> getHashtags() {
		return new ArrayList<Hashtag>(hashtags);
	}
}
