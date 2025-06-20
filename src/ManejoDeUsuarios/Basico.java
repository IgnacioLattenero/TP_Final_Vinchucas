package ManejoDeUsuarios;

import java.time.LocalDateTime;

public class Basico implements NivelDeUsuario {
	
	public Basico() {
	
	}
	
	@Override
	public void subirDeNivel(Usuario usuario) {
		
		if (usuario.cantidadDeEnvios() >= 10 && usuario.cantidadDeRevisiones() >= 20) {
			
			usuario.setNivel(new Experto());
		}
		
	}

}
