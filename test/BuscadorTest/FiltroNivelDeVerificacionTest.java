package BuscadorTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Buscador.Filtro;
import Buscador.FiltroNivelDeVerificacion;
import muestras.Muestra;
import muestras.Verificada;

class FiltroNivelDeVerificacionTest {

	 Filtro filtroNivelDeVerificacion;
	 Verificada verificada;
	 
	 List<Muestra> muestrasOriginales;
	 List<Muestra> muestrasEsperadas;
				
	 Muestra sordida;
	 Muestra guasayana;
	 Muestra chinchefoliada;
	
	@BeforeEach
	public void setUp() throws Exception {
		
		
		verificada = mock(Verificada.class);
		
		sordida = mock(Muestra.class);
		guasayana = mock(Muestra.class);
		chinchefoliada = mock(Muestra.class);
		
		muestrasOriginales.add(sordida);
		muestrasOriginales.add(guasayana);
		muestrasOriginales.add(chinchefoliada);
		
		muestrasEsperadas.add(sordida);
		
		
		// SUT:
		filtroNivelDeVerificacion = new FiltroNivelDeVerificacion(verificada);
		
	}
	
	@Test
	void filtrarTest() {
		
		assertEquals(muestrasEsperadas, filtroNivelDeVerificacion.filtrar(muestrasOriginales));
	}

}






























































