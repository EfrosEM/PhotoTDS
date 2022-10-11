package Dominio;

import java.util.*;

public class Usuario {

	private String nombre;
	private String usuario;
	private String email;
	private String password;
	private Date nacimiento;
	private String descripcion;
	private Foto fotoPerfil;
	private boolean premium;
	private ArrayList<Usuario> seguidores;
	private ArrayList<Foto> fotos;
	private ArrayList<Album> albumes;
	
	public Usuario(String nombre, String usuario, String email, String password, Date nacimiento, String descripcion, Foto fotoPerfil) {
		this.nombre = nombre;
		this.usuario = usuario;
		this.email = email;
		this.password = password;
		this.nacimiento = nacimiento;
		this.descripcion = descripcion;
		this.fotoPerfil = fotoPerfil;
		
		premium = false;
		seguidores = new ArrayList<Usuario>();
		fotos = new ArrayList<Foto>();
		albumes = new ArrayList<Album>();
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Date getNacimiento() {
		return nacimiento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Foto getFotoPerfil() {
		return fotoPerfil;
	}
	
	public List<Usuario> getSeguidores() {
		return new ArrayList<Usuario>(seguidores);
	}
	
	public List<Foto> getFotos() {
		return new ArrayList<Foto>(fotos);
	}
	
	public List<Album> getAlbumes() {
		return new ArrayList<Album>(albumes);
	}
	
	public boolean isPremium() {
		return premium;
	}

	

}
