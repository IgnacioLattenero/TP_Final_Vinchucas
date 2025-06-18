import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unidadGeografica.Ubicacion;



class testPrueba {

	
	Ubicacion ubicacionMock = mock(Ubicacion.class);
	Ubicacion ubicacionReal ;
	@BeforeEach
	public void setUp() {
		
		ubicacionReal = new Ubicacion(5,5);
		
		
		
		when(ubicacionMock.getLatitud()).thenReturn((double) 10);
		when(ubicacionMock.getLongitud()).thenReturn((double) 10);
	}
	
	
	@Test
	void test() {
		
		assertEquals(7,(int) ubicacionReal.distanciaA(ubicacionMock));
		
	}

}
