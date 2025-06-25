package BuscadorTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import Buscador.Buscador;
import Buscador.Filtro;
import Buscador.FiltroAnd;
import Buscador.FiltroFechaDeCreacion;
import Buscador.FiltroFechaDeUltimaVotacion;
import Buscador.FiltroNivelDeVerificacion;
import Buscador.FiltroOr;
import Buscador.FiltroTipoDeInsecto;
import ManejoDeUsuarios.Opinion;
import muestras.Especie;
import muestras.EstadoDeMuestra;
import muestras.Muestra;
import muestras.Verificada;

class BuscadorTest {

    Buscador buscador;
    FiltroNivelDeVerificacion filtroNivelDeVerificacion;
    FiltroFechaDeCreacion filtroFechaDeCreacion;
    FiltroFechaDeUltimaVotacion filtroFechaDeUltimaVotacion;
    FiltroTipoDeInsecto filtroTipoDeInsecto;
    FiltroOr filtroOr;
    FiltroAnd filtroAnd;
    
    List<Muestra> muestras;
    Muestra muestra1;
    Muestra muestra2;
    Muestra muestra3;
    Muestra muestra4;
    
    EstadoDeMuestra estado;
    LocalDateTime fechaCreacion;
    
    @BeforeEach
    public void setUp() throws Exception {
        
        buscador = new Buscador(); // SUT: El buscador se inicializa sin filtros
        
        filtroNivelDeVerificacion = mock(FiltroNivelDeVerificacion.class);
        filtroFechaDeCreacion = mock(FiltroFechaDeCreacion.class);
        filtroFechaDeUltimaVotacion = mock(FiltroFechaDeUltimaVotacion.class);
        filtroTipoDeInsecto = mock(FiltroTipoDeInsecto.class);
        filtroOr = mock(FiltroOr.class);
        filtroAnd = mock(FiltroAnd.class);
        
        muestra1 = mock(Muestra.class);
        muestra2 = mock(Muestra.class);
        muestra3 = mock(Muestra.class);
        muestra4 = mock(Muestra.class);
        
        muestras = new ArrayList<Muestra>();
        
        muestras.add(muestra1);
        muestras.add(muestra2);
        muestras.add(muestra3);
        muestras.add(muestra4);
        
        estado = mock(EstadoDeMuestra.class); 
        fechaCreacion = LocalDateTime.of(2025, 6, 15, 14, 0, 0);        
        
    }
    
    @Test
    void agregarFiltroTest() {
       
        buscador.agregarFiltro(filtroNivelDeVerificacion);
        
        assertEquals(1, buscador.getFiltros().size());
    }
    
    @Test
    void eliminarFiltroTest() {
        
        buscador.agregarFiltro(filtroNivelDeVerificacion);
        buscador.eliminarFiltro(filtroNivelDeVerificacion);
        
        assertEquals(0, buscador.getFiltros().size());
    }
    
    @Test
    void buscarSinFiltrosRetornaTodasLasMuestrasInicialesTest() {
        /**
         * pruebo el caso sin filtros, probando el if y el return sin el else, 
         * El buscador comienza sin filtros, le paso una lista de muestras, y debe 
         * retornar la misma lista sin cambios, luego, verifica que esten todas las muestras
         * que le fueron agregadas, y verifica el total.
         * */
    	
        List<Muestra> resultado = buscador.buscar(muestras);
        
        assertEquals(muestras.size(), resultado.size());
        assertTrue(resultado.containsAll(muestras));
        assertEquals(4, resultado.size()); 
    }

    @Test
    void buscarConFiltrosAplicaTodosLosFiltrosCorrectamenteTest() {
    	
    	/**
    	 * Cada filtro toma la salida del filtro anterior, primero agrego los filtros
    	 * al buscador, configuro los resultados esperados en los when, hago que 
    	 * muestra 1 pase los filtros 1 y 2, pero no pase el tercero, para que la 
    	 * lista al final sea vacia. Lo mas importante es que cada filto opera
    	 * con el resultado del filtro anterior.
    	 * 
    	 * */
        
        buscador.agregarFiltro(filtroNivelDeVerificacion);
        buscador.agregarFiltro(filtroFechaDeCreacion);
        buscador.agregarFiltro(filtroTipoDeInsecto); 
    
        List<Muestra> resultadoFiltro1 = new ArrayList<>();
        resultadoFiltro1.add(muestra1);
        when(filtroNivelDeVerificacion.filtrar(muestras)).thenReturn(resultadoFiltro1);
    
        List<Muestra> resultadoFiltro2 = new ArrayList<>();
        resultadoFiltro2.add(muestra1);
        when(filtroFechaDeCreacion.filtrar(resultadoFiltro1)).thenReturn(resultadoFiltro2); 
        List<Muestra> resultadoFiltro3 = new ArrayList<>(); 
        when(filtroTipoDeInsecto.filtrar(resultadoFiltro2)).thenReturn(resultadoFiltro3); 
    
        List<Muestra> resultadoBusqueda = buscador.buscar(muestras);
    
        verify(filtroNivelDeVerificacion, times(1)).filtrar(muestras);
        verify(filtroFechaDeCreacion, times(1)).filtrar(resultadoFiltro1); 
        verify(filtroTipoDeInsecto, times(1)).filtrar(resultadoFiltro2); 
    
        assertTrue(resultadoBusqueda.isEmpty());
        assertEquals(0, resultadoBusqueda.size());
    }
    
    @Test
    void buscarConFiltrosRetornaUnaMuestraCuandoSoloUnaPasaTodosLosFiltrosTest() {
    	
    	/**
    	 * una muestra pasa todos los filtros, se agregan los filtros, se configura 
    	 * a los filtros para que solo los pase la muestra 1, se verifica que se 
    	 * encuentra a la muestra1 al final y se verifican las llamadas a los mocks.
    	 * */
        
        buscador.agregarFiltro(filtroNivelDeVerificacion);
        buscador.agregarFiltro(filtroFechaDeCreacion);
        buscador.agregarFiltro(filtroTipoDeInsecto);
    
     
        List<Muestra> listaConMuestra1 = new ArrayList<>();
        listaConMuestra1.add(muestra1);
    
        when(filtroNivelDeVerificacion.filtrar(muestras)).thenReturn(listaConMuestra1);
    
        when(filtroFechaDeCreacion.filtrar(listaConMuestra1)).thenReturn(listaConMuestra1);
    
        when(filtroTipoDeInsecto.filtrar(listaConMuestra1)).thenReturn(listaConMuestra1);
    
        List<Muestra> resultadoBusqueda = buscador.buscar(muestras);
    
        assertFalse(resultadoBusqueda.isEmpty());
        assertEquals(1, resultadoBusqueda.size());
        assertEquals(muestra1, resultadoBusqueda.get(0));
    
        verify(filtroNivelDeVerificacion, times(1)).filtrar(muestras);
        verify(filtroFechaDeCreacion, times(1)).filtrar(listaConMuestra1);
        verify(filtroTipoDeInsecto, times(1)).filtrar(listaConMuestra1);
    }
    
}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			