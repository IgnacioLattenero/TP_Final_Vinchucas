package Buscador;

import java.util.List;

import ManejoDeUsuarios.Opinion;
import muestras.Muestra;

public class FiltroTipoDeInsecto implements Filtro {
	
	private Opinion tipoDeInsecto;
	
	public FiltroTipoDeInsecto(Opinion tipoDeInsecto) {
		
		this.tipoDeInsecto = tipoDeInsecto;
	}
	
	public Opinion getTipoDeInsecto() {
		
		return this.tipoDeInsecto;
	}

	@Override
	public List<Muestra> filtrar(List<Muestra> muestrasIniciales) {
		
		return muestrasIniciales.stream()
								.filter(muestra -> muestra.getVotos().stream().findFirst(voto -> voto.getOpinion().equals(this.getTipoDeInsecto())))
								.toList();
	}
	
	

}
