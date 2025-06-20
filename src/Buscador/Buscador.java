package Buscador;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import muestras.Muestra;

public class Buscador {
	
	private List<Filtro> filtros;
	
	public Buscador() {
		
		this.filtros = new ArrayList<Filtro>();
	}
	
	public void agregarFiltro(Filtro filtro) {
		
		this.filtros.add(filtro);
	}
	
	public void eliminarFiltro(Filtro filtro) {
		
		this.filtros.remove(filtro);
	}
	
	public List<Filtro> getFiltros() {
		
		return this.filtros;
	}
	
	public List<Muestra> buscar(List<Muestra> muestrasIniciales) {
		
		/**
		 * verifica que la lista de filtros no esté vacia para buscar las muestras,
		 * en caso de que este vacia, devuelve la lista inicial de muestras.
		 * 
		 * */
		if (!this.getFiltros().isEmpty()) {
			
			return this.getFiltros().stream()
							        .forEach(filtro -> filtro.filtrar(muestrasIniciales))
									.toList();

		} else {
		
			return muestrasIniciales;
	}
}




}