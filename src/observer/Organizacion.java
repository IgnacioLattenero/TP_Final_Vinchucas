package observer;

import muestras.Muestra;
import unidadGeografica.Ubicacion;

public class Organizacion implements Observer {
	private int 	  			 cantidadEmpleados;
	private Ubicacion 			 ubicacion;
	private TipoOrganizacion  	 tipo;
	private FuncionalidadExterna funcionalidadExterna;
	
	public Organizacion(int cantidadEmpleados, Ubicacion ubicacion, TipoOrganizacion tipo) {
		this.cantidadEmpleados = cantidadEmpleados;
		this.ubicacion 		   = ubicacion;
		this.tipo 			   = tipo;
	}

	@Override
	public void updateValidacionMuestra(ZonaDeCobertura zonaDeCobertura, Muestra muestra) {
		this.funcionalidadExterna.nuevoEvento(this, zonaDeCobertura, muestra);
	}

	@Override
	public void updateAltaMuestra(ZonaDeCobertura zonaDeCobertura, Muestra muestra) {
		this.funcionalidadExterna.nuevoEvento(this, zonaDeCobertura, muestra);
	}
	
}
