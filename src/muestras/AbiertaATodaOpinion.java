package muestras;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ManejoDeUsuarios.Basico;
import ManejoDeUsuarios.Voto;

public class AbiertaATodaOpinion extends EstadoDeMuestra {

	@Override
	public void agregarVoto(Voto voto, Muestra muestra) {
		voto.getNivelDelVotante().addVoto(muestra, voto);
	}
	

	@Override
	public String resultadoActual(List<Voto> votos) {
		return super.opinionMasVotadaEn(votos);
	}


	@Override
	public void votarEn(Voto voto, Muestra muestra) {
		muestra.getVotos().add(voto);
	}

	@Override
	public void votarYVerificar(Voto voto, Muestra muestra) {
		//no se puede verificar en este estado
	}

}
