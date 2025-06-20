package Buscador;

import java.util.List;

import muestras.Muestra;

public interface Filtro {
	
	public abstract List<Muestra> filtrar(List<Muestra> muestrasIniciales);
	
}
