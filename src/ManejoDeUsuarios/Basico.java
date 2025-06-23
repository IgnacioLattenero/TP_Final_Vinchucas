package ManejoDeUsuarios;

import java.time.LocalDateTime;
import java.util.List;

import muestras.Muestra;

public class Basico extends NivelDeUsuario {
	
	public Basico() {
	
	}
	
	@Override
	public void subirDeNivel(List<Muestra> muestras, Usuario usuario) {
		
		if (super.hizoMasDe10EnviosEn30Dias(muestras, usuario) &&
			super.hizoMasDe20RevisionesEn30Dias(muestras, usuario)) {

				usuario.setNivel(new Experto());

		}
	}

	@Override
	public String toString() {
		
		return "Basico";
	}


}
