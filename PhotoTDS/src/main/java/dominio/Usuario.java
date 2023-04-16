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
	private int seguidos;
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
		this.seguidos = 0;
		
		premium = false;
		seguidores = new ArrayList<Usuario>();
		publicaciones = new ArrayList<Publicacion>();
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String user) {
		this.usuario = user;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getNacimiento() {
		return nacimiento;
	}
	
	public void setNacimiento(Date fecha) {
		this.nacimiento = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFotoPerfil() {
		return fotoPerfil;
	}
	
	public void setFotoPerfil(String foto) {
		this.fotoPerfil = foto;
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
	
	public void setSeguidos(int seguidos) {
		this.seguidos = seguidos;
	}
	
	public void addSeguidor(Usuario seguidor) {
		seguidores.add(seguidor);
	}
	
	public void removeSeguidor(Usuario seguidor) {
		seguidores.remove(seguidor);
	}

	public void addPublicacion(Publicacion p) {
		publicaciones.add(p);
	}

	public boolean esSeguidor(String user) {
		
		for (Usuario seguidor: seguidores) {
			if (seguidor.getUsuario().equals(user)) {
				return true;
			}
		}
		
		return false;
	}
	
	public void addSeguidos() {
		seguidos++;
	}
	
	public void removeSeguidos() {
		if(seguidos>0)
			seguidos--;
	}

	public int getSeguidos() {
		return seguidos;
	}

	

}
