package Buscador;

import java.util.List;

import muestras.EstadoDeMuestra;
import muestras.Muestra;

public class FiltroNivelDeVerificacion extends Filtro {

	private EstadoDeMuestra nivelDeVerificacion;
	
	public FiltroNivelDeVerificacion(EstadoDeMuestra nivelDeVerificacion) {
		
		this.nivelDeVerificacion = nivelDeVerificacion;
	}
	
	public EstadoDeMuestra getNivelDeVerificacion() {
		
		return this.nivelDeVerificacion;
	}

	@Override
	public boolean pasaFiltro(Muestra muestra) {
		
		return muestra.getEstado().equals(this.getNivelDeVerificacion());
	}
	
}
