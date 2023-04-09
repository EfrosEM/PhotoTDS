package dominio;

import java.util.*;

public class Usuario {

	private int codigo;
	
	private String nombre;
	private String apellidos;
	private String usuario;
	private String email;
	private String password;
	private Date nacimiento;
	private String descripcion;
	private String fotoPerfil;
	private Boolean premium;
	private ArrayList<Usuario> seguidores;
	private ArrayList<Publicacion> publicaciones;
	
	public Usuario(String nombre, String usuario, String apellidos, String email, String password, Date nacimiento, String descripcion, String fotoPerfil) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.usuario = usuario;
		this.email = email;
		this.password = password;
		this.nacimiento = nacimiento;
		this.descripcion = descripcion;
		this.fotoPerfil = fotoPerfil;
		this.codigo = 0;
		
		premium = false;
		seguidores = new ArrayList<Usuario>();
		publicaciones = new ArrayList<Publicacion>();
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getApellidos() {
		return apellidos;
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

	public String getFotoPerfil() {
		return fotoPerfil;
	}
	
	public List<Usuario> getSeguidores() {
		return new ArrayList<Usuario>(seguidores);
	}
	
	public List<Publicacion> getPublicaciones() {
		return new ArrayList<Publicacion>(publicaciones);
	}

	public Boolean isPremium() {
		return premium;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public void setPremium(Boolean prem) {
		this.premium = prem;
	}
	
	public void addSeguidor(Usuario seguidor) {
		seguidores.add(seguidor);
	}

	

}
