package muestras;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ManejoDeUsuarios.Basico;
import ManejoDeUsuarios.Opinion;
import ManejoDeUsuarios.Voto;

public class VotanSoloExpertos extends EstadoDeMuestra {

	@Override
	public void agregarVoto(Voto voto, Muestra muestra) {
		voto.getNivelDelVotante().addVoto(muestra, voto);
	}
		
	@Override
	public void votarEn(Voto voto, Muestra muestra) {
		muestra.getVotos().add(voto);
	}
	
	@Override
	public void votarYVerificar(Voto voto, Muestra muestra) {
		this.votarEn(voto, muestra);
		if (muestra.hayOpinionCoincidenteCon(muestra.votosDeExpertos(), voto.getOpinion())) {
			muestra.setEstado(new Verificada());
		}
	}
	
	@Override
	public String resultadoActual(List<Voto> votos) {
		return super.opinionMasVotadaEn(super.votosExpertosEn(votos));
	}

}
