package ManejoDeUsuarios;

import java.util.List;
import muestras.Muestra;
import muestras.Verificada;

public class Especialista extends NivelDeUsuario {
	
	@Override
	public void cambiarNivelDelUsuario(List<Muestra> muestras, Usuario usuario) {
		
		/**
		 * Este método no hace nada, ya que un especialista nunca pierde su nivel
		 * */
		
	}

	@Override
	public void votarEnMuestraAbierta(Muestra muestra, Voto voto) {
		muestra.getVotos().add(voto);		
	}

	@Override
	public void votarEnMuestraExpertos(Muestra muestra, Voto voto) {

		if (muestra.hayOpinionCoincidenteCon(muestra.votosDeExpertos(), voto.getOpinion())) {
			muestra.setEstado(new Verificada());
			
			//NOTIFICAR A LAS ZONAS DE COBERTURA.
			muestra.getZonasALasQuePertenece().stream()
											  .forEach(z -> z.notifyValidacionMuestra(z, muestra));
		}
		
		muestra.getVotos().add(voto);
		
		
		
		
	}

	@Override
	public boolean esVotoCalificado() {
			return true;
	}
	
}
