package Buscador;

import java.util.ArrayList;
import java.util.List;

import muestras.Muestra;

public class FiltroOr extends Filtro {
	
	private Filtro primerFiltro;
	private Filtro segundoFiltro;

	public FiltroOr(Filtro primerFiltro, Filtro segundoFiltro) {
		
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
		
		return this.getPrimerFiltro().pasaFiltro(muestra) || this.getSegundoFiltro().pasaFiltro(muestra);
	}
}























