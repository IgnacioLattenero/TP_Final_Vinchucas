package muestras;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import ManejoDeUsuarios.*;


public abstract class EstadoDeMuestra {

	public abstract void agregarVoto(Voto voto, Muestra muestra);
	public abstract String resultadoActual(List<Voto> votos);
	public abstract void votarEn(Voto voto, Muestra muestra);
	public abstract void votarYVerificar(Voto voto, Muestra muestra);	
	
	public String opinionMasVotadaEn(List<Voto> votos) {

		List<Opinion> masVotados = mayoriaDeVotosEn(votos);
		
		// Si hay más de una opinión con el máximo valor, hay empate y el resultado es "No definida"
		if (masVotados.size() != 1) {
		  return "No definida";
		}

	    // Devolver el nombre de la opinión que fue la mas votada
		return masVotados.get(0).name();
	}
	
	public List<Opinion> mayoriaDeVotosEn(List<Voto> votos){
		
		//Contamos los votos y los enlistamos en un map (opinion, cantidadDeVotos)
		Map<Opinion, Long> numeroDeVotosPorOpinion = votos.stream()
							    						  .collect(Collectors.groupingBy(Voto::getOpinion, Collectors.counting()));

		//Obtengo el numero maximo de votos
		Optional<Long> cantidadMaximaDeVotos = numeroDeVotosPorOpinion.values().stream().max(Comparator.naturalOrder());
				
		//Pasar a Long con 0L como valor por defecto si no hay votos
		Long maxVotos = cantidadMaximaDeVotos.orElse(0L);
				
		//Ahora busco las opiniones que tienen ese valor maximo
		List<Opinion> masVotados = numeroDeVotosPorOpinion.entrySet().stream() //Convertimos en Stream el par clave/valor
														  .filter(entry -> entry.getValue() == maxVotos) //filtramos por valor
														  .map(Map.Entry::getKey) //Transforma cada entrada filtrada en su clave(Opinion).Ahora el stream es de tipo Stream<Opinion>.
														  .collect(Collectors.toList());//Juntamos las opiniones mas votadas en una lista
						
		return masVotados;
		
	}
	
}//End_Class
