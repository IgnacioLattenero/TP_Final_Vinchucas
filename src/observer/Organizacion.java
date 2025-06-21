package observer;

import muestras.Muestra;
import unidadGeografica.Ubicacion;

public class Organizacion implements Observer {
	private int 	  			 cantidadEmpleados;
	private Ubicacion 			 ubicacion;
	private TipoOrganizacion  	 tipo;
	private FuncionalidadExterna funcionalidadExternaValidacion;
	private FuncionalidadExterna funcionalidadExternaAlta;
	
	public Organizacion(int cantidadEmpleados, Ubicacion ubicacion, TipoOrganizacion tipo) {
		this.cantidadEmpleados = cantidadEmpleados;
		this.ubicacion 		   = ubicacion;
		this.tipo 			   = tipo;
	}

	@Override
	public void updateValidacionMuestra(ZonaDeCobertura zonaDeCobertura, Muestra muestra) {
		this.funcionalidadExternaValidacion.nuevoEvento(this, zonaDeCobertura, muestra);
	}

	@Override
	public void updateAltaMuestra(ZonaDeCobertura zonaDeCobertura, Muestra muestra) {
		this.funcionalidadExternaAlta.nuevoEvento(this, zonaDeCobertura, muestra);
	}
	
}
