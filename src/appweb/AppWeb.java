package appweb;

import java.util.List;


import Buscador.Filtro;
import ManejoDeUsuarios.Usuario;
import muestras.Muestra;

import observer.ZonaDeCobertura;


public class AppWeb {

	private List<Usuario> usuarios;
	private List<Muestra> muestras;
	private List<ZonaDeCobertura> zonasDeCobertura;
		
	
	public AppWeb(List<Usuario> usuarios, List<Muestra> muestras,
			List<ZonaDeCobertura> zonasDeCobertura) {
		super();
		this.usuarios = usuarios;
		this.muestras = muestras;
		this.zonasDeCobertura = zonasDeCobertura;
	}

	//Metodos
	
	public List<Muestra> muestrasQuePertenecenA(ZonaDeCobertura zona){
		return zona.muestrasReportadas(muestras);
	}
	
	
	
	public void nivelarUsuarios() {
		this.usuarios.stream().forEach(u -> u.cambiarNivelDelUsuario(muestras));
	}
	
	public List<Muestra> realizarBusqueda(Filtro filtro){
		return filtro.buscar(this.muestras);
	}
	
	
	public void agregarMuestra(Muestra muestra) {	
		this.muestras.add(muestra); // Agregar a la lista de muestras "registradas"
		this.enlazarConZonaDeCoberturaSiCorresponde(muestra); // Agregar a zona si corresponde	
		
		//Notificar a las zonasDeCobertura
		muestra.getZonasALasQuePertenece().stream()
										  .forEach(z -> z.notifyAltaMuestra(z, muestra));
	}
		
	public void enlazarConZonaDeCoberturaSiCorresponde(Muestra muestra) {
		// Filtra la zona (o las zonas) a la que puede pertenecer la muestra y la agrega a la muestra. 
		// Una muestra pertenece a una zona si la distancia de su ubicacion al epicentro de la zona es menor al radio de dicha zona.
		zonasDeCobertura.stream()
						.filter(zona -> zona.getEpicentro().distanciaA(muestra.getUbicacion()) < zona.getRadio())
						.forEach(zona -> muestra.agregarZona(zona));		
	
		
	}
	
	public List<Muestra> muestrasAMenosDe(int km, Muestra muestraCentral){
		
		return this.muestras.stream()
				 .filter(m -> m.getUbicacion().distanciaA(muestraCentral.getUbicacion()) <= km)
				 .toList();
	}
	
	
	
	public void agregarZonaDeCobertura(ZonaDeCobertura zona) {
		this.zonasDeCobertura.add(zona);
	}
	
	public void agregarUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
	}
	
	public List<Muestra> getMuestras(){
		return this.muestras;
	}
	
	public List<Usuario> getUsuarios(){
		return this.usuarios;
	}
	
	public List<ZonaDeCobertura> getZonasDeCobertura(){
		return this.zonasDeCobertura;
	}
}
