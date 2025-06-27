package BuscadorTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections; // Asegúrate de tenerlo importado en la clase Filtro
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Buscador.FiltroLaUltimaVotacionOcurrioEnLaFechaDada;
import ManejoDeUsuarios.Usuario;
import ManejoDeUsuarios.Voto;
import muestras.Muestra;

class FiltroLaUltimaVotacionOcurrioEnLaFechaDadaTest {

	FiltroLaUltimaVotacionOcurrioEnLaFechaDada filtroUltimaFechaDeVotacion; // Tipo concreto
	
	List<Muestra> muestrasOriginales;
	
	// Fechas de referencia
	LocalDateTime fechaBuscada; // 2025-06-20
	LocalDateTime fechaAnterior; // 2024-01-01
	LocalDateTime fechaPosterior; // 2025-07-01 (para asegurar que 'fechaBuscada' es la última en algunos casos)

	// Mocks de Muestra
	Muestra muestraConVotoEnFechaBuscada;
	Muestra muestraConVotoAnterior;
	Muestra muestraConVotoPosterior; // Si se necesita para otros escenarios
	Muestra muestraSinVotos; // Para el caso de borde
	Muestra muestraConMultiplesVotosVariados; // Para probar que selecciona la última
	
	// Mocks de Voto y Usuario (simplemente para que no sean null)
	Voto voto1;
	Voto voto2;
	Voto voto3;
	Voto voto4; // Nuevo voto para caso múltiple
	
	Usuario usuarioMock; // Un mock genérico de usuario para votos
	
	
	@BeforeEach
	public void setUp() throws Exception {
		
		muestrasOriginales = new ArrayList<>();
		
		// Definición de fechas
		fechaBuscada = LocalDateTime.of(2025, 6, 20, 13, 0, 0);
	    fechaAnterior = LocalDateTime.of(2024, 1, 1, 10, 0, 0);
	    fechaPosterior = LocalDateTime.of(2025, 7, 1, 15, 0, 0); // Una fecha posterior a la buscada
	        
	    // Instancia del SUT
	    filtroUltimaFechaDeVotacion = new FiltroLaUltimaVotacionOcurrioEnLaFechaDada(fechaBuscada);
	    
	    // Mocks genéricos de Usuario para votos (no se usan directamente en el filtro, pero los votos los necesitan)
	    usuarioMock = mock(Usuario.class);
	    
	    // Mocks de Voto
	    voto1 = mock(Voto.class);
	    voto2 = mock(Voto.class);
	    voto3 = mock(Voto.class);
	    voto4 = mock(Voto.class);

	    // Configuración de los mocks de Voto
	    when(voto1.getVotante()).thenReturn(usuarioMock);
	    when(voto1.getFechaEmision()).thenReturn(fechaBuscada); 
	        
	    when(voto2.getVotante()).thenReturn(usuarioMock);
	    when(voto2.getFechaEmision()).thenReturn(fechaAnterior); // Una fecha anterior a la buscada
	        
	    when(voto3.getVotante()).thenReturn(usuarioMock);
	    when(voto3.getFechaEmision()).thenReturn(fechaPosterior); // Una fecha posterior a la buscada

	    when(voto4.getVotante()).thenReturn(usuarioMock);
	    when(voto4.getFechaEmision()).thenReturn(fechaBuscada); // Otro voto en la fecha buscada
	    
	    // Mocks de Muestra
	    muestraConVotoEnFechaBuscada = mock(Muestra.class);
	    muestraConVotoAnterior = mock(Muestra.class);
	    muestraConVotoPosterior = mock(Muestra.class);
	    muestraSinVotos = mock(Muestra.class);
	    muestraConMultiplesVotosVariados = mock(Muestra.class);
	    
	    // Configuración de los mocks de Muestra
	    // Muestra con su última votación en la fecha buscada
	    when(muestraConVotoEnFechaBuscada.getVotos()).thenReturn(List.of(voto2, voto1)); // voto1 (fechaBuscada) es el más reciente
	    
	    // Muestra con su última votación anterior a la fecha buscada
	    when(muestraConVotoAnterior.getVotos()).thenReturn(List.of(voto2)); // solo voto2 (fechaAnterior)
	    
	    // Muestra con su última votación posterior a la fecha buscada
	    when(muestraConVotoPosterior.getVotos()).thenReturn(List.of(voto1, voto3)); // voto3 (fechaPosterior) es el más reciente
	    
	    // Muestra sin votos (debe devolver una lista vacía para evitar NPE en el filtro)
	    when(muestraSinVotos.getVotos()).thenReturn(new ArrayList<>()); 

	    // Muestra con múltiples votos para verificar que siempre toma la última fecha
	    when(muestraConMultiplesVotosVariados.getVotos()).thenReturn(List.of(voto2, voto4, voto1)); // voto1 o voto4 (fechaBuscada) son los más recientes
	    
	    // Agregar todas las muestras a la lista original para el test principal si es necesario
	    muestrasOriginales.add(muestraConVotoEnFechaBuscada);
	    muestrasOriginales.add(muestraConVotoAnterior);
	    muestrasOriginales.add(muestraConVotoPosterior);
	    muestrasOriginales.add(muestraSinVotos);
	    muestrasOriginales.add(muestraConMultiplesVotosVariados);

	}

