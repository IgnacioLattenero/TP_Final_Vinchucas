package Buscador;

import java.util.List;

import muestras.EstadoDeMuestra;
import muestras.Muestra;

public class FiltroNivelDeVerificacion extends Filtro {

	private EstadoDeMuestra nivelDeVerificacion;
	
	public FiltroNivelDeVerificacion(EstadoDeMuestra nivelDeVerificacion) {
		
		this.nivelDeVerificacion = nivelDeVerificacion;
	}

	@Override
	protected List<Muestra> filtrar(List<Muestra> muestrasIniciales) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Muestra> filtrarPorNivel() {
		
		return null;
	}
}
