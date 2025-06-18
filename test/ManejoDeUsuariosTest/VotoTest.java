package ManejoDeUsuariosTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import ManejoDeUsuarios.Opinion;
import ManejoDeUsuarios.Usuario;
import ManejoDeUsuarios.Voto;

class VotoTest {

	Voto voto;
	Usuario usuario;
	Opinion opinion;
	
	@BeforeEach
	public void setUp() throws Exception {
		
		
		usuario = mock(Usuario.class);
		opinion = mock(Opinion.class);
		
		
		// SUT: Voto
		
		voto = new Voto(opinion, usuario, LocalDateTime.of(2025, 6, 1, 13, 0, 0));
		
		when(opinion.getMensajeAsociado()).thenReturn("Se ha identificado una vinchuca de especie: ");
	}

	@Test
	void votarTest() {
		
		assertEquals(opinion.getMensajeAsociado(), voto.votar());
	}
	
	@Test
	void antiguedadTest() {
		
		assertEquals(17, voto.antiguedadEnDias());
	}
	
	@Test
	void getVotanteTest() {
		
		assertEquals(usuario, voto.getVotante());
	}
	
	@Test
	void getFechaEmisionTest() {
		
		assertEquals(LocalDateTime.of(2025, 6, 1, 13, 0, 0), voto.getFechaEmision());
	}
}













































