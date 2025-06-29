package muestras;
import ManejoDeUsuarios.Voto;

public class AbiertaATodaOpinion extends EstadoDeMuestra {

	@Override
	public void agregarVoto(Voto voto, Muestra muestra) {
		voto.getNivelDelVotante().votarEnMuestraAbierta(muestra, voto);
	}
	

	@Override
	public String resultadoActual(Muestra muestra) {
		return super.opinionMasVotadaEn(muestra.getVotos());
	}

}
