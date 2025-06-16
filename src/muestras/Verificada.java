package muestras;
import java.util.List;

public class Verificada extends EstadoDeMuestra {

	@Override
	public void agregarVoto(Voto voto, Muestra muestra) {
		
		throw new Exception("La muestra ya esta Verificada, no se admiten mas votos!!!");

	}

	@Override
	public String resultadoActual(List<Voto> votos) {			
		return super.opinionMasVotadaEn(super.votosExpertosEn(votos));
		
	}

}
