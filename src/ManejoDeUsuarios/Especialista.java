package ManejoDeUsuarios;

import java.util.List;

import muestras.Muestra;

public class Especialista extends NivelDeUsuario {
	
	public Especialista() {
		
	}

	@Override
	public String toString() {
		
		return "Especialista";
	}

	@Override
	public void subirDeNivel(List<Muestra> muestras, Usuario usuario) {
		
		/**
		 * Este método no hace nada, ya que un especialista nunca pierde su nivel
		 * */
		
	}

}
