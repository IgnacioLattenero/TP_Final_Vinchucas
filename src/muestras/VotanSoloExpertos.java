package muestras;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ManejoDeUsuarios.Opinion;
import ManejoDeUsuarios.Voto;

public class VotanSoloExpertos extends EstadoDeMuestra {

	@Override
	public void agregarVoto(Voto voto, Muestra muestra) throws Exception {
		if(!voto.getVotante().equals("BASICO")) { //TODO: MAL Y FEA CONDICION AQUI (repensar)
			super.añadirVotoSegunAntiguedad(voto, muestra); //ESTO SE QUEDA!
		}else {
			throw new Exception("Solo pueden votar los expertos!!!");
		}
		
		//Cambiar de estado si dos expertos coinciden (pasa a verificada)
		
		if(hayCoincidencia(muestra.getVotos())) {
			muestra.setEstado(new Verificada());
		}
		
	}

	@Override
	public String resultadoActual(List<Voto> votos) {
		return super.opinionMasVotadaEn(super.votosExpertosEn(votos));
	}
	

	public boolean hayCoincidencia(List<Voto> votos) {
		
		// Extraemos de la lista de votos, solo aquellos realizados 
		// por usuarios expertos/especialistas
		List<Voto> votosExpertos = super.votosExpertosEn(votos);
		
		//Opinion / cantidadDeVotos
		Map<Opinion, Long> votosPorOpinion = votosExpertos.stream()
							    				  		  .collect(Collectors.groupingBy(Voto::getOpinion, Collectors.counting()));
		
		return votosPorOpinion.entrySet().stream() //Convertimos en Stream el par clave/valor
										 .anyMatch(entry -> entry.getValue() >= 2);//Si hay alguno con 2 o mas votos, entonces hay coincidencia
	}



}
