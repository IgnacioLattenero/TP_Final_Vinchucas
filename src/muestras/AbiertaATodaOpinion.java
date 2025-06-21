package muestras;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ManejoDeUsuarios.Voto;

public class AbiertaATodaOpinion extends EstadoDeMuestra {

	@Override
	public void agregarVoto(Voto voto, Muestra muestra) throws Exception {
		
		if(muestra.getVotos().isEmpty()) {
			muestra.getVotos().add(voto); //Si es vacia, este primer voto va a ser el mas actual
		}else {
			super.añadirVotoSegunAntiguedad(voto, muestra);
		}
	
		
		//Cambio de estado si hay al menos un experto
		boolean hayUnExperto = muestra.getVotos().stream()
												 .anyMatch(v -> !v.getVotante().getRangoUsuario().equals("BASICO"));
		if(hayUnExperto) {
			muestra.setEstado(new VotanSoloExpertos());
		}
		
	}

	@Override
	public String resultadoActual(List<Voto> votos) {
		return super.opinionMasVotadaEn(votos);
	}

	

}
