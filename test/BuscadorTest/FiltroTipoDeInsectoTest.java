package BuscadorTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*; 

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Buscador.Filtro;
import Buscador.FiltroTipoDeInsecto;
import ManejoDeUsuarios.Opinion;  
import muestras.Muestra;

class FiltroTipoDeInsectoTest {

	Filtro filtroTipoDeInsecto;
	Opinion EspecieBuscada; 
	List<Muestra> muestrasOriginales;
	List<Muestra> muestrasEsperadas;
	
	
	Muestra sordida;
	Muestra guasayana;
	Muestra infestans;
	
	@BeforeEach
	public void setUp() throws Exception {
		
		
		muestrasOriginales = new ArrayList<Muestra>();
		muestrasEsperadas = new ArrayList<Muestra>();
		
		sordida = mock(Muestra.class);
		guasayana = mock(Muestra.class);
		infestans = mock(Muestra.class);
		
		muestrasOriginales.add(sordida);
		muestrasOriginales.add(guasayana);
		muestrasOriginales.add(infestans);
		
		muestrasEsperadas.add(sordida); // 'sordida' es la única que debería pasar el filtro
		
		EspecieBuscada = Opinion.SORDIDA; 
		
		// SUT :
		filtroTipoDeInsecto = new FiltroTipoDeInsecto(EspecieBuscada);
		
		when(sordida.getEspecie()).thenReturn(Opinion.SORDIDA); 
		when(guasayana.getEspecie()).thenReturn(Opinion.GUASAYANA); 
		when(infestans.getEspecie()).thenReturn(Opinion.INFESTANS); 

	}
	
	@Test
	void filtrarTest() {
		
		List<Muestra> resultadoFiltro = filtroTipoDeInsecto.buscar(muestrasOriginales);
		
		assertNotNull(resultadoFiltro);
		assertEquals(muestrasEsperadas.size(), resultadoFiltro.size());
	    assertTrue(resultadoFiltro.containsAll(muestrasEsperadas));
	    assertEquals(sordida, resultadoFiltro.get(0));
	}

}









































