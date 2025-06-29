package observer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestras.Muestra;
import unidadGeografica.Ubicacion;

class OrganizacionTest {

	//SUT
	Organizacion organizacion;
	
	//DOC
	Ubicacion ubicacion 								= mock(Ubicacion.class);
	TipoOrganizacion tipo 								= mock(TipoOrganizacion.class);
	FuncionalidadExterna funcionalidadExternaAlta		= mock(FuncionalidadExterna.class);
	FuncionalidadExterna funcionalidadExternaValidacion = mock(FuncionalidadExterna.class);
	FuncionalidadExterna funcionalidadExterna 			= mock(FuncionalidadExterna.class);
	ZonaDeCobertura miZona;	
	Muestra muestra 									= mock(Muestra.class);
	
	//spy del objeto real
	ZonaDeCobertura zona; 
    Organizacion spyOrga;
    
	@BeforeEach
	public void setUp() throws Exception {
		
		organizacion = new Organizacion(10,
										ubicacion,
										tipo,
										funcionalidadExternaAlta,
										funcionalidadExternaValidacion);
		
		miZona = new ZonaDeCobertura(1, "zona", ubicacion);
		
		zona = spy(miZona);
		spyOrga = spy(organizacion);
		
		//Ubicacion (5,5)
		when(ubicacion.getLatitud()).thenReturn(5);
		when(ubicacion.getLongitud()).thenReturn(5);
		// Tipo Salud
		when(tipo.name()).thenReturn("SALUD"); // Necesario ?
		
		
	}
	
	
	@Test
	void testSuscribirseAZona() {
		organizacion.suscribirseAZona(zona);
		
		verify(zona).attachObserver(organizacion);
	}
	
	@Test
	void testDeSuscribirseAZona() {
		organizacion.suscribirseAZona(zona);
		organizacion.desuscribirseDeZona(zona);
		
		verify(zona).detachObserver(organizacion);
	}
	
	
	@Test
	void testModificarFuncionalidadExternaAlta() {


        spyOrga.modificarFuncionalidadExternaAlta(funcionalidadExterna);

        verify(spyOrga).modificarFuncionalidadExternaAlta(funcionalidadExterna);
      
	}
	
	@Test
	void testModificarFuncionalidadExternaValidacion() {
  
        spyOrga.modificarFuncionalidadExternaValidacion(funcionalidadExterna);

        verify(spyOrga).modificarFuncionalidadExternaValidacion(funcionalidadExterna);
      
	}
	
	@Test
	void testUpdateValidacionDeMuestra() {

        organizacion.updateValidacionMuestra(miZona, muestra);

        // Verificar que se llamó al método de la interfaz FuncionalidadExterna
        verify(funcionalidadExternaValidacion).nuevoEvento(organizacion, miZona, muestra);
      
	}
	
	@Test
	void testUpdateAltaDeMuestra() {

        organizacion.updateAltaMuestra(miZona, muestra);

        // Verificar que se llamó al método de la interfaz FuncionalidadExterna
        verify(funcionalidadExternaAlta).nuevoEvento(organizacion, miZona, muestra);
      
	}
}//END
