package dominio;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class UsuarioTest {
	
	private Usuario u1, u2;

	@Before
	public void setup() {
		u1 = new Usuario("user", "user1", "1", "user1@um.es", "test", null, "Usuario 1 test", "rutaFotoPerfil");
		u2 = new Usuario("user", "user2", "2", "user2@um.es", "test", null, "Usuario 2 test", "rutaFotoPerfil");
	}
	
	@Test
	public void testPremium() {
		u1.makePremium();
		assertTrue(u1.isPremium());
	}
	
	@Test
	public void testSeguidor() {
		List<Usuario> seguidores = new ArrayList<>();
		seguidores.add(u2);
		u1.addSeguidor(u2);
		u2.addSeguidos();
		
		assertEquals(u1.getSeguidores().size(), 1);
		assertEquals(u1.getSeguidores(), seguidores);
		assertEquals(u2.getSeguidos(), 1);
	}
	
	@Test
	public void testFullName() {
		assertEquals(u1.getNombre() + u1.getApellidos(), "user" + "1");
	}
	
	@Test
	public void testUsername() {
		assertEquals(u2.getUsuario(), "user2");
	}
	
	@Test
	public void testEmail() {
		assertEquals(u1.getEmail(), "user1@um.es");
	}

}
