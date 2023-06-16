package dominio;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class AlbumTest {
	
	private Usuario u1;
	private Usuario u2;
	private Foto foto;
	private Album album;
	private Notificacion notificacion;

	@Before
	public void setup() {
		
		u1 = new Usuario("user", "user1", "1", "user1@um.es", "test", null, "Usuario 1 test", "rutaFotoPerfil");
		u2 = new Usuario("user", "user2", "2", "user2@um.es", "test", null, "Usuario 2 test", "rutaFotoPerfil");
		
		foto = new Foto("ruta", "descripcion", 1, LocalDate.now());
		//fotos.add(foto);
		
		album = new Album("titulo", LocalDate.now());
		album.addFoto(foto);
		
		notificacion = new Notificacion(u1, foto);
		u2.addNotificacion(notificacion);
		
	}
	
	@Test
	public void testAlbumNotNull() {
		assertNotNull(album);
	}
	
	@Test
	public void testAddPhoto() {
		ArrayList<Foto> fotos = new ArrayList<Foto>();
		fotos.add(foto);
		
		assertEquals(fotos, album.getFotos());
	}
	
	@Test
	public void testNotification() {
		ArrayList<Notificacion> notificaciones = new ArrayList<Notificacion>();
		notificaciones.add(notificacion);
		
		assertEquals(u2.getNotificaciones(), notificaciones);
	}
	

}
