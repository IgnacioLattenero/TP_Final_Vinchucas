package ManejoDeUsuarios;

import java.time.LocalDateTime;
import java.util.List;

import muestras.AbiertaATodaOpinion;
import muestras.EstadoDeMuestra;
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
	public void addVoto(Muestra muestra, Voto voto) {
		EstadoDeMuestra abiertaATodaOpinion = new AbiertaATodaOpinion();
		if(muestra.getEstado().equals(abiertaATodaOpinion)) {
			muestra.getEstado().agregarVoto(voto, muestra);
		}
	}
}
