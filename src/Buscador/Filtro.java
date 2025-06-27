package Buscador;

import java.util.List;

import muestras.Muestra;

public abstract class Filtro {
	
	// TODO: Actualizar UML
	
	public List<Muestra> buscar(List<Muestra> muestrasIniciales) {
		
		return muestrasIniciales.stream()
								.filter(muestra -> this.pasaFiltro(muestra)) 
								.toList();
	}
	
	public abstract boolean pasaFiltro(Muestra muestra);
	
}
