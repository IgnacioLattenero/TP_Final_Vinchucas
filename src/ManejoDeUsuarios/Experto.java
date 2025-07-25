package ManejoDeUsuarios;

import java.util.List;
import muestras.Muestra;
import muestras.Verificada;
import muestras.VotanSoloExpertos;

public class Experto extends NivelDeUsuario {


	@Override
	public void cambiarNivelDelUsuario(List<Muestra> muestras, Usuario usuario) {
		
		if (!super.hizoMasDe10EnviosEnLosUltimos30Dias(muestras, usuario) ||
			!super.hizoMasDe20RevisionesEnLosUltimos30Dias(muestras, usuario)) {

				usuario.setNivel(new Basico());

		}
	
	}

	@Override
	public void votarEnMuestraAbierta(Muestra muestra, Voto voto) {
		muestra.getVotos().add(voto);
		muestra.setEstado(new VotanSoloExpertos());
	}

	@Override
	public void votarEnMuestraExpertos(Muestra muestra, Voto voto) {
		
		if (muestra.hayOpinionCoincidenteCon(muestra.votosDeExpertos(), voto.getOpinion())) {
			
			muestra.setEstado(new Verificada());//Muestra es verificada
			
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
