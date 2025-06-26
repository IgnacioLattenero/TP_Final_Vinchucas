package ManejoDeUsuariosTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ManejoDeUsuarios.Opinion;
import ManejoDeUsuarios.Usuario;
import ManejoDeUsuarios.NivelDeUsuario;
import ManejoDeUsuarios.Voto;

class VotoTest {

	Voto voto;
	Usuario usuario;
	Opinion opinion;
	NivelDeUsuario nivelDelVotante; // Para mockear el nivel del usuario
	
	// Definimos algunas fechas fijas para nuestros tests
	private final LocalDateTime FECHA_VOTO_SENCILLA = LocalDateTime.of(2025, 6, 15, 10, 0, 0);
	private final LocalDateTime FECHA_UN_DIA_DESPUES = LocalDateTime.of(2025, 6, 16, 10, 0, 0);
	private final LocalDateTime FECHA_CINCO_DIAS_DESPUES = LocalDateTime.of(2025, 6, 20, 10, 0, 0);
	private final LocalDateTime FECHA_DIEZ_DIAS_DESPUES = LocalDateTime.of(2025, 6, 25, 10, 0, 0);
	private final LocalDateTime FECHA_UN_DIA_ANTES = LocalDateTime.of(2025, 6, 14, 10, 0, 0); // Para caso borde

	@BeforeEach
	public void setUp() throws Exception {
		usuario = mock(Usuario.class);
		opinion = mock(Opinion.class);
		nivelDelVotante = mock(NivelDeUsuario.class); 
		
		when(usuario.getNivelDeUsuario()).thenReturn(nivelDelVotante); // Comportamiento para el constructor de Voto
	}
	
	@Test
	void getVotanteTest() {
		// Creamos el voto para este test específico
		voto = new Voto(opinion, usuario, FECHA_VOTO_SENCILLA); 
		assertEquals(usuario, voto.getVotante());
	}
	
	@Test
	void getFechaEmisionTest() {
		// Creamos el voto con una fecha de emisión específica y la verificamos
		voto = new Voto(opinion, usuario, FECHA_VOTO_SENCILLA); 
		assertEquals(FECHA_VOTO_SENCILLA, voto.getFechaEmision());
	}
	
	@Test
	void antiguedadEnDiasTest_ceroDias() {
		// El voto se emite el 15 y el "día de hoy" también es el 15
		voto = new Voto(opinion, usuario, FECHA_VOTO_SENCILLA);
		assertEquals(0, voto.antiguedadEnDias(FECHA_VOTO_SENCILLA));
	}
	
	@Test
	void antiguedadEnDiasTest_unDia() {
		// El voto se emite el 15 y el "día de hoy" es el 16
		voto = new Voto(opinion, usuario, FECHA_VOTO_SENCILLA);
		assertEquals(1, voto.antiguedadEnDias(FECHA_UN_DIA_DESPUES));
	}
	
	@Test
	void antiguedadEnDiasTest_cincoDias() {
		// El voto se emite el 15 y el "día de hoy" es el 20
		voto = new Voto(opinion, usuario, FECHA_VOTO_SENCILLA);
		assertEquals(5, voto.antiguedadEnDias(FECHA_CINCO_DIAS_DESPUES));
	}

	@Test
	void antiguedadEnDiasTest_diezDias() {
		// El voto se emite el 15 y el "día de hoy" es el 25
		voto = new Voto(opinion, usuario, FECHA_VOTO_SENCILLA);
		assertEquals(10, voto.antiguedadEnDias(FECHA_DIEZ_DIAS_DESPUES));
	}
	
	@Test
	void antiguedadEnDiasTest_fechaFutura() {
		// Si la fecha de "hoy" es anterior a la fecha de emisión (no debería pasar normalmente)
		// tu método devuelve 0 gracias a Math.max.
		voto = new Voto(opinion, usuario, FECHA_VOTO_SENCILLA);
		assertEquals(0, voto.antiguedadEnDias(FECHA_UN_DIA_ANTES));
	}
	
	// Test para el constructor original (sin pasar fecha) y el antiguedadEnDias() sin argumentos
	@Test
	void antiguedadEnDiasTest_constructorYMetodoOriginales() {
		// Este test es para verificar que el constructor original y el método antiguedadEnDias() sin argumentos
		// usan LocalDateTime.now() en el momento de la ejecución.
		// Debido a la naturaleza de LocalDateTime.now(), este test NO es determinístico al 100% en el valor exacto de días,
		// pero sí podemos verificar que no sea negativo y que la fecha de emisión no sea null.
		
		// Dado que el cálculo es sobre el día, es MUY probable que dé 0 si el test corre el mismo día.
		// Si se ejecutara en un día diferente al que se compila/deploya, podría dar 1, etc.
		
		Voto votoDefault = new Voto(opinion, usuario); // Se crea con LocalDateTime.now()
		assertNotNull(votoDefault.getFechaEmision()); // Aseguramos que la fecha se setee
		
		// La antigüedad debería ser 0 si se ejecuta en el mismo día, o 1 si pasa la medianoche.
		// Es el test menos robusto, pero verifica el comportamiento por defecto.
		assertTrue(votoDefault.antiguedadEnDias() >= 0); // Siempre debe ser no-negativo
		
		// Opcional: si la ejecución es muy rápida, podemos asumir 0 días.
		assertEquals(0, votoDefault.antiguedadEnDias()); 
	}
}






















