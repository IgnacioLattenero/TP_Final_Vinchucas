package muestras;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AbiertaATodaOpinion extends EstadoDeMuestra {

	@Override
	public void agregarVoto(Voto voto, Muestra muestra) {
		
		muestra.getVotos().add(voto);
		
		//Cambio de estado si hay al menos un experto
		boolean hayUnExperto = muestra.getVotos().stream()
												 .anyMatch(v -> !v.getUsuario().getRangoUsuario().equals("BASICO"));
		if(hayUnExperto) {
			muestra.setEstado(new VotanSoloExpertos());
		}
		
	}

	@Override
	public String resultadoActual(List<Voto> votos) {
		
		super.opinionMasVotadaEn(votos);
	}

}
