package dominio;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class FotoTest {

	@Test
	public void testAddLike() {
		
		Foto foto = new Foto("ruta", "descripcion", 0, LocalDate.now());
		
		int resultado = 2;
		foto.addLike();
		foto.addLike();
		
		assertEquals(resultado, foto.getLikes());
	}

}
