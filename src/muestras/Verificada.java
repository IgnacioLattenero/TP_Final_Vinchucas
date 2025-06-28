package muestras;
import java.util.List;

import ManejoDeUsuarios.Voto;



public class Verificada extends EstadoDeMuestra {

	@Override
	public void agregarVoto(Voto voto, Muestra muestra) {
		//no se agregan votos en este estado
	}

	@Override
	public void votarEn(Voto voto, Muestra muestra) {
		//no se agregan votos en este estado
	}

	@Override
	public void votarYVerificar(Voto voto, Muestra muestra) {
		//no se agregan votos en este estado
	}

	@Override
	public String resultadoActual(List<Voto> votos) {
		return super.opinionMasVotadaEn(super.votosExpertosEn(votos));
	}

}
