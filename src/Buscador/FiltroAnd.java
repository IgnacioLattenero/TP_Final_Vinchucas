package Buscador;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

import muestras.Muestra;

public class FiltroAnd extends Filtro {
	
	private Filtro primerFiltro;
	private Filtro segundoFiltro;

	public FiltroAnd(Filtro primerFiltro, Filtro segundoFiltro) {
		
		this.primerFiltro = primerFiltro;
		this.segundoFiltro = segundoFiltro;
	}
	
	public Filtro getPrimerFiltro() {
		
		return this.primerFiltro;
	}
	
	public Filtro getSegundoFiltro() {
		
		return this.segundoFiltro;
	}

	@Override
	public boolean pasaFiltro(Muestra muestra) {
		
		return this.getPrimerFiltro().pasaFiltro(muestra) &&
			   this.getSegundoFiltro().pasaFiltro(muestra);
	}

}
