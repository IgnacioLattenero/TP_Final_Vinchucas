package unidadGeografica;

import java.util.List;

import muestras.Muestra;

public class Mapa {
	
	//Atributos
	private List<Muestra> muestras;
	private List<ZonaDeCobertura> zonasDeCobertura;
	
	
	//Metodos
	public void agregarMuestra(Muestra muestra) {	
		this.muestras.add(muestra);
		this.ubicarEnZonaSiCorresponde(muestra);	
	}
	
	public void ubicarEnZonaSiCorresponde(Muestra muestra) {
		//Agregamos las muestras a la zona correspondiente
		// Una muestra pertenece a una zona su distancia al epicentro es menor al radio de la zona.
		zonasDeCobertura.stream()
						.filter(zona -> zona.getEpicentro().distanciaA(muestra.getUbicacion()) < zona.getRadio())
						.forEach(zona -> zona.addMuestra(muestra));		
		
		
	}
	
	public void agregarZonaDeCobertura(ZonaDeCobertura zona) {
		this.zonasDeCobertura.add(zona);
	}
	
}
