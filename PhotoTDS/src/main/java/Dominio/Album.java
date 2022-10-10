package Dominio;

import java.util.ArrayList;
import java.util.List;

public class Album {

	private String tematica;
	private ArrayList<Foto> fotos;
	private ArrayList<Hashtag> hashtags;
	
	public Album(String tematica) {
		this.tematica = tematica;
		this.fotos = new ArrayList<Foto>();
		this.hashtags = new ArrayList<Hashtag>();
	}

	public String getTematica() {
		return tematica;
	}

	public List<Foto> getFotos() {
		return new ArrayList<Foto>(fotos);
	}
	
	public List<Hashtag> getHashtags() {
		return new ArrayList<Hashtag>(hashtags);
	}
}
