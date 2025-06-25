package BuscadorTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Buscador.Filtro;
import Buscador.FiltroNivelDeVerificacion;
import muestras.AbiertaATodaOpinion;
import muestras.Muestra;
import muestras.Verificada;
import muestras.VotanSoloExpertos;

class FiltroNivelDeVerificacionTest {

	 Filtro filtroNivelDeVerificacion;
	 Verificada verificada;
	 AbiertaATodaOpinion abierta;
	 VotanSoloExpertos votanExpertos;
	 
	 List<Muestra> muestrasOriginales;
	 List<Muestra> muestrasEsperadas;
				
	 Muestra sordida;
	 Muestra guasayana;
	 Muestra chinchefoliada;
	 
	
	
	@BeforeEach
	public void setUp() throws Exception {
		
		muestrasOriginales = new ArrayList<Muestra>();
	    muestrasEsperadas = new ArrayList<Muestra>();
		
		verificada = mock(Verificada.class);
		abierta = mock(AbiertaATodaOpinion.class);
		votanExpertos = mock(VotanSoloExpertos.class);
		
		sordida = mock(Muestra.class);
		guasayana = mock(Muestra.class);
		chinchefoliada = mock(Muestra.class);
		
		muestrasOriginales.add(sordida);
		muestrasOriginales.add(guasayana);
		muestrasOriginales.add(chinchefoliada);
		
		muestrasEsperadas.add(sordida);
		
		// SUT:
		filtroNivelDeVerificacion = new FiltroNivelDeVerificacion(verificada);
		
		when(sordida.getEstado()).thenReturn(verificada);
		when(guasayana.getEstado()).thenReturn(abierta);
		when(chinchefoliada.getEstado()).thenReturn(votanExpertos);
		
	}
	
	@Test
	void filtrarTest() {
		
		 List<Muestra> resultadoFiltro = filtroNivelDeVerificacion.filtrar(muestrasOriginales);
	        
	     assertEquals(muestrasEsperadas.size(), resultadoFiltro.size());
	     assertTrue(resultadoFiltro.containsAll(muestrasEsperadas));
	     assertEquals(sordida, resultadoFiltro.get(0));
	}

}






























































