// --------------- ExpertoTest.java ---------------
package ManejoDeUsuariosTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ManejoDeUsuarios.Basico;
import ManejoDeUsuarios.Experto; 
import ManejoDeUsuarios.Usuario;
import muestras.Muestra;

class ExpertoTest {

    Usuario usuario;
    Usuario otroUsuario; // Para representar publicadores/votantes que no son el usuario SUT
    Experto expertoSpy; // SUT
    
    // La lista de muestras se creará directamente en cada test
    // List<Muestra> muestras; 
    
    LocalDateTime fechaActualParaTest;
    LocalDateTime fechaDeCreacionValida;

    @BeforeEach
    public void setUp() throws Exception {
        usuario = mock(Usuario.class);
        otroUsuario = mock(Usuario.class); // Para muestras no relacionadas al usuario principal
        expertoSpy = spy(new Experto()); 
        
        fechaActualParaTest = LocalDateTime.of(2025, 6, 26, 12, 0, 0); 
        fechaDeCreacionValida = fechaActualParaTest.minusDays(5); 

        when(usuario.getNivelDeUsuario()).thenReturn(expertoSpy); 

        // Mockear el getFechaActual() en el spy para todos los tests
        doReturn(fechaActualParaTest).when(expertoSpy).getFechaActual();
    }
    
    @Test
    void cambiarNivelDeUsuario_DeExpertoABasico_CuandoNoCumpleCondiciones() {
        // Escenario: El usuario es Experto y NO cumple con los requisitos para mantener el nivel.

        List<Muestra> muestrasParaDegradar = new ArrayList<>();

        // CONFIGURAR MUESTRAS PARA NO CUMPLIR ENVÍOS (menos de 11)
        for (int i = 0; i < 5; i++) { // Solo 5 envíos
            Muestra m = mock(Muestra.class);
            when(m.getPublicador()).thenReturn(usuario); // <-- Publicador es el usuario
            when(m.getFechaDeCreacion()).thenReturn(fechaDeCreacionValida);
            muestrasParaDegradar.add(m);
        }
        // CONFIGURAR MUESTRAS PARA CUMPLIR REVISIONES (más de 20)
        // (Esto es para que el OR en el if de Experto se cumpla con la primera condición falsa)
        for (int i = 0; i < 21; i++) { 
            Muestra m = mock(Muestra.class);
            when(m.haVotado(usuario)).thenReturn(true);
            // Publicador también debe estar mockeado
            when(m.getPublicador()).thenReturn(otroUsuario); 
            when(m.getFechaDeCreacion()).thenReturn(fechaDeCreacionValida);
            muestrasParaDegradar.add(m);
        }

        // ACT
        expertoSpy.cambiarNivelDelUsuario(muestrasParaDegradar, usuario);

        // ASSERT
        verify(usuario).setNivel(isA(Basico.class));
        verify(usuario, never()).setNivel(eq(expertoSpy));
    }

    @Test
    void cambiarNivelDeUsuario_ExpertoNoCambia_SiCumpleCondiciones() {
        // Escenario: El usuario es Experto y SÍ cumple con los requisitos para MANTENER el nivel.

        List<Muestra> muestrasParaMantener = new ArrayList<>();

        // CONFIGURAR MUESTRAS PARA CUMPLIR AMBAS CONDICIONES
        for (int i = 0; i < 11; i++) { // 11 envíos
            Muestra m = mock(Muestra.class);
            when(m.getPublicador()).thenReturn(usuario); // <-- Publicador es el usuario
            when(m.getFechaDeCreacion()).thenReturn(fechaDeCreacionValida);
            muestrasParaMantener.add(m);
        }
        for (int i = 0; i < 21; i++) { // 21 revisiones
            Muestra m = mock(Muestra.class);
            when(m.haVotado(usuario)).thenReturn(true);
            when(m.getPublicador()).thenReturn(otroUsuario); // Publicador también debe estar mockeado
            when(m.getFechaDeCreacion()).thenReturn(fechaDeCreacionValida);
            muestrasParaMantener.add(m);
        }

        // ACT
        expertoSpy.cambiarNivelDelUsuario(muestrasParaMantener, usuario);

        // ASSERT
        verify(usuario, never()).setNivel(any());
    }
    
    @Test
    void getFechaActualTest() {
    	
    	assertEquals(fechaActualParaTest, expertoSpy.getFechaActual());
    }
    
    
    
    
}