package muestras;
import ManejoDeUsuarios.Voto;



public class Verificada extends EstadoDeMuestra {

	@Override
	public void agregarVoto(Voto voto, Muestra muestra) {
		//no se agregan votos en este estado
	}

	@Override
	public String resultadoActual(Muestra muestra) {
		return super.opinionMasVotadaEn(muestra.votosDeExpertos());
	}

}
