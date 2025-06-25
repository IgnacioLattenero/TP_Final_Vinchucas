package Buscador;

import java.util.List;

import muestras.Especie;
import muestras.Muestra;

public class FiltroTipoDeInsecto implements Filtro {
	
	private Especie tipoDeInsecto;
	
	public FiltroTipoDeInsecto(Especie tipoDeInsecto) {
		
		this.tipoDeInsecto = tipoDeInsecto;
	}
	
	public Especie getTipoDeInsecto() {
		
		return this.tipoDeInsecto;
	}

	@Override
	public List<Muestra> filtrar(List<Muestra> muestrasIniciales) {
		
		return muestrasIniciales.stream()
								.filter(muestra -> muestra.getEspecie().equals(this.getTipoDeInsecto()))
								.toList();
	}
	
	

}
