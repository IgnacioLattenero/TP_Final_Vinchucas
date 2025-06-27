package ManejoDeUsuarios;

import java.util.List;

import muestras.Muestra;

public class Especialista extends NivelDeUsuario {
	
	@Override
	public void cambiarNivelDelUsuario(List<Muestra> muestras, Usuario usuario) {
		
		/**
		 * Este método no hace nada, ya que un especialista nunca pierde su nivel
		 * */
		
	}

}
