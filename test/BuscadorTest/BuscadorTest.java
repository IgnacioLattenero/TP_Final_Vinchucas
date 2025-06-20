package BuscadorTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import Buscador.Buscador;
import Buscador.Filtro;
import muestras.Muestra;

class BuscadorTest {

	Buscador buscador;
	Filtro filtroNivelDeVerificacion;
	Filtro fechaDeCreacion;
	Filtro fechaDeUltimaVotacion;
	Filtro tipoDeInsecto;
	Filtro filtroOr;
	Filtro filtroAnd;
	
	List<Muestra> muestras;
	Muestra muestra1;
	Muestra muestra2;
	Muestra muestra3;
	
	List<Muestra> muestrasEsperadas;
	
	@BeforeEach
	public void setUp() throws Exception {
		
		buscador = new Buscador();
		
		filtroNivelDeVerificacion = mock(Filtro.class);
		fechaDeCreacion = mock(Filtro.class);
		
		muestras = new ArrayList<Muestra>();
		
		muestras.add(muestra1);
		muestras.add(muestra2);
		muestras.add(muestra3);
		
		muestrasEsperadas = new ArrayList<Muestra>();
		
		muestrasEsperadas.add(muestra1);
	}
	
	@Test
	void agregarFiltroTest() {
		
		buscador.agregarFiltro(filtroNivelDeVerificacion);
		buscador.agregarFiltro(fechaDeCreacion);
		assertEquals(2, buscador.getFiltros().size());
	}
	
	@Test
	void eliminarFiltroTest() {
		
		buscador.eliminarFiltro(fechaDeCreacion);
		assertEquals(1, buscador.getFiltros().size());
	}
	
	@Test
	void buscarTest() {
		
		assertEquals(muestrasEsperadas, buscador.buscar(muestras)); // o esta
		
		verify(filtroNivelDeVerificacion, times(1)).filtrar(muestras); // o esta, porque es solo una delegacion
		
	}
	

}








































