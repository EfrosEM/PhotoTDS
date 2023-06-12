package dominio;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Test;

public class AlbumTest {

	@Test
	public void testAddFoto() {
		
		ArrayList<Foto> fotos = new ArrayList<Foto>();
		Foto foto = new Foto("ruta", "descripcion", 1, LocalDate.now());
		fotos.add(foto);
		
		Album album = new Album("titulo", LocalDate.now(), null);
		album.addFoto(foto);
		
		
		assertEquals(fotos, album.getFotos());
	}

}
