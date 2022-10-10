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
	
	public boolean isPremium() {
		return premium;
	}

	

}
