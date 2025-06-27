package Buscador;

import java.time.LocalDateTime;
import java.util.List;

import muestras.Muestra;

public class FiltroFechaDeCreacion extends Filtro {

	private LocalDateTime fechaDeCreacion;
	
	public FiltroFechaDeCreacion(LocalDateTime fechaDeCreacion) {
		
		this.fechaDeCreacion = fechaDeCreacion;
	}
	
	
	public LocalDateTime getFechaDeCreacion() {
		
		return this.fechaDeCreacion;
	}

	@Override
	public boolean pasaFiltro(Muestra muestra) {
		
		return muestra.getFechaDeCreacion().equals(this.getFechaDeCreacion());
	}
	
}
