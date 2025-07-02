package appWebTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Buscador.FiltroFechaDeCreacion;
import ManejoDeUsuarios.Usuario;
import appweb.AppWeb;
import muestras.Muestra;
import observer.ZonaDeCobertura;
import unidadGeografica.Ubicacion;

class appWebTest {

	//SUT
	AppWeb app;
	List<Usuario> usuarios;
	List<Muestra> muestras;
	List<ZonaDeCobertura> zonasDeCobertura;
		
	//DOC
	Usuario user1 = mock(Usuario.class);
	Usuario user2 = mock(Usuario.class);
	Usuario user3 = mock(Usuario.class);
	
	Muestra muestra1 = mock(Muestra.class);
	Muestra muestra2 = mock(Muestra.class);
	Muestra muestra3 = mock(Muestra.class);
	
	Ubicacion ubicacionM1 = mock(Ubicacion.class);
	Ubicacion ubicacionM2 = mock(Ubicacion.class);
	Ubicacion ubicacionM3 = mock(Ubicacion.class);
	
	ZonaDeCobertura zona = mock(ZonaDeCobertura.class);
	Ubicacion epicentro  = mock(Ubicacion.class); 
	
	FiltroFechaDeCreacion filtro = mock(FiltroFechaDeCreacion.class);
	
	@BeforeEach
	public void setUp() {
		
		usuarios = new ArrayList<>();
		muestras = new ArrayList<>();
		zonasDeCobertura = new ArrayList<>();
	
		usuarios.add(user1);
		usuarios.add(user2);
		usuarios.add(user3);
		
		//muestras.add(muestra1);
		muestras.add(muestra2);
		muestras.add(muestra3);
		
		zonasDeCobertura.add(zona);
		
		app = new AppWeb(usuarios, muestras, zonasDeCobertura);
		
		when(muestra1.getUbicacion()).thenReturn(ubicacionM1);
		when(muestra2.getUbicacion()).thenReturn(ubicacionM2);
		when(muestra3.getUbicacion()).thenReturn(ubicacionM3);
		
		when(filtro.buscar(muestras)).thenReturn(muestras);
		when(zona.getEpicentro()).thenReturn(epicentro);
		when(zona.getRadio()).thenReturn(10);
		
		when(epicentro.getLatitud()).thenReturn(5);
		when(epicentro.getLongitud()).thenReturn(5);
		
		when(ubicacionM1.getLatitud()).thenReturn(5);
		when(ubicacionM1.getLongitud()).thenReturn(6);
		
		when(ubicacionM2.getLatitud()).thenReturn(5);
		when(ubicacionM2.getLongitud()).thenReturn(5);
		
		when(ubicacionM3.getLatitud()).thenReturn(5000);
		when(ubicacionM3.getLongitud()).thenReturn(6000);
		
		
	}
	
	@Test
	void testDosMuestrasPertenecenAZona() {
		
		List<Muestra> resultado = new ArrayList<>();
		resultado.add(muestra1);
		resultado.add(muestra2);
		
		
		when(zona.muestrasReportadas(muestras)).thenReturn(resultado);
		
		assertEquals(resultado, app.muestrasQuePertenecenA(zona));
	}
	
	@Test
	void testRealizarBusqueda() {
		assertEquals(muestras, app.realizarBusqueda(filtro));
	}
	
	@Test
	void agregarMuestraALaLista() {
	  
	    app.agregarMuestra(muestra1);

	    assertTrue(app.getMuestras().contains(muestra1));
	}
	
	@Test
	void agregarMuestraYLlamaAddMuestraEnZonaSiCorresponde() {

	    app.agregarMuestra(muestra1);

	    // Verifica que se llamó a addMuestra en la zona mockeada
	    verify(muestra1).agregarZona(zona);
	}
	
	@Test
	void agregarMuestraYNoLlamaAddMuestraSiNoCorresponde() {
	   
	    Ubicacion ubicacionLejana = mock(Ubicacion.class);
	    when(muestra1.getUbicacion()).thenReturn(ubicacionLejana);
	    // Simula que la distancia es mayor al radio
	    when(epicentro.distanciaA(ubicacionLejana)).thenReturn(20.0); // radio es 10

	    app.agregarMuestra(muestra1);

	    // Verifica que NO se llamó a addMuestra
	    verify(muestra1, never()).agregarZona(zona);
	}
	
	@Test
	void getZonaDeCobertura() {
		
		assertEquals(zonasDeCobertura, app.getZonasDeCobertura());
	}
	
	@Test
	void getUsuarios() {
		assertEquals(usuarios, app.getUsuarios());
	 
	}
	
	@Test
	void getMuestras() {

		assertEquals(muestras, app.getMuestras());
	}
	
	@Test
	void agregarUsuario() {
		
		Usuario user = mock(Usuario.class);
		app.agregarUsuario(user);
		
		assertTrue(app.getUsuarios().contains(user));
	}
	
	@Test
	void agregarZonaDeCobertura() {
		
		ZonaDeCobertura zona2 = mock(ZonaDeCobertura.class);
		app.agregarZonaDeCobertura(zona2);
		
		assertTrue(app.getZonasDeCobertura().contains(zona2));
	}
	
	@Test
	void muestrasAMenosDe10Km() {
	
		// muestra1 es el centro, muestra2 está a 5, muestra3 está a 20
	    when(ubicacionM2.distanciaA(ubicacionM1)).thenReturn(5.0);   // Dentro de 10 km
	    when(ubicacionM3.distanciaA(ubicacionM1)).thenReturn(20.0);  // Fuera de 10 km
		
		List<Muestra> resultado = new ArrayList<>();
		resultado.add(muestra2);
		
		assertEquals(resultado, app.muestrasAMenosDe(10, muestra1));
	}
	
	@Test
	void seNivelanTodosLosUsuariosRegistrados() {
	
		app.nivelarUsuarios();
		
		verify(user1).cambiarNivelDelUsuario(muestras);
		verify(user2).cambiarNivelDelUsuario(muestras);
		verify(user3).cambiarNivelDelUsuario(muestras);
	}
	
	
	
}
