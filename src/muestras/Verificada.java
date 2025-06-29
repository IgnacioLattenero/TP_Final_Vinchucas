package muestras;
import ManejoDeUsuarios.Voto;



public class Verificada extends EstadoDeMuestra {

	@Override
	public void agregarVoto(Voto voto, Muestra muestra) {
		//NO SE ADMITEN MAS VOTOS UNA VEZ VERIFICADA 
	}

	@Override
	public String resultadoActual(Muestra muestra) {
		return super.opinionMasVotadaEn(muestra.votosDeExpertos());
	}

}
