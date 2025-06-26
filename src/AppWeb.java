import java.util.List;

import Buscador.Buscador;
import Buscador.Filtro;
import ManejoDeUsuarios.Usuario;
import muestras.Muestra;
import observer.Organizacion;
import observer.ZonaDeCobertura;


public class AppWeb {

	private Buscador buscador;
	private List<Usuario> usuarios;
	private List<Muestra> muestras;
	private List<ZonaDeCobertura> zonasDeCobertura;
	private List<Organizacion> organizaciones;
	
	
	
	//Metodos
	public void nivelarUsuarios() {
		this.usuarios.stream().forEach(u -> u.cambiarNivelDelUsuario(muestras));
	}
	
	public List<Muestra> realizarBusqueda(){
		return this.buscador.buscar(this.muestras);
	}
	
	public void establecerFiltro(Filtro filtro) {
		this.buscador.agregarFiltro(filtro);
	}
	
	
	public void agregarMuestra(Muestra muestra) {	
		this.muestras.add(muestra); // Agregar a la lista de muestras "registradas"
		this.ubicarEnZonaSiCorresponde(muestra); // Agregar a zona si corresponde	 
	}
		
	public void ubicarEnZonaSiCorresponde(Muestra muestra) {
		//Agregamos las muestras a la zona correspondiente
		// Una muestra pertenece a una zona si la distancia de su ubicacion al epicentro de la zona es menor al radio de dicha zona.
		zonasDeCobertura.stream()
						.filter(zona -> zona.getEpicentro().distanciaA(muestra.getUbicacion()) < zona.getRadio())
						.forEach(zona -> zona.addMuestra(muestra));		
		
		
	}
	
	public List<Muestra> muestrasAMenosDe(int km, Muestra muestraCentral){
		
		return this.muestras.stream()
				 .filter(m -> muestraCentral.getUbicacion().distanciaA(m.getUbicacion()) <= km)
				 .toList();
	}
	
	
	
	public void agregarZonaDeCobertura(ZonaDeCobertura zona) {
		this.zonasDeCobertura.add(zona);
	}
	
	public void agregarUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
	}
	
	public void agregarOrganizacion(Organizacion organizacion) {
		this.organizaciones.add(organizacion);
	}
	
	
}
