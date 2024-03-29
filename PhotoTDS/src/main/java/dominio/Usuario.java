package dominio;

import java.time.LocalDate;
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
	private Descuento descuento;
	private ArrayList<Usuario> seguidores;
	private ArrayList<Publicacion> publicaciones;
	private ArrayList<Notificacion> notificaciones;

	public Usuario(String nombre, String usuario, String apellidos, String email, String password, Date nacimiento,
			String descripcion, String fotoPerfil) {
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
		descuento = null;
		seguidores = new ArrayList<Usuario>();
		publicaciones = new ArrayList<Publicacion>();
		notificaciones = new ArrayList<Notificacion>();
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

	public List<Notificacion> getNotificaciones() {
		return new ArrayList<Notificacion>(notificaciones);
	}
	
	public void setDescuento(Descuento descuento) {
		this.descuento = descuento;
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

	public void addNotificacion(Notificacion notificacion) {
		notificaciones.add(notificacion);
	}

	public boolean esSeguidor(String user) {

		for (Usuario seguidor : seguidores) {
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
		if (seguidos > 0)
			seguidos--;
	}

	public int getSeguidos() {
		return seguidos;
	}

	public boolean existsAlbum(String nombre) {

		for (Publicacion publicacion : publicaciones) {
			if (publicacion instanceof Album) {
				if (publicacion.getTitulo().equals(nombre)) {
					return true;
				}
			}
		}
		return false;
	}

	public Foto registrarFoto(String ruta, String descripcion, LocalDate fechaSubida, Usuario user,
			String... hashtags) {
		Foto foto = new Foto(ruta, descripcion, user, fechaSubida, hashtags);
		publicaciones.add(foto);

		return foto;
	}

	public Album registrarAlbum(String titulo, Foto foto, Usuario user, LocalDate fecha, String... hashtags) {
		Album album = new Album(titulo, foto, user, fecha, hashtags);
		publicaciones.add(album);

		return album;
	}

	public List<Foto> getFotos() {
		ArrayList<Foto> fotos = new ArrayList<Foto>();
		for (Publicacion p : getPublicaciones()) {
			if (p instanceof Foto) {
				fotos.add((Foto) p);
			}
		}

		return fotos;
	}

	public List<Album> getAlbumes() {
		ArrayList<Album> albumes = new ArrayList<Album>();
		for (Publicacion p : getPublicaciones()) {
			if (p instanceof Album) {
				albumes.add((Album) p);
			}
		}
		
		return albumes;
	}
	
	public void deletePublicacion(Publicacion p) {
		Iterator<Publicacion> iterator = publicaciones.iterator();
		while (iterator.hasNext()) {
		    Publicacion publicacion = iterator.next();
		    if (publicacion.getCodigo() == p.getCodigo()) {
		        iterator.remove(); // Eliminar el elemento de forma segura
		    }
		}
	}
	
	public Optional<Notificacion> deleteNotificacion(Publicacion p) {
		Iterator<Notificacion> iterator = notificaciones.iterator();
		while (iterator.hasNext()) {
		    Notificacion notificacion = iterator.next();
		    if (notificacion.getPublicacion().getCodigo() == p.getCodigo()) {
		        iterator.remove(); // Eliminar el elemento de forma segura
		        return Optional.of(notificacion);
		    }
		}
		
		return Optional.empty();
	}
	
	public int getEdad(Date fechaNacimiento) {
		Calendar fechaActual = Calendar.getInstance();
        Calendar fechaNac = Calendar.getInstance();
        fechaNac.setTime(fechaNacimiento);

        int edad = fechaActual.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);

        if (fechaActual.get(Calendar.MONTH) < fechaNac.get(Calendar.MONTH)) {
            edad--;
        } else if (fechaActual.get(Calendar.MONTH) == fechaNac.get(Calendar.MONTH)
                && fechaActual.get(Calendar.DAY_OF_MONTH) < fechaNac.get(Calendar.DAY_OF_MONTH)) {
            edad--;
        }

        return edad;
	}
	
	public int getTotalLikes() {
		int likes = 0;
		for (Foto foto : getFotos()) {
			likes += foto.getLikes();
		}
		
		return likes;
	}
	
	public double calcularDescuento(double precio) {
		return descuento.aplicarDescuento(this, precio);
	}
	
	public void makePremium() {
		this.setPremium(true);
	}
	
	public void cancelPremium() {
		this.setPremium(false);
	}
}





