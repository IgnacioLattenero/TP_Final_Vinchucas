package Buscador;

import java.time.LocalDateTime;
import java.util.List;

import muestras.Muestra;

public class FiltroFechaDeCreacion implements Filtro {

	private LocalDateTime fechaDeCreacion;
	
	public FiltroFechaDeCreacion(LocalDateTime fechaDeCreacion) {
		
		this.fechaDeCreacion = fechaDeCreacion;
	}
	
	
	public LocalDateTime getFechaDeCreacion() {
		
		return this.fechaDeCreacion;
	}
	 
	@Override
	public List<Muestra> filtrar(List<Muestra> muestrasIniciales) {
		
		return muestrasIniciales.stream()
								.filter(muestra -> muestra.getFechaDeCreacion().equals(this.getFechaDeCreacion()))
								.toList();
	}
	

}
