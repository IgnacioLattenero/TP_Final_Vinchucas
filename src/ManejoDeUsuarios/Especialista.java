package ManejoDeUsuarios;

import java.util.List;

import muestras.AbiertaATodaOpinion;
import muestras.EstadoDeMuestra;
import muestras.Muestra;
import muestras.VotanSoloExpertos;

public class Especialista extends NivelDeUsuario {
	
	@Override
	public void cambiarNivelDelUsuario(List<Muestra> muestras, Usuario usuario) {
		
		/**
		 * Este método no hace nada, ya que un especialista nunca pierde su nivel
		 * */
		
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
