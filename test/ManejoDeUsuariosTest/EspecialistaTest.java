package ManejoDeUsuariosTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ManejoDeUsuarios.Especialista;
import ManejoDeUsuarios.Usuario;
import muestras.Muestra;

class EspecialistaTest {

	Especialista especialista;
	Usuario usuario;
	
	List<Muestra> muestras;
	
	
	@BeforeEach
	public void setUp() throws Exception {
		
		
		muestras = new ArrayList<Muestra>();
		
		especialista = new Especialista();
		
		usuario = mock(Usuario.class);
		
		when(usuario.getNivelDeUsuario()).thenReturn(especialista);
	
	}
	
	@Test
	void cambiarNivelDelUsuarioTest() {
		
		especialista.cambiarNivelDelUsuario(muestras, usuario);
		assertEquals(especialista, usuario.getNivelDeUsuario());
	}

}
