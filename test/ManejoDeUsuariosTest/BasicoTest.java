package ManejoDeUsuariosTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

	
	Usuario usuario;
	Basico basico;
	Experto experto;
	
	List<Muestra> muestras;
	
	Muestra sordida;
	Muestra guasayana;
	Muestra infestans;
	Muestra sordida2;
	Muestra guasayana2;
	Muestra infestans2;
	Muestra sordida3;
	Muestra guasayana3;
	Muestra infestans3;
	Muestra sordida4;
	Muestra guasayana4;
	Muestra infestans4;
	
	LocalDateTime fechaDeCreacionValida;
	
	@BeforeEach
	public void setUp() throws Exception {
		
		usuario = mock(Usuario.class);
		basico = new Basico();
		experto = mock(Experto.class);
		
		muestras = new ArrayList<Muestra>();
		
		sordida = mock(Muestra.class);
		guasayana = mock(Muestra.class);
		infestans = mock(Muestra.class);
		sordida2 = mock(Muestra.class);
		guasayana2 = mock(Muestra.class);
		infestans2 = mock(Muestra.class);
		sordida3 = mock(Muestra.class);
		guasayana3 = mock(Muestra.class);
		infestans3 = mock(Muestra.class);
		sordida4 = mock(Muestra.class);
		guasayana4 = mock(Muestra.class);
		infestans4 = mock(Muestra.class);
		
		muestras.add(sordida);
		muestras.add(sordida2);
		muestras.add(sordida3);
		muestras.add(sordida4);
		muestras.add(infestans);
		muestras.add(infestans2);
		muestras.add(infestans3);
		muestras.add(infestans4);
		muestras.add(guasayana);
		muestras.add(guasayana2);
		muestras.add(guasayana3);
		muestras.add(guasayana4);
		
		fechaDeCreacionValida = LocalDateTime.of(2025, 6, 25, 13, 0, 0);
		
		when(sordida.getPublicador()).thenReturn(usuario);
		when(sordida2.getPublicador()).thenReturn(usuario);
		when(sordida3.getPublicador()).thenReturn(usuario);
		when(sordida4.getPublicador()).thenReturn(usuario);
		when(guasayana.getPublicador()).thenReturn(usuario);
		when(guasayana2.getPublicador()).thenReturn(usuario);
		when(guasayana3.getPublicador()).thenReturn(usuario);
		when(guasayana4.getPublicador()).thenReturn(usuario);
		when(infestans.getPublicador()).thenReturn(usuario);
		when(infestans2.getPublicador()).thenReturn(usuario);
		when(infestans3.getPublicador()).thenReturn(usuario);
		when(infestans4.getPublicador()).thenReturn(usuario);
		
		when(sordida.getFechaDeCreacion()).thenReturn(fechaDeCreacionValida);
		when(sordida2.getFechaDeCreacion()).thenReturn(fechaDeCreacionValida);
		when(sordida3.getFechaDeCreacion()).thenReturn(fechaDeCreacionValida);
		when(sordida4.getFechaDeCreacion()).thenReturn(fechaDeCreacionValida);
		when(guasayana.getFechaDeCreacion()).thenReturn(fechaDeCreacionValida);
		when(guasayana2.getFechaDeCreacion()).thenReturn(fechaDeCreacionValida);
		when(guasayana3.getFechaDeCreacion()).thenReturn(fechaDeCreacionValida);
		when(guasayana4.getFechaDeCreacion()).thenReturn(fechaDeCreacionValida);
		when(infestans.getFechaDeCreacion()).thenReturn(fechaDeCreacionValida);
		when(infestans2.getFechaDeCreacion()).thenReturn(fechaDeCreacionValida);
		when(infestans3.getFechaDeCreacion()).thenReturn(fechaDeCreacionValida);
		when(infestans4.getFechaDeCreacion()).thenReturn(fechaDeCreacionValida);
		
		when(sordida.haVotado(usuario)).thenReturn(true);
		when(sordida2.haVotado(usuario)).thenReturn(true);
		when(sordida3.haVotado(usuario)).thenReturn(true);
		when(sordida4.haVotado(usuario)).thenReturn(true);
		when(guasayana.haVotado(usuario)).thenReturn(true);
		when(guasayana2.haVotado(usuario)).thenReturn(true);
		when(guasayana3.haVotado(usuario)).thenReturn(true);
		when(guasayana4.haVotado(usuario)).thenReturn(true);
		when(infestans.haVotado(usuario)).thenReturn(true);
		when(infestans2.haVotado(usuario)).thenReturn(true);
		when(infestans3.haVotado(usuario)).thenReturn(true);
		when(infestans4.haVotado(usuario)).thenReturn(true);
		
		when(usuario.getNivelDeUsuario()).thenReturn(experto);
	}
	
	@Test
	void cambiarNivelDeUsuarioTest() {
		
		basico.cambiarNivelDelUsuario(muestras, usuario);
		assertEquals(experto, usuario.getNivelDeUsuario());
	}

}



























