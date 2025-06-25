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
         * Si la lista de filtros está vacía, devuelve la lista inicial de muestras, 
         * es decir, no se filtra nada, luego, se inicializa el recorrido con 
         * todas las muestras, y se aplica cada filtro sobre el resultado del filtro anterior, 
         * acotando la lista final, hasta la resultante.
         * */ 
        if (this.getFiltros().isEmpty()) {
            return muestrasIniciales; // Si no hay filtros, no se filtra nada.
        }

        List<Muestra> muestrasResultantes = new ArrayList<>(muestrasIniciales); // Inicializamos con todas las muestras

        // Aplicamos cada filtro sobre el resultado del filtro anterior.
        for (Filtro filtro : this.getFiltros()) {
            muestrasResultantes = filtro.filtrar(muestrasResultantes);
        }

        return muestrasResultantes;
    }
}











































