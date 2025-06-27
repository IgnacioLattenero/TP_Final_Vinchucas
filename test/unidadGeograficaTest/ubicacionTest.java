package unidadGeograficaTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unidadGeografica.Ubicacion;

class ubicacionTest {

	//SUT
	Ubicacion ubicacionReal ;
	
	//DOC
	Ubicacion ubicacionMock 		 = mock(Ubicacion.class);
	Ubicacion ubicacionLejanaUnoMock = mock(Ubicacion.class);
	Ubicacion ubicacionLejanaDosMock = mock(Ubicacion.class);
	Ubicacion ubicacionCercanaMock   = mock(Ubicacion.class);
	
	List<Ubicacion> ubicaciones;
	
	@BeforeEach
	public void setUp() {
		
		ubicacionReal = new Ubicacion(5,5);

		when(ubicacionMock.getLatitud()).thenReturn(10);
		when(ubicacionMock.getLongitud()).thenReturn(10);
		
		when(ubicacionLejanaUnoMock.getLatitud()).thenReturn(100);
		when(ubicacionLejanaUnoMock.getLongitud()).thenReturn(100);
		
		when(ubicacionLejanaDosMock.getLatitud()).thenReturn(200);
		when(ubicacionLejanaDosMock.getLongitud()).thenReturn(200);
		
		when(ubicacionCercanaMock.getLatitud()).thenReturn(5);
		when(ubicacionCercanaMock.getLongitud()).thenReturn(6);
		
		ubicaciones = new ArrayList<Ubicacion>();
		
		ubicaciones.add(ubicacionCercanaMock);
		ubicaciones.add(ubicacionLejanaUnoMock);
		ubicaciones.add(ubicacionLejanaDosMock);
	}
	
	@Test
	void distanciaEntreDosPuntosEsIgualASiete() {
		assertEquals(7,(int)ubicacionReal.distanciaA(ubicacionMock));
	}
	
	@Test
	void hayUnaUbicacionAMenosDe10() {
		
		List<Ubicacion> resultado = new ArrayList<>();
		resultado.add(ubicacionCercanaMock);
		
		
		assertEquals(resultado,ubicacionReal.ubicacionesEn_AMenosDe_(ubicaciones, 5));
	}
	
	@Test
	void getLatitudEsIgualACinco() {
		assertEquals(5,ubicacionReal.getLatitud());
	}
	
	@Test
	void getLongitudEsIgualACinco() {
		assertEquals(5,ubicacionReal.getLongitud());
	}
}
