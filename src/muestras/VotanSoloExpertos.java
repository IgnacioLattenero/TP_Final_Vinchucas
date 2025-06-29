package muestras;
import ManejoDeUsuarios.Voto;

public class VotanSoloExpertos extends EstadoDeMuestra {

	@Override
	public void agregarVoto(Voto voto, Muestra muestra) {
		voto.getNivelDelVotante().votarEnMuestraExpertos(muestra, voto);
	}
	
	@Override
	public String resultadoActual(Muestra muestra) {
		return super.opinionMasVotadaEn(muestra.votosDeExpertos());
	}

}
