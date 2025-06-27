package Buscador;

import ManejoDeUsuarios.Opinion;
import muestras.Muestra;

public class FiltroTipoDeInsecto extends Filtro {
	
	private Opinion tipoDeInsecto;
	
	public FiltroTipoDeInsecto(Opinion tipoDeInsecto) {
		
		this.tipoDeInsecto = tipoDeInsecto;
	}
	
	public Opinion getTipoDeInsecto() {
		
		return this.tipoDeInsecto;
	}

	@Override
	public boolean pasaFiltro(Muestra muestra) {
		
		return muestra.getEspecie().equals(this.getTipoDeInsecto());
	}

}
