package unidadGeografica;

import java.util.List;

public class Ubicacion {
	
	//Atributos
	private int latitud;
	private int longitud;
	
	
	//Constructor
	public Ubicacion(int latitud, int longitud) {
		this.latitud  = latitud;
		this.longitud = longitud;
	}
	
	//Metodos
	
	public double distanciaA(Ubicacion otraUbicacion) {
		/*
		 * Las diferencias entre latitud y longitud se guardan en deltas
		 * Luego se aplica la formula de la distancia en un plano
		 */
		double deltaX = otraUbicacion.getLatitud() - this.getLatitud();
		double deltaY = otraUbicacion.getLongitud() - this.getLongitud();
		
		return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
		
	}
	
	public List<Ubicacion> ubicacionesEn_AMenosDe_(List<Ubicacion> ubicaciones, int distanciaKm){
		
		return ubicaciones.stream()
						  .filter(u -> this.distanciaA(u) <= distanciaKm)
						  .toList();
		
		
	}
	
	//Getters and setters
	public int getLatitud() {
		return this.latitud;
	}
	
	public int getLongitud() {
		return this.longitud;
	}
	
	
	
	
	
	
	
}
