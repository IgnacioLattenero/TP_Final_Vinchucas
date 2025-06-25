package ManejoDeUsuarios;

import java.util.List;

import muestras.Muestra;

public class Experto extends NivelDeUsuario {


	@Override
	public String toString() {
		
		return "Experto";
	}

	@Override
	public void cambiarNivelDelUsuario(List<Muestra> muestras, Usuario usuario) {
		
		if (!super.hizoMasDe10EnviosEnLosUltimos30Dias(muestras, usuario) ||
			!super.hizoMasDe20RevisionesEnLosUltimos30Dias(muestras, usuario)) {

				usuario.setNivel(new Basico());

		}
	
	}

}
