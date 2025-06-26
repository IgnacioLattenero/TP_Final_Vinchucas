package observer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestras.Muestra;
import unidadGeografica.Ubicacion;

class ZonaDeCoberturaTest {
	private Ubicacion ubicacionMock1;
	private Ubicacion ubicacionMock2;
	private Ubicacion ubicacionMock3;
	private Ubicacion ubicacionMock4;
	private Muestra muestraMock1;
	private Muestra muestraMock2;
	private Muestra muestraMock3;
	private Observer observerMock1;
	private Observer observerMock2;
	
	private ZonaDeCobertura zonaDeCoberturaSUT;
	private ZonaDeCobertura zonaDeCobertura2;
	private ZonaDeCobertura zonaDeCobertura3;
	private ZonaDeCobertura zonaDeCobertura4;
	private List<ZonaDeCobertura> zonasAEvaluar;

	@BeforeEach
	void setUp() {
		this.ubicacionMock1 = mock(Ubicacion.class);
		this.ubicacionMock2 = mock(Ubicacion.class);
		this.ubicacionMock3 = mock(Ubicacion.class);
		this.ubicacionMock4 = mock(Ubicacion.class);
		this.muestraMock1 = mock(Muestra.class);
		this.muestraMock2 = mock(Muestra.class);
		this.muestraMock3 = mock(Muestra.class);
		this.zonaDeCoberturaSUT = new ZonaDeCobertura(10, "Zona 1 para test", ubicacionMock1);
		this.zonaDeCobertura2   = new ZonaDeCobertura(10, "Zona 2 para test", ubicacionMock2);
		this.zonaDeCobertura3   = new ZonaDeCobertura(10, "Zona 3 para test", ubicacionMock3);
		this.zonaDeCobertura4   = new ZonaDeCobertura(10, "Zona 4 para test", ubicacionMock4);
		this.zonasAEvaluar = new ArrayList<ZonaDeCobertura>();
		this.zonasAEvaluar.add(zonaDeCobertura2); // Agrego 3 listas para comparar con la 1
		this.zonasAEvaluar.add(zonaDeCobertura3); //
		this.zonasAEvaluar.add(zonaDeCobertura4); //
		this.observerMock1 = mock(Observer.class);
		this.observerMock2 = mock(Observer.class);
	}
	

	@Test
	void testConstructor() {
		assertNotNull(zonaDeCoberturaSUT);
		
	}
	
	@Test
	void testAgregarMuestrasYDevolverMuestrasReportadas() {
		this.zonaDeCoberturaSUT.addMuestra(muestraMock1);
		this.zonaDeCoberturaSUT.addMuestra(muestraMock2);
		this.zonaDeCoberturaSUT.addMuestra(muestraMock3);
		assertEquals(List.of(muestraMock1, muestraMock2, muestraMock3), zonaDeCoberturaSUT.muestrasReportadas());
		
	}
	
	@Test
	void testZonasSolapadas() {
		when(ubicacionMock1.distanciaA(ubicacionMock2)).thenReturn(15d); //se solapa
		when(ubicacionMock1.distanciaA(ubicacionMock3)).thenReturn(30d); //no se solapa
		when(ubicacionMock1.distanciaA(ubicacionMock4)).thenReturn(18d); //se solapa
		assertEquals(List.of(zonaDeCobertura2, zonaDeCobertura4), zonaDeCoberturaSUT.zonasSolapadas(zonasAEvaluar));
		
	}
	
	@Test
	void testSuscribirYDesuscribirObservadoresYNotificarlos() {
		this.zonaDeCoberturaSUT.attachObserver(observerMock1);
		this.zonaDeCoberturaSUT.attachObserver(observerMock2);
		this.zonaDeCoberturaSUT.notifyAltaMuestra(zonaDeCoberturaSUT, muestraMock1);
		this.zonaDeCoberturaSUT.notifyValidacionMuestra(zonaDeCoberturaSUT, muestraMock1);
		verify(observerMock1).updateAltaMuestra(zonaDeCoberturaSUT, muestraMock1);
		verify(observerMock2).updateAltaMuestra(zonaDeCoberturaSUT, muestraMock1);
		verify(observerMock1).updateValidacionMuestra(zonaDeCoberturaSUT, muestraMock1);
		verify(observerMock2).updateValidacionMuestra(zonaDeCoberturaSUT, muestraMock1);
		this.zonaDeCoberturaSUT.detachObserver(observerMock2);
		this.zonaDeCoberturaSUT.notifyAltaMuestra(zonaDeCoberturaSUT, muestraMock2);
		verify(observerMock1).updateAltaMuestra(zonaDeCoberturaSUT, muestraMock2);
		verifyNoMoreInteractions(observerMock2);
		
	}

}
