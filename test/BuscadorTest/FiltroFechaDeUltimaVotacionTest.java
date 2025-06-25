package BuscadorTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;

import Buscador.Filtro;
import Buscador.FiltroFechaDeUltimaVotacion;
import ManejoDeUsuarios.Usuario;
import ManejoDeUsuarios.Voto;
import muestras.Muestra;

class FiltroFechaDeUltimaVotacionTest {

	Filtro filtroUltimaFechaDeVotacion;
	
	List<Muestra> muestrasOriginales;
	List<Muestra> muestrasEsperadas;
	List<Voto> votos1;
	List<Voto> votos2;
	List<Voto> votos3;
	
	LocalDateTime fechaBuscada;
	
	Muestra sordida;
	Muestra guasayana;
	Muestra chinchefoliada;
	
	Voto primerVoto;
	Voto segundoVoto;
	Voto tercerVoto;
	
	Usuario usuario1;
	Usuario usuario2;
	Usuario usuario3;
	
	
	@BeforeEach
	public void setUp() throws Exception {
		
		muestrasOriginales = new ArrayList<Muestra>();
		muestrasEsperadas = new ArrayList<Muestra>();
		votos1 = new ArrayList<Voto>();
		votos2 = new ArrayList<Voto>();
		votos3 = new ArrayList<Voto>();
		
		sordida = mock(Muestra.class);
		guasayana = mock(Muestra.class);
		chinchefoliada = mock(Muestra.class);
		
		primerVoto = mock(Voto.class);
		segundoVoto = mock(Voto.class);
		tercerVoto = mock(Voto.class);
		
		usuario1 = mock(Usuario.class);
		usuario2 = mock(Usuario.class);
		usuario3 = mock(Usuario.class);
		
		
		muestrasOriginales.add(sordida);
		muestrasOriginales.add(guasayana);
		muestrasOriginales.add(chinchefoliada);
		
		muestrasEsperadas.add(sordida);
		
		votos1.add(primerVoto);
		votos1.add(segundoVoto);
		votos1.add(tercerVoto);
		
		votos2.add(segundoVoto);
		
		votos3.add(tercerVoto);
		
		sordida.agregarVoto(primerVoto, votos1);
		guasayana.agregarVoto(segundoVoto, votos2);
		chinchefoliada.agregarVoto(tercerVoto, votos3);
		
		fechaBuscada = LocalDateTime.of(2025, 6, 20, 13, 0, 0);
	        
	    LocalDateTime fechaDiferente = LocalDateTime.of(2024, 1, 1, 10, 0, 0);
	        
	    // SUT:
	    filtroUltimaFechaDeVotacion = new FiltroFechaDeUltimaVotacion(fechaBuscada);
	        
	    when(primerVoto.getVotante()).thenReturn(usuario1);
	    when(segundoVoto.getVotante()).thenReturn(usuario2);
	    when(tercerVoto.getVotante()).thenReturn(usuario3);
	    
	    
	    
	    when(primerVoto.getFechaEmision()).thenReturn(fechaBuscada); 
	        
	    when(segundoVoto.getFechaEmision()).thenReturn(fechaDiferente);
	        
	    when(tercerVoto.getFechaEmision()).thenReturn(fechaDiferente);
	    
	    when(sordida.getVotos()).thenReturn(votos1);
	    when(guasayana.getVotos()).thenReturn(votos2);
	    when(chinchefoliada.getVotos()).thenReturn(votos3);
		
	}

	@Test
	void filtrarTest() {
		
		 List<Muestra> resultadoFiltro = filtroUltimaFechaDeVotacion.filtrar(muestrasOriginales);
		
		 assertEquals(muestrasEsperadas.size(), resultadoFiltro.size()); 
	     assertTrue(resultadoFiltro.containsAll(muestrasEsperadas));
	     assertEquals(sordida, resultadoFiltro.get(0));
	}

}








































































