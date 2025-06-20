package Buscador;

import java.time.LocalDateTime;
import java.util.List;

import muestras.Muestra;

public class FiltroFechaDeUltimaVotacion implements Filtro {
	
	private LocalDateTime fechaDeVotacionBuscada;
	
	public FiltroFechaDeUltimaVotacion(LocalDateTime fechaDeVotacionBuscada) {
		
		this.fechaDeVotacionBuscada = fechaDeVotacionBuscada;
	}
	
	public LocalDateTime getFechaDeVotacionBuscada() {
		
		return this.fechaDeVotacionBuscada;
	}
	@Override
	public List<Muestra> filtrar(List<Muestra> muestrasIniciales) {
		
		/**
		 * recorre la lista de muestras, recorre la lista de votos de cada muestra, y busca las muestras que tengan la misma 
		 * fecha de antiguedad, busca la mas reciente. ivan pone su lista de votos en cada muestra ordenadas por antiguedad, de mas nuevo 
		 * a mas viejo.
		 * */
		
		return muestrasIniciales.stream()
								.filter(muestra -> muestra.getVotos().getFirst().equals(this.getFechaDeVotacionBuscada()))
								.toList();
								
	}
	
}
