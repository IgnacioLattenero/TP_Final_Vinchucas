package unidadGeografica;

import java.util.List;

public class Ubicacion {
	
	//Atributos
	private double latitud;
	private double longitud;
	
	
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
	
	public List<Ubicacion> ubicacionesEn_AMenosDe_(List<Ubicacion> ubicaciones, double distanciaKm){
		
		return ubicaciones.stream()
						  .filter(u -> u.distanciaA(this) <= distanciaKm)
						  .toList();
		
		
	}
	
	//Getters and setters
	public double getLatitud() {
		return this.latitud;
	}
	
	public double getLongitud() {
		return this.longitud;
	}
	
	public void setLatitud(double nuevaLatitud) {
		this.latitud = nuevaLatitud;
	}
	
	public void setLongitud(double nuevaLongitud) {
		this.longitud = nuevaLongitud;
	}
	
	
	
	
	
}
