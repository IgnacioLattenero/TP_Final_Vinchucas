package ManejoDeUsuarios;

import java.util.List;

import muestras.AbiertaATodaOpinion;
import muestras.EstadoDeMuestra;
import muestras.Muestra;
import muestras.VotanSoloExpertos;

public class Experto extends NivelDeUsuario {


	@Override
	public void cambiarNivelDelUsuario(List<Muestra> muestras, Usuario usuario) {
		
		if (!super.hizoMasDe10EnviosEnLosUltimos30Dias(muestras, usuario) ||
			!super.hizoMasDe20RevisionesEnLosUltimos30Dias(muestras, usuario)) {

				usuario.setNivel(new Basico());

		}
	
	}

	@Override
	public void addVoto(Muestra muestra, Voto voto) {
		EstadoDeMuestra abiertaATodaOpinion = new AbiertaATodaOpinion();
		EstadoDeMuestra votanSoloExpertos = new VotanSoloExpertos();
		if(muestra.getEstado().equals(abiertaATodaOpinion)) {
			muestra.getEstado().agregarVoto(voto, muestra);
		} else if (muestra.getEstado().equals(votanSoloExpertos)) {
			muestra.getEstado().votarYVerificar(voto, muestra);
		}
	}

}
