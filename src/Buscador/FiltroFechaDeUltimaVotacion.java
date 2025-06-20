package Buscador;

import java.time.LocalDateTime;
import java.util.List;

import muestras.Muestra;

public class FiltroFechaDeUltimaVotacion extends Filtro {
	
	private LocalDateTime fechaDeVotacionBuscada;
	
	public FiltroFechaDeUltimaVotacion(LocalDateTime fechaDeVotacionBuscada) {
		
		this.fechaDeVotacionBuscada = fechaDeVotacionBuscada;
	}

	@Override
	protected List<Muestra> filtrar(List<Muestra> muestrasIniciales) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Muestra> filtrarPorFechaUltimoVoto() {
		return null;
	}
}
