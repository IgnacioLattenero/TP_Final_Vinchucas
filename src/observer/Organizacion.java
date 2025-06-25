package observer;

import muestras.Muestra;
import unidadGeografica.Ubicacion;

public class Organizacion implements Observer {
	private int 	  			 cantidadEmpleados;
	private Ubicacion 			 ubicacion;
	private TipoOrganizacion  	 tipo;
	private FuncionalidadExterna funcionalidadExternaAlta;
	private FuncionalidadExterna funcionalidadExternaValidacion;
	
	public Organizacion(int cantidadEmpleados, Ubicacion ubicacion, TipoOrganizacion tipo,
							FuncionalidadExterna funcExtAlta, FuncionalidadExterna funcExtValidacion) {
		this.cantidadEmpleados 				= cantidadEmpleados;
		this.ubicacion 		   				= ubicacion;
		this.tipo 			   				= tipo;
		this.funcionalidadExternaAlta 		= funcExtAlta;
		this.funcionalidadExternaValidacion = funcExtValidacion;
	}
	
	public void suscribirseAZona(ZonaDeCobertura zona) {
		zona.attachObserver(this);
	}
	
	public void desuscribirseDeZona(ZonaDeCobertura zona) {
		zona.detachObserver(this);
	}
	
	public void modificarFuncionalidadExternaAlta(FuncionalidadExterna funcionalidadExterna) {
		this.funcionalidadExternaAlta = funcionalidadExterna;
	}

	public void modificarFuncionalidadExternaValidacion(FuncionalidadExterna funcionalidadExterna) {
		this.funcionalidadExternaValidacion = funcionalidadExterna;
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
