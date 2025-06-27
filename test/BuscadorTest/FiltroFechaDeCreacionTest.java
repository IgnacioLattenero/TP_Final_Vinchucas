package BuscadorTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Buscador.Filtro;
import Buscador.FiltroFechaDeCreacion;
import muestras.Muestra;

class FiltroFechaDeCreacionTest {

    Filtro filtroFechaDeCreacion;
    
    List<Muestra> muestrasOriginales;
    List<Muestra> muestrasEsperadas;
    
    LocalDateTime fechaBuscada;
    
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
        
        fechaBuscada = LocalDateTime.of(2025, 6, 20, 13, 0, 0);
        
        LocalDateTime fechaDiferente = LocalDateTime.of(2024, 1, 1, 10, 0, 0);
        
        // SUT:
        filtroFechaDeCreacion = new FiltroFechaDeCreacion(fechaBuscada);
        
        when(sordida.getFechaDeCreacion()).thenReturn(fechaBuscada); 
        
        when(guasayana.getFechaDeCreacion()).thenReturn(fechaDiferente);
        
        when(chinchefoliada.getFechaDeCreacion()).thenReturn(fechaDiferente);
    }
    
    @Test
    void filtrarTest() {
        
        List<Muestra> resultadoFiltro = filtroFechaDeCreacion.buscar(muestrasOriginales);
        
        assertEquals(muestrasEsperadas.size(), resultadoFiltro.size());
        assertTrue(resultadoFiltro.containsAll(muestrasEsperadas));
        assertEquals(sordida, resultadoFiltro.get(0));
    }
}





























