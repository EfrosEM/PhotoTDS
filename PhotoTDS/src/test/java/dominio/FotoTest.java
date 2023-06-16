package dominio;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class FotoTest {

	private Foto foto;
	
	@Before
	public void setup() {
		
		foto = new Foto("ruta", "descripcion", 0, LocalDate.now());
	}
	
	@Test
	public void testAddLikes() {
		int resultado = 2;
		foto.addLike();
		foto.addLike();
		
		assertEquals(resultado, foto.getLikes());
	}
	
	@Test
	public void testHashtags() {
		assertTrue(foto.getHashtags().isEmpty());
	}
	
	@Test
	public void testDescripcion() {
		assertFalse(foto.getDescripcion().isEmpty());
	}

}