	@Test
	void filtrarTest() {
		// La muestra esperada es aquella cuya última votación sea exactamente 'fechaBuscada'
		List<Muestra> muestrasEsperadas = List.of(muestraConVotoEnFechaBuscada, muestraConMultiplesVotosVariados);
		
		List<Muestra> resultadoFiltro = filtroUltimaFechaDeVotacion.buscar(muestrasOriginales);
		
		// Verificamos que el tamaño del resultado sea el esperado
		assertEquals(muestrasEsperadas.size(), resultadoFiltro.size(), 
			"El tamaño del resultado del filtro no es el esperado."); 
	    
		// Verificamos que el resultado contenga todas las muestras esperadas (ignorando el orden)
	    assertTrue(resultadoFiltro.containsAll(muestrasEsperadas), 
	    	"El resultado del filtro debe contener todas las muestras esperadas.");
	    
	    // Verificamos que las muestras esperadas estén contenidas en el resultado (ignorando el orden)
	    assertTrue(muestrasEsperadas.containsAll(resultadoFiltro),
	    	"Las muestras en el resultado deben ser solo las esperadas.");
	}

	@Test
	void pasaFiltro_RetornaTrue_CuandoUltimaVotacionEsLaFechaBuscada() {
		assertTrue(filtroUltimaFechaDeVotacion.pasaFiltro(muestraConVotoEnFechaBuscada),
			"Debería pasar el filtro si la última votación coincide con la fecha buscada.");
	}

	@Test
	void pasaFiltro_RetornaTrue_CuandoMultiplesVotosLaUltimaEsLaFechaBuscada() {
		assertTrue(filtroUltimaFechaDeVotacion.pasaFiltro(muestraConMultiplesVotosVariados),
			"Debería pasar el filtro si, con múltiples votos, el último coincide con la fecha buscada.");
	}

	@Test
	void pasaFiltro_RetornaFalse_CuandoUltimaVotacionEsAnterior() {
		assertFalse(filtroUltimaFechaDeVotacion.pasaFiltro(muestraConVotoAnterior),
			"No debería pasar el filtro si la última votación es anterior a la fecha buscada.");
	}

	@Test
	void pasaFiltro_RetornaFalse_CuandoUltimaVotacionEsPosterior() {
		assertFalse(filtroUltimaFechaDeVotacion.pasaFiltro(muestraConVotoPosterior),
			"No debería pasar el filtro si la última votación es posterior a la fecha buscada.");
	}

	@Test
	void pasaFiltro_RetornaFalse_CuandoMuestraNoTieneVotos() {
		// En este caso, si getVotos() devuelve una lista vacía, el filtro lanzaría una excepción
		// porque Collections.sort(fechas) en una lista vacía no es un problema, pero fechas.get(fechas.size() - 1) sí.
		// Debemos manejar este caso en la implementación del filtro si puede ocurrir.
		assertFalse(filtroUltimaFechaDeVotacion.pasaFiltro(muestraSinVotos),
			"No debería pasar el filtro si la muestra no tiene votos.");
	}
}