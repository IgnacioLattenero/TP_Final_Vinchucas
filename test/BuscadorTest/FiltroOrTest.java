package BuscadorTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Buscador.FiltroAnd;
import Buscador.FiltroFechaDeCreacion;
import Buscador.FiltroOr;
import Buscador.FiltroTipoDeInsecto;
import muestras.Especie;
import muestras.Muestra;

class FiltroOrTest {

	FiltroTipoDeInsecto filtroTipoDeInsecto;
	FiltroFechaDeCreacion filtroFechaDeCreacion;
	FiltroOr filtroOr;
	
	List<Muestra> muestrasIniciales;
	List<Muestra> muestrasEsperadas;
	
	Muestra sordida;
	Muestra guasayana;
	Muestra infestans;
	Muestra infestans2;
	
	LocalDateTime fechaBuscada;
	LocalDateTime otraFecha;
	
	@BeforeEach
	public void setUp() throws Exception {
		
		filtroTipoDeInsecto = new FiltroTipoDeInsecto(Especie.SORDIDA);
		fechaBuscada = LocalDateTime.of(2025, 6, 20, 13, 0, 0);
		otraFecha = LocalDateTime.of(2025, 1, 1, 14, 0, 0);
		filtroFechaDeCreacion = new FiltroFechaDeCreacion(fechaBuscada);
		
		sordida = mock(Muestra.class);
		guasayana = mock(Muestra.class);
		infestans = mock(Muestra.class);
		infestans2 = mock(Muestra.class);
		
		muestrasIniciales = new ArrayList<Muestra>();
		
		muestrasIniciales.add(sordida);
		muestrasIniciales.add(guasayana);
		muestrasIniciales.add(infestans);
		muestrasIniciales.add(infestans2);
		
		muestrasEsperadas = new ArrayList<Muestra>();
		muestrasEsperadas.add(sordida);
		muestrasEsperadas.add(guasayana);

		filtroOr = new FiltroOr(filtroTipoDeInsecto, filtroFechaDeCreacion);
		
		when(sordida.getEspecie()).thenReturn(Especie.SORDIDA);
		when(guasayana.getEspecie()).thenReturn(Especie.GUASAYANA);
		when(infestans.getEspecie()).thenReturn(Especie.INFESTANS);
		when(infestans2.getEspecie()).thenReturn(Especie.INFESTANS);
		
		when(sordida.getFechaDeCreacion()).thenReturn(otraFecha);
		when(guasayana.getFechaDeCreacion()).thenReturn(fechaBuscada);
		when(infestans.getFechaDeCreacion()).thenReturn(otraFecha);
		when(infestans2.getFechaDeCreacion()).thenReturn(otraFecha);
		
	}
	
	
	@Test
	void filtrarTest() {
		
		 List<Muestra> resultadoFiltro = filtroOr.filtrar(muestrasIniciales);
	        
	     assertEquals(muestrasEsperadas.size(), resultadoFiltro.size());
	     assertTrue(resultadoFiltro.containsAll(muestrasEsperadas));
	     assertEquals(muestrasEsperadas, resultadoFiltro);
	}

}
