package muestras;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ManejoDeUsuarios.Basico;
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

		if(esVotoDeExperto(voto)) {
			muestra.setEstado(new VotanSoloExpertos());
		}
		
	}

	public boolean esVotoDeExperto(Voto voto) {
		//Si el voto no es basico, entonces es experto
		return !voto.getNivelDelVotante().equals(new Basico());
	}
	
	
	@Override
	public String resultadoActual(List<Voto> votos) {
		return super.opinionMasVotadaEn(votos);
	}

	

}
