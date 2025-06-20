package BuscadorTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import Buscador.Filtro;
import Buscador.FiltroTipoDeInsecto;
import ManejoDeUsuarios.Opinion;
import muestras.Muestra;

class FiltroTipoDeInsectoTest {

	Filtro filtroTipoDeInsecto;
	Opinion opinion;
	List<Muestra> muestrasOriginales;
	List<Muestra> muestrasEsperadas;
	
	
	Muestra sordida;
	Muestra guasayana;
	Muestra chinchefoliada;
	
	@BeforeEach
	public void setUp() throws Exception {
		
		
		muestrasOriginales = new ArrayList<Muestra>();
		muestrasEsperadas = new ArrayList<Muestra>();
		
		sordida = mock(Muestra.class);
		guasayana = mock(Muestra.class);
		chinchefoliada = mock(Muestra.class);
		
		muestrasOriginales.add(sordida);
		muestrasOriginales.add(guasayana);
		muestrasOriginales.add(chinchefoliada);
		
		muestrasEsperadas.add(sordida);
		
		opinion = opinion.SORDIDA;
		// SUT:
		filtroTipoDeInsecto = new FiltroTipoDeInsecto(opinion);
		
		
	}
	
	@Test
	void filtrarTest() {
		
		assertEquals(muestrasEsperadas, filtroTipoDeInsecto.filtrar(muestrasOriginales));
	}

}






















































