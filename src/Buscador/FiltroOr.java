package Buscador;

import java.util.ArrayList;
import java.util.List;

import muestras.Muestra;

public class FiltroOr implements Filtro {
	
	private Filtro[] filtros = new Filtro[2];

	public FiltroOr(Filtro primerFiltro, Filtro segundoFiltro) {
		
		this.filtros[0] = primerFiltro;
		this.filtros[1] = segundoFiltro;
	}

	@Override
	public List<Muestra> filtrar(List<Muestra> muestrasIniciales) {
		
		/**
		 * Este filtro tiene 2 filtros, obtiene 2 listas filtradas, y las va a juntar en una tercera 
		 * resultante, que va a contener a las dos listas y devuelve una tercera, y a la tercera
		 * con el metodo distinct de streams, eliminamos los duplicados al final.
		 * 
		 * */
		
		List<Muestra> primerResultado = filtros[0].filtrar(muestrasIniciales);
		List<Muestra> segundoResultado = filtros[1].filtrar(muestrasIniciales);
		
        List<Muestra> listaConcatenada = new ArrayList<>(primerResultado.size() + segundoResultado.size());

       
        for (Muestra muestraActual : primerResultado) {
        	
            listaConcatenada.add(muestraActual);
        }

       
        for (Muestra muestraActual : segundoResultado) {
        	
            listaConcatenada.add(muestraActual);
        }

      
        return listaConcatenada.stream().distinct().toList(); 
	}
}
