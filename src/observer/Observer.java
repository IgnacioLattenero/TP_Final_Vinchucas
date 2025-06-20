package observer;

import muestras.Muestra;

public interface Observer {
	
	public void updateValidacionMuestra(ZonaDeCobertura zonaDeCobertura, Muestra muestra);
	public void updateAltaMuestra(ZonaDeCobertura zonaDeCobertura, Muestra muestra);

}
