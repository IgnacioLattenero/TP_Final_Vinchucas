package observer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import muestras.Muestra;
import unidadGeografica.Ubicacion;

public class ZonaDeCobertura implements Subject {
	private int 	  	   radio;
	private String	  	   nombre;
	private Ubicacion 	   epicentro;
	private List<Muestra>  muestras;
	private List<Observer> observadores;
	
	public ZonaDeCobertura(int radio, String nombre, Ubicacion epicentro) {
		this.radio 	   	  = radio;
		this.nombre    	  = nombre;
		this.epicentro 	  = epicentro;
		this.muestras  	  = new ArrayList<Muestra>();
		this.observadores = new ArrayList<Observer>();
		
	}
	
	public List<Muestra> muestrasReportadas() {
		return this.muestras;
	}
	
	public List<ZonaDeCobertura> zonasSolapadas(List<ZonaDeCobertura> zonasAEvaluar) {
		return zonasAEvaluar.stream().filter(zona -> this.sonZonasSolapadas(this, zona)).collect(Collectors.toList());
	}
	
	private boolean sonZonasSolapadas(ZonaDeCobertura zona1, ZonaDeCobertura zona2) {
		return (zona1.epicentro.distanciaA(zona2.epicentro) < zona1.radio + zona2.radio); //distancia entre epicentros < suma de los radios 
	}
	
	public void addMuestra(Muestra muestra) {
		this.muestras.add(muestra);
	}

	@Override
	public void attachObserver(Observer observador) {
		this.observadores.add(observador);
	}

	@Override
	public void detachObserver(Observer observador) {
		this.observadores.remove(observador);
	}
	
	@Override
	public void notifyValidacionMuestra(ZonaDeCobertura zonaDeCobertura, Muestra muestra) {
		this.observadores.stream().forEach(observador -> observador.updateValidacionMuestra(this, muestra));
		
	}
	@Override
	public void notifyAltaMuestra(ZonaDeCobertura zonaDeCobertura, Muestra muestra) {
		this.observadores.stream().forEach(observador -> observador.updateAltaMuestra(this, muestra));
	}
	
}
