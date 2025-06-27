// --------------- BasicoTest.java ---------------
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

class BasicoTest {

    Usuario usuario; // El usuario que estamos probando
    Usuario otroUsuario; // Para representar muestras no publicadas por el usuario principal
    Basico basicoSpy; // SUT
    
    // Las listas de muestras se crearán directamente en cada test para mayor control
    // List<Muestra> muestrasCompletas; 
    
    LocalDateTime fechaActualParaTest; 
    LocalDateTime fechaDeCreacionValida; 

    @BeforeEach
    public void setUp() throws Exception {
        usuario = mock(Usuario.class);
        otroUsuario = mock(Usuario.class); // Creamos un mock para un "otro" publicador
        basicoSpy = spy(new Basico()); 
        
        fechaActualParaTest = LocalDateTime.of(2025, 6, 26, 12, 0, 0); 
        fechaDeCreacionValida = fechaActualParaTest.minusDays(5); 

        when(usuario.getNivelDeUsuario()).thenReturn(basicoSpy); 
        
        // Mockear el getFechaActual() en el spy para todos los tests
        doReturn(fechaActualParaTest).when(basicoSpy).getFechaActual();
    }
    
    @Test
    void cambiarNivelDeUsuario_DeBasicoAExperto_CuandoCumpleCondiciones() {
        // Escenario: El usuario es Básico y cumple con los requisitos para ser Experto.

        List<Muestra> muestrasParaCumplir = new ArrayList<>();

        // 1. CONFIGURAR MUESTRAS PARA CUMPLIR CONDICIÓN DE ENVÍOS (> 10)
        // Necesitamos 11 muestras enviadas por 'usuario'
        for (int i = 0; i < 11; i++) { 
            Muestra m = mock(Muestra.class);
            when(m.getPublicador()).thenReturn(usuario); // <-- ESENCIAL: El publicador es el usuario que testeamos
            when(m.getFechaDeCreacion()).thenReturn(fechaDeCreacionValida);
            muestrasParaCumplir.add(m);
        }

        // 2. CONFIGURAR MUESTRAS PARA CUMPLIR CONDICIÓN DE REVISIONES (> 20)
        // Necesitamos 21 muestras votadas por 'usuario'. Pueden ser las mismas u otras.
        // Para asegurar que no haya colisiones de lógica, las creamos aparte.
        for (int i = 0; i < 21; i++) { 
            Muestra m = mock(Muestra.class);
            // Para la revisión, solo importa que 'haVotado' sea true y la fecha
            // El publicador de estas muestras no importa para la condición de revisiones,
            // pero para evitar NPE en el filtro de 'envios' SIEMPRE deben tener un publicador.
            when(m.getPublicador()).thenReturn(otroUsuario); // No es el usuario que publica, pero tiene un publicador
            when(m.haVotado(usuario)).thenReturn(true); 
            when(m.getFechaDeCreacion()).thenReturn(fechaDeCreacionValida);
            muestrasParaCumplir.add(m);
        }

        // ACT
        basicoSpy.cambiarNivelDelUsuario(muestrasParaCumplir, usuario);

        // ASSERT
        verify(usuario).setNivel(isA(Experto.class));
        verify(usuario, never()).setNivel(eq(basicoSpy)); 
    }

    @Test
    void cambiarNivelDeUsuario_BasicoNoCambia_SiNoCumpleCondiciones() {
        // Escenario: El usuario es Básico y NO cumple con los requisitos para ser Experto.

        List<Muestra> pocasMuestras = new ArrayList<>();
        // Ej: solo 5 envíos (menos de 11) y 5 revisiones (menos de 21)
        for (int i = 0; i < 5; i++) { 
            Muestra mEnvio = mock(Muestra.class);
            when(mEnvio.getPublicador()).thenReturn(usuario); // <-- Publicador es el usuario
            when(mEnvio.getFechaDeCreacion()).thenReturn(fechaDeCreacionValida);
            pocasMuestras.add(mEnvio);

            Muestra mRevision = mock(Muestra.class);
            when(mRevision.haVotado(usuario)).thenReturn(true);
            // El publicador de las muestras de revisión también debe estar mockeado.
            // Puede ser el mismo 'usuario' o 'otroUsuario'. Lo importante es que no sea null.
            when(mRevision.getPublicador()).thenReturn(otroUsuario); 
            when(mRevision.getFechaDeCreacion()).thenReturn(fechaDeCreacionValida);
            pocasMuestras.add(mRevision);
        }

        // ACT
        basicoSpy.cambiarNivelDelUsuario(pocasMuestras, usuario);

        // ASSERT
        verify(usuario, never()).setNivel(any());
    }
    
    
    @Test
    void getFechaActualTest() {
    	
    	assertEquals(fechaActualParaTest, basicoSpy.getFechaActual());
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}