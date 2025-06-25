package BuscadorTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Buscador.Filtro;
import Buscador.FiltroAnd;
import Buscador.FiltroFechaDeCreacion;
import Buscador.FiltroFechaDeUltimaVotacion;
import Buscador.FiltroTipoDeInsecto;
import muestras.Especie;
import muestras.Muestra;

class FiltroAndTest {

	FiltroTipoDeInsecto filtroTipoDeInsecto;
	FiltroFechaDeCreacion filtroFechaDeCreacion;
	FiltroAnd filtroAnd;
	
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

		filtroAnd = new FiltroAnd(filtroTipoDeInsecto, filtroFechaDeCreacion);
		
		when(sordida.getEspecie()).thenReturn(Especie.SORDIDA);
		when(guasayana.getEspecie()).thenReturn(Especie.GUASAYANA);
		when(infestans.getEspecie()).thenReturn(Especie.INFESTANS);
		when(infestans2.getEspecie()).thenReturn(Especie.INFESTANS);
		
		when(sordida.getFechaDeCreacion()).thenReturn(fechaBuscada);
		when(guasayana.getFechaDeCreacion()).thenReturn(otraFecha);
		when(infestans.getFechaDeCreacion()).thenReturn(otraFecha);
		when(infestans2.getFechaDeCreacion()).thenReturn(otraFecha);
	}
	
	@Test
	void filtrarTest() {
		
		 List<Muestra> resultadoFiltro = filtroAnd.filtrar(muestrasIniciales);
	        
	     assertEquals(muestrasEsperadas.size(), resultadoFiltro.size());
	     assertTrue(resultadoFiltro.containsAll(muestrasEsperadas));
	     assertEquals(sordida, resultadoFiltro.get(0));
	}

}















































