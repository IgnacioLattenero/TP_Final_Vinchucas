package observer;

import muestras.Muestra;

public interface Subject {
	
	public void attachObserver(Observer observador);
	public void detachObserver(Observer observador);	
	public void notifyValidacionMuestra(ZonaDeCobertura zonaDeCobertura, Muestra muestra);
	public void notifyAltaMuestra(ZonaDeCobertura zonaDeCobertura, Muestra muestra);

}
