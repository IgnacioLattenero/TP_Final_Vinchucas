package ManejoDeUsuarios;

import java.util.List;

import muestras.Muestra;

public class Basico extends NivelDeUsuario {
	
	@Override
	public void cambiarNivelDelUsuario(List<Muestra> muestras, Usuario usuario) {
		
		if (super.hizoMasDe10EnviosEnLosUltimos30Dias(muestras, usuario) &&
			super.hizoMasDe20RevisionesEnLosUltimos30Dias(muestras, usuario)) {

				usuario.setNivel(new Experto());

		}
	}

	@Override
	public void votarEnMuestraAbierta(Muestra muestra, Voto voto) {
		muestra.getVotos().add(voto);
	}

	@Override
	public void votarEnMuestraExpertos(Muestra muestra, Voto voto) {
		// el usuario básico no vota en ese estado
	}

	@Override
	public boolean esVotoCalificado() {
		return false;
	}
}
