package Buscador;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

import muestras.Muestra;

public class FiltroAnd implements Filtro {
	
	private Filtro[] filtros = new Filtro[2];

	public FiltroAnd(Filtro primerFiltro, Filtro segundoFiltro) {
		
		this.filtros[0] = primerFiltro;
		this.filtros[1] = segundoFiltro;
	}
	
	@Override
	public List<Muestra> filtrar(List<Muestra> muestrasIniciales) {
		
		List<Muestra> listaResultante = filtros[1].filtrar(filtros[0].filtrar(muestrasIniciales));
		
//		if (listaResultante.isEmpty()) {
//			
//			return listaResultante;
//		}
		
		return listaResultante; // testear y probar que si la lista es vacia en el primer filtro da error o no
		
		/**
		 * si la lista del primer filtro es vacia, analizar si el segundo filtro se va a romper o no, 
		 * y si se va a romper, atrapar ese error, con un if. buscar en gemini
		 * saber explicarlo para la defensa oral
		 * */
	}

}
