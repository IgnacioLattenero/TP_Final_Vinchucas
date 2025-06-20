package Buscador;

import java.util.List;

import muestras.EstadoDeMuestra;
import muestras.Muestra;

public class FiltroNivelDeVerificacion implements Filtro {

	private EstadoDeMuestra nivelDeVerificacion;
	
	public FiltroNivelDeVerificacion(EstadoDeMuestra nivelDeVerificacion) {
		
		this.nivelDeVerificacion = nivelDeVerificacion;
	}
	
	public EstadoDeMuestra getNivelDeVerificacion() {
		
		return this.nivelDeVerificacion;
	}

	@Override
	public List<Muestra> filtrar(List<Muestra> muestrasIniciales) {
		
		
		return muestrasIniciales.stream()
								.filter(muestra -> muestra.getEstado().equals(this.getNivelDeVerificacion()))
								.toList();
	}
	
}
